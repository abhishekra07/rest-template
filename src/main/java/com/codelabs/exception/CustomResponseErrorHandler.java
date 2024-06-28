package com.codelabs.exception;

import jakarta.annotation.Nullable;
import org.springframework.core.log.LogFormatUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.*;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

public class CustomResponseErrorHandler extends DefaultResponseErrorHandler {

    @Nullable
    private List<HttpMessageConverter<?>> messageConverters;

    void setMessageConverters(List<HttpMessageConverter<?>> converters) {
        this.messageConverters = Collections.unmodifiableList(converters);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        String statusText = response.getStatusText();
        HttpHeaders headers = response.getHeaders();
        HttpStatusCode statusCode = response.getStatusCode();
        byte[] body = getResponseBody(response);
        Charset charset = getCharset(response);
        String message = getErrorMessage(statusCode.value(), statusText, body, charset, null, null);

        RestClientResponseException ex;
        if (statusCode.is4xxClientError()) {
            ex = HttpClientErrorException.create(message, statusCode, statusText, headers, body, charset);
        }
        else if (statusCode.is5xxServerError()) {
            ex = HttpServerErrorException.create(message, statusCode, statusText, headers, body, charset);
        }
        else {
            ex = new UnknownHttpStatusCodeException(message, statusCode.value(), statusText, headers, body, charset);
        }

        if (!CollectionUtils.isEmpty(this.messageConverters)) {
            ex.setBodyConvertFunction(initBodyConvertFunction(response, body));
        }

        throw ex;
    }

    /* We can also use this way to call super method if we dont want to add custom logic or just use DefaultResponseErrorHandler*/
    /*@Override
    public void handleError(ClientHttpResponse response) throws IOException {
        super.handleError(response);
    }*/

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().isError();
    }

    private String getErrorMessage(int rawStatusCode, String statusText, @Nullable byte[] responseBody, @Nullable Charset charset,
                                   @Nullable URI url, @Nullable HttpMethod method) {

        StringBuilder msg = new StringBuilder(rawStatusCode + " " + statusText);
        if (method != null) {
            msg.append(" on ").append(method).append(" request");
        }
        if (url != null) {
            msg.append(" for \"");
            String urlString = url.toString();
            int idx = urlString.indexOf('?');
            if (idx != -1) {
                msg.append(urlString, 0, idx);
            }
            else {
                msg.append(urlString);
            }
            msg.append("\"");
        }
        msg.append(": ");
        if (ObjectUtils.isEmpty(responseBody)) {
            msg.append("[no body]");
        }
        else {
            charset = (charset != null ? charset : StandardCharsets.UTF_8);
            String bodyText = new String(responseBody, charset);
            bodyText = LogFormatUtils.formatValue(bodyText, -1, true);
            msg.append(bodyText);
        }
        return msg.toString();
    }
}