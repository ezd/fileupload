package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileuploadExperimentApplication implements CommandLineRunner {

	@Autowired
	S3Services s3Services;
	
	@Value("${jsa.s3.uploadfile}")
	private String uploadFilePath;
	
	@Value("${jsa.s3.downloadfile}")
	private String downloadFilePath;
	
	@Value("${jsa.s3.key}")
	private String key;
	
	
	public static void main(String[] args) {
		SpringApplication.run(FileuploadExperimentApplication.class, args);
		
		
		
	}
	@Override
	public void run(String... args) throws Exception {
		System.out.println("---------------- START UPLOAD FILE ----------------");
		s3Services.uploadFile(key, uploadFilePath);
//		System.out.println("---------------- START DOWNLOAD FILE ----------------");
//		s3Services.downloadFile(key, downloadFilePath);
		System.out.println("DONE!");
	}
}
