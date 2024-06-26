# RestTemplate Examples
This repository contains examples of RestTemplate API calls.

## Example

### GET API Call

```java
RestTemplate restTemplate = new RestTemplate();
String URL = "https://jsonplaceholder.typicode.com/posts/1";

ResponseEntity<String> response = restTemplate.getForEntity(URL, String.class);

HttpHeaders headers = response.getHeaders();
HttpStatus statusCode = (HttpStatus) response.getStatusCode();
```


### GET API Call using Exchange

```java
ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, HttpEntity.EMPTY, String.class);
```

### POST API Call

```java
HttpHeaders headers = new HttpHeaders();
headers.setContentType(MediaType.APPLICATION_JSON);
HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

ResponseEntity<String> response = restTemplate.postForEntity(URL, entity, String.class);
```

### POST API Call using Exchange

```java
HttpHeaders headers = new HttpHeaders();
headers.setContentType(MediaType.APPLICATION_JSON);
HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
```

### PUT API Call using Exchange

```java
HttpHeaders headers = new HttpHeaders();
headers.setContentType(MediaType.APPLICATION_JSON);
HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

ResponseEntity<String> response = restTemplate.exchange(uriPath, HttpMethod.PUT, entity, String.class);
```

## Configure Interceptor
```java

private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
private static final int TIMEOUT = 5000;

@Bean
public RestTemplate restTemplate(RestTemplateCustomizer customizer) {
    RestTemplate restTemplate = new RestTemplat(clientHttpRequestFactory());
    customizer.customize(restTemplate);
	return restTemplate;
}

Bean
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
```