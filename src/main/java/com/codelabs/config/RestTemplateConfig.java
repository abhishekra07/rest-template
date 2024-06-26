package com.codelabs.config;

import com.codelabs.exception.CustomResponseErrorHandler;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class RestTemplateConfig {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final int TIMEOUT = 5000; // Timeout in milliseconds (5 seconds)

    @Bean
    public RestTemplate restTemplate(RestTemplateCustomizer customizer) {
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
        customizer.customize(restTemplate);

        CustomResponseErrorHandler errorHandler = new CustomResponseErrorHandler();
        restTemplate.setErrorHandler(errorHandler);
        return restTemplate;
    }

    @Bean
    public RestTemplateCustomizer restTemplateCustomizer() {
        return restTemplate -> restTemplate.getInterceptors().add(baseUrlInterceptor());
    }

    private ClientHttpRequestInterceptor baseUrlInterceptor() {
        return (request, body, execution) -> {
            try {
                URI uri = new URI(BASE_URL + request.getURI().toString());
                request.getHeaders().set("Base-URL-Header", BASE_URL);
                request = new HttpRequestWrapper(request) {
                    @Override
                    public URI getURI() {
                        return uri;
                    }
                };
            } catch (URISyntaxException e) {
                throw new IOException(e);
            }
            return execution.execute(request, body);
        };
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(TIMEOUT); // Set connection timeout
        factory.setReadTimeout(TIMEOUT); // Set read timeout
        return factory;
    }
}