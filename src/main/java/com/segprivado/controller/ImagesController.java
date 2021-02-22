package com.segprivado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import com.segprivado.uploads.StorageService;


@Controller
public class ImagesController {
	
	@Autowired
	StorageService storageService;
	
	
	@GetMapping("/files/{filename:.+}") // para utilizar el recurso en forma de imágen
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String foto) {
		Resource file = storageService.loadAsResource(foto);
		return ResponseEntity.ok().body(file);// cargamos nuestra imágen
	}
}
