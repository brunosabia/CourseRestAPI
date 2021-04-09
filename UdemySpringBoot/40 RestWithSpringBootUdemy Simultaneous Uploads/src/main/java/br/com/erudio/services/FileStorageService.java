package br.com.erudio.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.erudio.Exception.FileStorageException;
import br.com.erudio.config.FileStorageConfig;

@Service
public class FileStorageService {

	private final Path fileStorageLocation;
	
	@Autowired
	public FileStorageService(FileStorageConfig fileStorageConfig) {
	
		this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir()) //vai até o config que criamos e busca o diretório de upload
				.toAbsolutePath() //retorna o path absoluto
				.normalize(); //normaliza o path para evitar nomes redundantes
	
		//vamos procurar o diretório informado acima
		try {
			Files.createDirectories(this.fileStorageLocation);//ou seja , ele vai TENTAR criar esse diretorio na localizacao passada acima.
		} catch (Exception e) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",e);
		}
	}

	public String storeFile(MultipartFile file) { //RECEBE O FILE
		String fileName = StringUtils.cleanPath(file.getOriginalFilename()); //o cleanPath limpa o FileName, caso ele tem um espaço, por exemplo, daria problema em um ambiente Linux
		
		try {
			if(fileName.contains("..")) {
				throw new FileStorageException("Filename contains invalid path sequence " + fileName);
			} 
			//se passar por essa outra validação finalmente será armazenado em disco
			
			Path targetLocation = this.fileStorageLocation.resolve(fileName); //criando o Path targetLocation que irá receber o file
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING); //copia o InputStream() que veio dentro do file  para a targetLocation Substituindo CASO já exista.
			
			
			return fileName;
			
		} catch (Exception e) {
			throw new FileStorageException("Could not store the file " + fileName + ". Please try again.", e);
		}
	}
		
}
