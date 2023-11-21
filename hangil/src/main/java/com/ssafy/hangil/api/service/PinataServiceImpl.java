package com.ssafy.hangil.api.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PinataServiceImpl implements IPinataService {

    @Value("${pinata.api.key}")
    private String pinataApiKey;

    @Value("${pinata.api.secret.key}")
    private String pinataSecretApiKey;

    public List<String> uploadToPinata(MultipartFile[] imageFiles) throws IOException {

        List<String> cids = new ArrayList<>();

        for (MultipartFile imageFile : imageFiles) {
            File file = convertMultiPartToFile(imageFile);

            String url = "https://api.pinata.cloud/pinning/pinFileToIPFS";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            headers.set("pinata_api_key", pinataApiKey);
            headers.set("pinata_secret_api_key", pinataSecretApiKey);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", new FileSystemResource(file));

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

            String cid = extractCidFromResponse(response.getBody());
            cids.add(cid);

            file.delete();
        }

        return cids;
    }

    public File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    public String extractCidFromResponse(String responseBody) {
        JSONObject json = new JSONObject(responseBody);
        return json.getString("IpfsHash");
    }
}
