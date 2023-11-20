package com.ssafy.hangil.api.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IPinataService {
	public List<String> uploadToPinata(MultipartFile[] imageFiles) throws IOException;
	
	public File convertMultiPartToFile(MultipartFile file) throws IOException;

	public String extractCidFromResponse(String responseBody);
}
