package com.codelabs.exmaples;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class RestTemplatePostExample {

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

    public void postExchangeExample() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/posts";
        String requestJson = "{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1}";
        HttpEntity<String> entity = new HttpEntity<>(requestJson);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println("postForEntityExample -> " + response.getBody());
        System.out.println("postForEntityExample -> " + response.getStatusCode());
        System.out.println("postForEntityExample -> " + response.getHeaders());
    }

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
