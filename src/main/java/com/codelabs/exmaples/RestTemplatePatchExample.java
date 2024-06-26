package com.codelabs.exmaples;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

/**
 * Demonstrates PATCH requests using RestTemplate.
 */
public class RestTemplatePatchExample {

    /**
     * Performs a PATCH request using exchange method with custom headers, logging the response body and status code.
     */
    public void patchExchangeExample() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        String requestBody = "{\"title\":\"Updated Title\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PATCH, entity, String.class);
        HttpStatusCode statusCode = response.getStatusCode();
        if (statusCode == HttpStatus.OK) {
            System.out.println("PATCH request successful.");
            System.out.println("Response Body: " + response.getBody());
        } else {
            System.err.println("PATCH request failed. Status code: " + statusCode);
        }
    }

    /**
     * Performs a PATCH request using exchange method without custom headers, logging the response body and status code.
     */
    public void patchExchangeWithoutHeaderExample() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        String requestBody = "{\"title\":\"Updated Title\"}";
        HttpEntity<String> entity = new HttpEntity<>(requestBody);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PATCH, entity, String.class);
        HttpStatusCode statusCode = response.getStatusCode();
        if (statusCode == HttpStatus.OK) {
            System.out.println("PATCH request successful.");
            System.out.println("Response Body: " + response.getBody());
        } else {
            System.err.println("PATCH request failed. Status code: " + statusCode);
        }
    }
}