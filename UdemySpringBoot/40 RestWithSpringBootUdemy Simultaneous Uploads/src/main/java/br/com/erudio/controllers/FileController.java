package br.com.erudio.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.erudio.data.vo.v1.UploadFileResponseVO;
import br.com.erudio.services.FileStorageService;
import io.swagger.annotations.Api;

@Api(tags = "FileEndpoint")
@RestController
@RequestMapping("/api/file/v1")
public class FileController {

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
	
	
}
