package com.example.tests;

    import com.example.api.ApiClient;
    import com.example.api.AuthManager;
    import org.json.JSONObject;
    import org.testng.Assert;
    import org.testng.annotations.BeforeClass;
    import org.testng.annotations.Test;

    public class ExampleApiTest {
        private static ApiClient apiClient;
        private static AuthManager authManager;

        @BeforeClass
        public void setup() {
            String baseUrl = "https://reqres.in";
            authManager = new AuthManager(baseUrl);
            try {
                // reqres.in test credentials
                authManager.authenticate("eve.holt@reqres.in", "cityslicka");
            } catch (Exception e) {
                throw new RuntimeException("Authentication failed", e);
            }
            apiClient = new ApiClient(baseUrl, authManager);
        }

        @Test
        public void testAuthentication() {
            String token = authManager.getToken();
            Assert.assertNotNull(token);
            Assert.assertFalse(token.isEmpty());
        }

        @Test
        public void testGetUserWithOkHttp() throws Exception {
            ApiClient.ApiResponse response = apiClient.getUserWithOkHttp(2); // user id 2 exists
            Assert.assertEquals(response.statusCode, 200);
            JSONObject json = new JSONObject(response.body);
            Assert.assertEquals(json.getJSONObject("data").getString("email"), "janet.weaver@reqres.in");
        }
    }