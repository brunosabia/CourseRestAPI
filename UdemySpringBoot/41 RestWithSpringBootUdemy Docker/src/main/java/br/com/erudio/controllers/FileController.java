package br.com.erudio.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.common.net.HttpHeaders;

import br.com.erudio.data.vo.v1.UploadFileResponseVO;
import br.com.erudio.services.FileStorageService;
import io.swagger.annotations.Api;

@Api(tags = "FileEndpoint")
@RestController
@RequestMapping("/api/file/v1")
public class FileController {

	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class); //injeção do logger apenas para retornar o log da file de download no tratamento de exceções
	
	@Autowired
	private FileStorageService fileStorageService;
	
	@PostMapping("/uploadFile")
	public UploadFileResponseVO uploadFile(@RequestParam("file") MultipartFile file) {
		
		String fileName = fileStorageService.storeFile(file); //esse método pega o path da file e retorna o fileName.
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath() //aqui basicamente seta o link de download da file que estamos Uploadando
				.path("/api/file/v1/downloadFile/") //esse path será usado futuramente apenas
				.path(fileName)
				.toUriString();
		
		return new UploadFileResponseVO(fileName,fileDownloadUri, file.getContentType(), file.getSize()); //retornamos um VO populando o construtor dele com todos os atributos recebidos.
	}
	
	//a lógica deste método é receber um array de files do body da request, e transformar esse array em uma lista, executando o upload (do método uploadFile) de cada um dos files da lista
	@PostMapping("/uploadMutipleFiles")
	public List<UploadFileResponseVO> uploadMutipleFiles(@RequestParam("files") MultipartFile[] files) {
		
		return Arrays.asList(files) //transforma o array em lista
					 .stream() //percorre pela lista
					 .map(file -> uploadFile(file))//para cada item da lista ele executa o método UploadFile passando cada file
					 .collect(Collectors.toList()); //Coleta as responses e monta uma lista com cada retorno.
	}
	
	//método responsável pelo download da file. recebendo o fileName
	@GetMapping("/downloadFile/{fileName:.+}") //a variável é colocada dentro das chaves e o :.+ é para mostrar que aceita qualquer tipo de extensão.
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		//primeiramente vamos buscar a nossa file no disco.
		
		Resource resource = fileStorageService.loadFileAsResource(fileName); // envia o fileName e tras o path todo do file como um resource.
		
		String contentType = null; //iniciamos o contentType
		
		 try {
			 
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath()); //populamos o contentType
		
		 } catch (Exception e) {
			logger.info("Could not determine file type.");//logger irá mandar o log desse erro.
		}
		 
		 if(contentType == null) {
			 contentType = "application/octet-stream"; //caso o contentType nao seja encontrado ou nao seja valido, este funcionará como um type genérico.
		 }
		
		 //aqui nós estamos construindo nossa Response Entity
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))//setando nossa responseEntity com o media type que encontramos acima.
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=)\""+ resource.getFilename() + "\"") //setamos no header o anexo
				.body(resource); //adicionamos o nosso resource(o file transformado em rosource) ao body da response
	}
	
}
