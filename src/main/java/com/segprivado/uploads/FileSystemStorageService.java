package com.segprivado.uploads;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileSystemStorageService implements StorageService {

	private final Path rootLocation;// directorio para encontrar la imágen del paciente
	
	@Autowired
	StorageService storageService;
	
	@Autowired
	public FileSystemStorageService(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}
	
	
	@Override
	public String store(MultipartFile imagen, int id) {
		String photo = StringUtils.cleanPath(imagen.getOriginalFilename());
		String ext = StringUtils.getFilenameExtension(photo);
		String uploader = id + "." + ext;
		
		try {
			if (imagen.isEmpty())
				throw new StorageException("Error al subir archivo.");
			
			if (!photo.contains("..")) 
				throw new StorageException("No debe almacenar el archivo fuera del directorio "+imagen);
			
			try (InputStream inputStream = imagen.getInputStream()) {
				Files.copy(inputStream, this.rootLocation.resolve(uploader),StandardCopyOption.REPLACE_EXISTING);//contenido de la imágen que en caso de que exista se reemplace
				return uploader;// Procedemos a subir el archivo
			}
		}
		catch (IOException e) {
			throw new StorageException("Esta imágen no se ha podido almacenar.", e);
		}
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 1)
				.filter(path -> !path.equals(this.rootLocation))
				.map(this.rootLocation::relativize);
		}
		catch (IOException e) {
			throw new StorageException("Error en la lectura del archivo almacenado", e);
		}

	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
			else {
				throw new StorageFileNotFoundException(
						"No se ha podido leer el archivo: " + filename);

			}
		}
		catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("No se ha podido leer el archivo: " + filename, e);
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		}
		catch (IOException e) {
			throw new StorageException("Error al inicializar el almacenamiento", e);
		}
	}

	@Override
	public void delete(String foto) {
		storageService.delete(foto);
	}
}
