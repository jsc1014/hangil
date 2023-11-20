package com.ssafy.hangil.api.service;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface IPinataService {
	public String uploadToPinata(MultipartFile imageFile) throws IOException;
	
	public File convertMultiPartToFile(MultipartFile file) throws IOException;

	public String extractCidFromResponse(String responseBody);
}
