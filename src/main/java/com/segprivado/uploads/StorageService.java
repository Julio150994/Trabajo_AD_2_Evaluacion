package com.segprivado.uploads;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
import java.util.stream.Stream;


public interface StorageService {

	void init();

	String store(MultipartFile file, int id);

	Stream<Path> loadAll();

	Path load(String foto);

	Resource loadAsResource(String foto);
	
	void delete(String foto);
	
	void deleteAll();

}
