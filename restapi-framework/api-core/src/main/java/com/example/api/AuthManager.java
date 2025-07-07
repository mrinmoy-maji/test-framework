package com.example.api;

import java.util.HashMap;
import java.util.Map;
import okhttp3.*;
import org.json.JSONObject;

public class AuthManager {
    private String token;
    private String baseUrl;
    private OkHttpClient client;

    public AuthManager(String baseUrl) {
        this.baseUrl = baseUrl;
        this.client = new OkHttpClient();
    }

    // Call login endpoint to retrieve token
    public void authenticate(String email, String password) throws Exception {
        String loginUrl = baseUrl + "/api/login";
        JSONObject json = new JSONObject();
        json.put("email", email);
        json.put("password", password);
        RequestBody body = RequestBody.create(json.toString(), MediaType.parse("application/json"));
        Request.Builder requestBuilder = new Request.Builder().url(loginUrl).post(body);
        // Add your API key here
        requestBuilder.addHeader("x-api-key", "reqres-free-v1");
        Request request = requestBuilder.build();
        try (Response response = client.newCall(request).execute()) {
            String respBody = response.body().string();
            if (!response.isSuccessful()) {
                throw new RuntimeException("Login failed: " + respBody);
            }
            JSONObject respJson = new JSONObject(respBody);
            if (!respJson.has("token")) {
                throw new RuntimeException("Login response missing token: " + respBody);
            }
            this.token = respJson.getString("token");
        }
    }

    public Map<String, String> getAuthHeaders() {
        Map<String, String> headers = new HashMap<>();
        if (token != null) {
            headers.put("Authorization", "Bearer " + token);
        }
        return headers;
    }

    public String getToken() {
        return token;
    }
}
