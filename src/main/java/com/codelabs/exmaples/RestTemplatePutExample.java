package com.codelabs.exmaples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Demonstrates PUT requests using RestTemplate.
 */
@Component
public class RestTemplatePutExample {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Performs a PUT request to update resource at specified URL.
     */
    public void putExample() {
        String uriPath = "/posts/1";
        String requestBody = "{\"id\":1, \"title\":\"Updated Title\",\"body\":\"Updated Body\",\"userId\":1}";
        HttpEntity<String> entity = new HttpEntity<>(requestBody);
        restTemplate.put(uriPath, entity);
    }

    /**
     * Performs a PUT request using exchange method, logging response body and handling status code.
     */
    public void putWithExchangeExample() {
        String uriPath = "/posts/1";
        String requestBody = "{\"id\":1, \"title\":\"Updated Title\",\"body\":\"Updated Body\",\"userId\":1}";
        HttpEntity<String> entity = new HttpEntity<>(requestBody);
        ResponseEntity<String> response = restTemplate.exchange(uriPath, HttpMethod.PUT, entity, String.class);

        HttpStatusCode statusCode = response.getStatusCode();
        if (statusCode == HttpStatus.OK) {
            System.out.println("PUT request successful.");
            System.out.println("Response Body: " + response.getBody());
        } else {
            System.err.println("PUT request failed. Status code: " + statusCode);
        }
    }
}