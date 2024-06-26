package com.codelabs.exmaples;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * Demonstrates various ways to perform POST requests using RestTemplate.
 */
public class RestTemplatePostExample {

    /**
     * Performs a POST request and retrieves the response as a String.
     */
    public void postForObjectExample() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/posts";
        String requestJson = "{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        String result = restTemplate.postForObject(url, entity, String.class);
        System.out.println("postForObjectExample -> " + result);
    }

    /**
     * Performs a POST request and retrieves the response as ResponseEntity, logging body, status code, and headers.
     */
    public void postForEntityExample() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/posts";
        String requestJson = "{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        System.out.println("postForEntityExample -> " + response.getBody());
        System.out.println("postForEntityExample -> " + response.getStatusCode());
        System.out.println("postForEntityExample -> " + response.getHeaders());
    }

    /**
     * Performs a POST request using exchange method, logging body, status code, and headers.
     */
    public void postExchangeExample() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/posts";
        String requestJson = "{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1}";
        HttpEntity<String> entity = new HttpEntity<>(requestJson);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println("postExchangeExample -> " + response.getBody());
        System.out.println("postExchangeExample -> " + response.getStatusCode());
        System.out.println("postExchangeExample -> " + response.getHeaders());
    }

    /**
     * Performs a POST request using exchange method with custom headers, logging body, status code, and headers.
     */
    public void postExchangeWithHeadersExample() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/posts";
        String requestJson = "{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println("postExchangeWithHeadersExample -> " + response.getBody());
        System.out.println("postExchangeWithHeadersExample -> " + response.getStatusCode());
        System.out.println("postExchangeWithHeadersExample -> " + response.getHeaders());
    }

    /**
     * Performs a POST request and retrieves the response location URI.
     */
    public void postForLocationExample() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/posts";
        String requestJson = "{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        URI response = restTemplate.postForLocation(url, entity);
        System.out.println("postForLocationExample -> " + response);
    }

}