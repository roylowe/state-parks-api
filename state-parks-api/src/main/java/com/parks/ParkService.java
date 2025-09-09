package com.parks;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.*;

@Service
public class ParkService {

    private final String API_KEY = "tcNI5fO7eCpNidGvgAentW8jcqPff6gALbMIN62V";

    public List<Map<String, Object>> fetchParks(String stateCode) {
        String url = "https://developer.nps.gov/api/v1/parks?stateCode=" + stateCode + "&limit=10";

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", API_KEY);

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
        Map<String, Object> responseBody = responseEntity.getBody();

        if (responseBody == null || !responseBody.containsKey("data")) {
            throw new IllegalStateException("No data found in response");
        }

        Object data = responseBody.get("data");
        if (!(data instanceof List)) {
            throw new IllegalStateException("Unexpected data format");
        }

        return (List<Map<String, Object>>) data;
    }
}