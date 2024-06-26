package com.codelabs.exmaples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Demonstrates various ways to perform GET requests using RestTemplate.
 */
@Component
public class RestTemplateGetExample {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Performs a simple GET request and logs the response body.
     */
    public void getForObjectExample() {
        String uriPath = "/posts/1";
        String result = restTemplate.getForObject(uriPath, String.class);
        System.out.println("getForObjectExample -> GET API Call Result : " + result);
    }

    /**
     * Performs a GET request and logs the response body, headers, and status code.
     */
    public void getForEntityExample() {
        String uriPath = "/posts/1";
        ResponseEntity<String> response = restTemplate.getForEntity(uriPath, String.class);
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
        String uriPath = "/posts/1";
        ResponseEntity<String> response = restTemplate.exchange(uriPath, HttpMethod.GET, HttpEntity.EMPTY, String.class);
        System.out.println("getWithExchangeExample -> " + response.getBody());
        System.out.println("getWithExchangeExample -> " + response.getStatusCode());
        System.out.println("getWithExchangeExample -> " + response.getHeaders());
    }

    /**
     * Performs a GET request using the exchange method with custom headers and logs the response body, headers, and status code.
     */
    public void getWithExchangeWithHeadersExample() {
        String uriPath = "/posts/1";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(uriPath, HttpMethod.GET, entity, String.class);
        System.out.println("getWithExchangeWithHeadersExample -> " + response.getBody());
        System.out.println("getWithExchangeWithHeadersExample -> " + response.getStatusCode());
        System.out.println("getWithExchangeWithHeadersExample -> " + response.getHeaders());
    }
}
