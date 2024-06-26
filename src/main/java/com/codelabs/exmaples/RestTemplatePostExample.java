package com.codelabs.exmaples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * Demonstrates various ways to perform POST requests using RestTemplate.
 */
@Component
public class RestTemplatePostExample {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Performs a POST request and retrieves the response as a String.
     */
    public void postForObjectExample() {
        String uriPath = "/posts";
        String requestJson = "{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        String result = restTemplate.postForObject(uriPath, entity, String.class);
        System.out.println("postForObjectExample -> " + result);
    }

    /**
     * Performs a POST request and retrieves the response as ResponseEntity, logging body, status code, and headers.
     */
    public void postForEntityExample() {
        String uriPath = "/posts";
        String requestJson = "{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(uriPath, entity, String.class);
        System.out.println("postForEntityExample -> " + response.getBody());
        System.out.println("postForEntityExample -> " + response.getStatusCode());
        System.out.println("postForEntityExample -> " + response.getHeaders());
    }

    /**
     * Performs a POST request using exchange method, logging body, status code, and headers.
     */
    public void postExchangeExample() {
        String uriPath = "/posts";
        String requestJson = "{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1}";
        HttpEntity<String> entity = new HttpEntity<>(requestJson);
        ResponseEntity<String> response = restTemplate.exchange(uriPath, HttpMethod.POST, entity, String.class);
        System.out.println("postExchangeExample -> " + response.getBody());
        System.out.println("postExchangeExample -> " + response.getStatusCode());
        System.out.println("postExchangeExample -> " + response.getHeaders());
    }

    /**
     * Performs a POST request using exchange method with custom headers, logging body, status code, and headers.
     */
    public void postExchangeWithHeadersExample() {
        String uriPath = "/posts";
        String requestJson = "{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        ResponseEntity<String> response = restTemplate.exchange(uriPath, HttpMethod.POST, entity, String.class);
        System.out.println("postExchangeWithHeadersExample -> " + response.getBody());
        System.out.println("postExchangeWithHeadersExample -> " + response.getStatusCode());
        System.out.println("postExchangeWithHeadersExample -> " + response.getHeaders());
    }

    /**
     * Performs a POST request and retrieves the response location URI.
     */
    public void postForLocationExample() {
        String uriPath = "/posts";
        String requestJson = "{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        URI response = restTemplate.postForLocation(uriPath, entity);
        System.out.println("postForLocationExample -> " + response);
    }

}