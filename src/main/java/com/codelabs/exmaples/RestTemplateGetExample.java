package com.codelabs.exmaples;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

/**
 * Demonstrates various ways to perform GET requests using RestTemplate.
 */
public class RestTemplateGetExample {

    /**
     * Performs a simple GET request and logs the response body.
     */
    public void getForObjectExample() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        String result = restTemplate.getForObject(url, String.class);
        System.out.println("getForObjectExample -> GET API Call Result : " + result);
    }

    /**
     * Performs a GET request and logs the response body, headers, and status code.
     */
    public void getForEntityExample() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        HttpHeaders headers = response.getHeaders();
        HttpStatus statusCode = (HttpStatus) response.getStatusCode();
        System.out.println("getForEntityExample -> " + response.getBody());
        System.out.println("getForEntityExample -> " + statusCode);
        System.out.println("getForEntityExample -> " + headers);
    }

    /**
     * Performs a GET request using the exchange method and logs the response body, headers, and status code.
     */
    public void getWithExchangeExample() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, String.class);
        System.out.println("getWithExchangeExample -> " + response.getBody());
        System.out.println("getWithExchangeExample -> " + response.getStatusCode());
        System.out.println("getWithExchangeExample -> " + response.getHeaders());
    }

    /**
     * Performs a GET request using the exchange method with custom headers and logs the response body, headers, and status code.
     */
    public void getWithExchangeWithHeadersExample() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("getWithExchangeWithHeadersExample -> " + response.getBody());
        System.out.println("getWithExchangeWithHeadersExample -> " + response.getStatusCode());
        System.out.println("getWithExchangeWithHeadersExample -> " + response.getHeaders());
    }
}
