package com.ssafy.hangil.api.service;

import java.io.File;
import java.io.IOException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

@Service
public class PinataServiceImpl implements IPinataService {
	
	@Value("${pinata.api.key}")
	private String pinataApiKey;
	
	@Value("${pinata.api.secret.key")
	private String pinataSecretApiKey;

	public String uploadToPinata(MultipartFile imageFile) throws IOException {
		// MultipartFile을 File로 변환
		File file = convertMultiPartToFile(imageFile);

		// Pinata 엔드포인트
		String url = "https://api.pinata.cloud/pinning/pinFileToIPFS";

		// HttpHeaders 생성 및 인증 토큰 추가
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.set("pinata_api_key", pinataApiKey);
		headers.set("pinata_secret_api_key", pinataSecretApiKey);

		// 멀티파트 요청 바디 구성
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("file", new FileSystemResource(file));

		// 요청 엔티티 생성
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

		// RestTemplate을 사용하여 요청 전송
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

		// 응답에서 CID 추출
		String cid = extractCidFromResponse(response.getBody());

		// 임시 파일 삭제
		file.delete();

		return cid;
	}

	public File convertMultiPartToFile(MultipartFile file) throws IOException {
	    File convFile = new File(file.getOriginalFilename());
	    file.transferTo(convFile);
	    return convFile;
	}


	public String extractCidFromResponse(String responseBody) {
	    // JSON 파싱 라이브러리를 사용하거나, 정규 표현식 등을 사용해 CID를 추출
	    // 예시로, JSON 파싱을 가정하고 간단한 코드를 작성합니다.
	    JSONObject json = new JSONObject(responseBody);
	    return json.getString("IpfsHash"); // Pinata의 응답에서 IPFS 해시를 가지고 오는 키
	}
}
