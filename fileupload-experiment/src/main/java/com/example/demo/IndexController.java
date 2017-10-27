package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import groovyjarjarantlr.PreservingFileWriter;

@Controller
public class IndexController {
	static final Logger LOGGER=LoggerFactory.getLogger(IndexController.class); 

	@RequestMapping("/")
	public String getindex(@ModelAttribute("user") User user){
		return "index";
	}
	
	@RequestMapping("/submit")
	public String getSubmit(@ModelAttribute("user") User user,
			@RequestParam(name="file", required=false) MultipartFile file,
			Model model){
		System.out.println("coming");
		
		String profileImageUrl=null;
		if(null!=profileImageUrl){
			user.setProfileImageUrl(profileImageUrl);
		}
		else{
			LOGGER.warn("the image is not uploaded");
		}
		
		model.addAttribute("succssMsg", "success");
		model.addAttribute("url", file.getOriginalFilename());
		return "index";
	}
}
