package com.example.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiClient {
    private String baseUrl;
    private AuthManager authManager;
    private OkHttpClient okHttpClient;

    public ApiClient(String baseUrl, AuthManager authManager) {
        this.baseUrl = baseUrl;
        this.authManager = authManager;
        this.okHttpClient = new OkHttpClient();
    }

    // HTTPURLConnection approach
    public ApiResponse getUserWithHttpURLConnection(int userId) throws Exception {
        String endpoint = baseUrl + "/api/user/" + userId;
        URL url = new URL(endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        for (Map.Entry<String, String> entry : authManager.getAuthHeaders().entrySet()) {
            conn.setRequestProperty(entry.getKey(), entry.getValue());
        }
        int status = conn.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        conn.disconnect();
        return new ApiResponse(status, content.toString());
    }

    // OkHttp (REST) approach
    public ApiResponse getUserWithOkHttp(int userId) throws Exception {
        String endpoint = baseUrl + "/api/users/" + userId;
        Request.Builder builder = new Request.Builder().url(endpoint);
        for (Map.Entry<String, String> entry : authManager.getAuthHeaders().entrySet()) {
            builder.addHeader(entry.getKey(), entry.getValue());
        }
        Request request = builder.build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            String body = response.body() != null ? response.body().string() : "";
            return new ApiResponse(response.code(), body);
        }
    }

    public static class ApiResponse {
        public int statusCode;
        public String body;
        public ApiResponse(int statusCode, String body) {
            this.statusCode = statusCode;
            this.body = body;
        }
    }
}

