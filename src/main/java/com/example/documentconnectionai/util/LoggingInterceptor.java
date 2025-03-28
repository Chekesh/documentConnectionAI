package com.example.documentconnectionai.util;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Set;

/**
 * Interceptor for logging HTTP requests and responses.
 * Implements ClientHttpRequestInterceptor to intercept outgoing HTTP requests
 * and their corresponding responses, logging details while protecting sensitive information.
 */
@Slf4j
public class LoggingInterceptor implements ClientHttpRequestInterceptor {

    /**
     * Set of header names that contain sensitive information and should be protected in logs.
     */
    private static final Set<String> SENSITIVE_HEADERS = Set.of("Authorization", "Cookie", "Set-Cookie");

    /**
     * Intercepts HTTP requests and responses for logging purposes.
     * Logs details of the request before execution and response after execution.
     *
     * @param request   the request to be executed
     * @param body      the body of the request
     * @param execution the execution chain to be invoked
     * @return the response from the execution
     * @throws IOException in case of I/O errors during request execution
     */
    @NotNull
    @Override
    public ClientHttpResponse intercept(@NotNull final HttpRequest request,
                                        @NotNull final byte[] body,
                                        final ClientHttpRequestExecution execution) throws IOException {
        logRequest(request, body);
        final ClientHttpResponse response = execution.execute(request, body);
        logResponse(response);
        return response;
    }

    /**
     * Logs details of an HTTP request.
     * Includes method, URI, headers (with sensitive information masked), and body.
     *
     * @param request the HTTP request to log
     * @param body    the body of the request
     */
    private void logRequest(final HttpRequest request, final byte[] body) {
        final StringBuilder logMessage = new StringBuilder();
        logMessage.append("Sending request: ")
                .append(request.getMethod()).append(" ")
                .append(request.getURI()).append("\n");

        request.getHeaders().forEach((key, value) -> {
            if (SENSITIVE_HEADERS.contains(key)) {
                logMessage.append(key).append(": [PROTECTED]").append("\n");
            } else {
                logMessage.append(key).append(": ").append(value).append("\n");
            }
        });

        if (body.length > 0) {
            logMessage.append("Request body: ").append(new String(body, StandardCharsets.UTF_8)).append("\n");
        }

        log.info(logMessage.toString());
    }

    /**
     * Logs details of an HTTP response.
     * Includes status code and headers.
     *
     * @param response the HTTP response to log
     * @throws IOException if an I/O error occurs while accessing the response
     */
    private void logResponse(final ClientHttpResponse response) throws IOException {
        final StringBuilder logMessage = new StringBuilder();
        logMessage.append("Received response: ")
                .append(response.getStatusCode()).append("\n");

        response.getHeaders().forEach((key, value) ->
                logMessage.append(key).append(": ").append(value).append("\n"));

        log.info(logMessage.toString());
    }
}