package com.codelabs.exmaples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Demonstrates DELETE requests using RestTemplate.
 */
@Component
public class RestTemplateDeleteExample {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Performs a simple DELETE request to delete a resource at the specified URL.
     */
    public void deleteExample() {
        String uriPath = "/posts/1";
        restTemplate.delete(uriPath);
        System.out.println("Deleted Successfully!");
    }

    /**
     * Performs a DELETE request using the exchange method, logging the response body and status code.
     */
    public void deleteWithExchangeExample() {
        String uriPath = "/posts/1";
        ResponseEntity<String> deleteResponse = restTemplate.exchange(uriPath, HttpMethod.DELETE, HttpEntity.EMPTY, String.class);
        System.out.println("Deleted Successfully! " + deleteResponse.getBody());
        System.out.println("Deleted Successfully! " + deleteResponse.getStatusCode());
    }
}