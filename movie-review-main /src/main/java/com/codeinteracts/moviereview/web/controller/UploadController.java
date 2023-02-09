package com.codeinteracts.moviereview.web.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.codeinteracts.moviereview.entity.Dummy;
import com.codeinteracts.moviereview.repository.DummyRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class UploadController {
	
	@Autowired
	private DummyRepository dummyRepository;

	@Value("${file.upload.path}")
	String UPLOAD_DIRECTORY;
	
	@Value("${file.upload.limit}")
	String LIMIT_VALUE;
	
//	private static final String UPLOAD_DIRECTORY ="images";  

	@GetMapping("/upload")
	public String displayUploadForm(Model model) {
		model.addAttribute("dummy", new Dummy());
		return "movie/upload";
	}
	
	@PostMapping("/upload")
	public String uploadImage(Model model, @ModelAttribute Dummy dummy, @RequestParam("image-file") MultipartFile file, HttpSession session) throws IOException {
		StringBuilder fileNames = new StringBuilder();
		
//		ServletContext context = session.getServletContext();  
//		String path = context.getRealPath(UPLOAD_DIRECTORY);  
//	    String filename = file.getOriginalFilename();  
//	    
//	    path = UPLOAD_DIRECTORY;
//	    
//	    String filePath = session.getServletContext().getRealPath("/")
//          + "WEB-INF" + File.separator + "resources"
//          + File.separator + "image" + File.separator
//          + file.getOriginalFilename();
//	    

//	    filePath = path + file.getOriginalFilename();
		
		System.out.println("FILE LIMIT:::"+LIMIT_VALUE);
		System.out.println("FILE UPLOAD_DIRECTORY:::"+UPLOAD_DIRECTORY);
		
		
//		Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
//		fileNames.append(file.getOriginalFilename());
//		Files.write(fileNameAndPath, file.getBytes());
	    
//	    Files.write(Paths.get(filePath), file.getBytes());
		
		
		dummy.setImage(file.getBytes());
		
		dummyRepository.save(dummy);
		Integer id = dummy.getId();
		System.out.println(id);
//		dummy.setFilepath(fileNameAndPath.toString());
		
		model.addAttribute("msg", "Uploaded images: " + fileNames.toString());
		return "movie/upload";
	}
	
	@GetMapping("/upload/{photoId}/details")
	public String uploadImage(Model model, @PathVariable Integer photoId) throws IOException {
		Dummy dummy = dummyRepository.findById(photoId).get();
		dummy.setFilepath("file://"+dummy.getFilepath());
		String imageStr = Base64Utils.encodeToString(dummy.getImage());
		dummy.setImageString(imageStr );
		model.addAttribute("dummy", dummy);
		return "movie/upload-details";
		
	}
}
