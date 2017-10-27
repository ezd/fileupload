package com.example.demo;

public interface S3Services {
	public void uploadFile(String keyName, String uploadFilePath);
	public void downloadFile(String keyName, String downloadFilePath);
}
