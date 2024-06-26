package com.codelabs.exmaples;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Demonstrates DELETE requests using RestTemplate.
 */
public class RestTemplateDeleteExample {

    /**
     * Performs a simple DELETE request to delete a resource at the specified URL.
     */
    public void deleteExample() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        restTemplate.delete(url);
        System.out.println("Deleted Successfully!");
    }

    /**
     * Performs a DELETE request using the exchange method, logging the response body and status code.
     */
    public void deleteWithExchangeExample() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        ResponseEntity<String> deleteResponse = restTemplate.exchange(url, HttpMethod.DELETE, HttpEntity.EMPTY, String.class);
        System.out.println("Deleted Successfully! " + deleteResponse.getBody());
        System.out.println("Deleted Successfully! " + deleteResponse.getStatusCode());
    }
}