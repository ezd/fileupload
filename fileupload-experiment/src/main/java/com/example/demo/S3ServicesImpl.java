package com.example.demo;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressListener;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;

@Service
public class S3ServicesImpl implements S3Services{

	Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	TransferManager transferManager;
	
	@Value("${jsa.s3.bucket}")
	protected String bucketName;

	@Override
	public void uploadFile(String keyName, String uploadFilePath) {
		PutObjectRequest request=new PutObjectRequest(bucketName, keyName, new File(uploadFilePath));
		
		request.setGeneralProgressListener(new ProgressListener() {
			
			@Override
			public void progressChanged(ProgressEvent progressEvent) {
				String transferredBytes="Uploaded bytes:"+progressEvent.getBytesTransferred();
				logger.info(transferredBytes);
				
			}
		});
		
		Upload upload=transferManager.upload(request);
		
		
		try{
			upload.waitForCompletion();
			
		}catch(AmazonServiceException e){
			logger.warn("Amazon Service Ex: "+e.getMessage());
		}catch(AmazonClientException e){
			logger.warn("Amazon Client Ex:"+e.getMessage());
			logger.warn("Amazon Client Ex:"+e.getStackTrace().toString());
		}catch(InterruptedException e){
			logger.warn("Interrupted Ex:"+e.getMessage());
		}
		
	}

	@Override
	public void downloadFile(String keyName, String downloadFilePath) {
		/**
		 * final GetObjectRequest request = new GetObjectRequest(bucketName, keyName);
		
		request.setGeneralProgressListener(new ProgressListener() {
			@Override
			public void progressChanged(ProgressEvent progressEvent) {
				String transferredBytes = "Downloaded bytes: " + progressEvent.getBytesTransferred();
				logger.info(transferredBytes);
			}
		});
		
		Download download = transferManager.download(request, new File(downloadFilePath));
		
		try {
			
			download.waitForCompletion();
			
		} catch (AmazonServiceException e) {
			logger.info(e.getMessage());
		} catch (AmazonClientException e) {
			logger.info(e.getMessage());
		} catch (InterruptedException e) {
			logger.info(e.getMessage());
		}
	}
		 */
		
	}
	
}
