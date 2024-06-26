package com.codelabs.exmaples;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

/**
 * Demonstrates PUT requests using RestTemplate.
 */
public class RestTemplatePutExample {

    /**
     * Performs a PUT request to update resource at specified URL.
     */
    public void putExample() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        String requestBody = "{\"id\":1, \"title\":\"Updated Title\",\"body\":\"Updated Body\",\"userId\":1}";
        HttpEntity<String> entity = new HttpEntity<>(requestBody);
        restTemplate.put(url, entity);
    }

    /**
     * Performs a PUT request using exchange method, logging response body and handling status code.
     */
    public void putWithExchangeExample() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        String requestBody = "{\"id\":1, \"title\":\"Updated Title\",\"body\":\"Updated Body\",\"userId\":1}";
        HttpEntity<String> entity = new HttpEntity<>(requestBody);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);

        HttpStatusCode statusCode = response.getStatusCode();
        if (statusCode == HttpStatus.OK) {
            System.out.println("PUT request successful.");
            System.out.println("Response Body: " + response.getBody());
        } else {
            System.err.println("PUT request failed. Status code: " + statusCode);
        }
    }
}