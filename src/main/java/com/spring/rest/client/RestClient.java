package com.spring.rest.client;


import com.spring.rest.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import java.util.List;

public class RestClient {

    private final RestTemplate restTemplate;
    private final String baseUrl = "http://94.198.50.185:7081/api/users";

    public RestClient() {
        this.restTemplate = new RestTemplate();
    }


    public String getUsers(Session session) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.GET, entity, String.class);


        List<String> cookies = response.getHeaders().get("Set-Cookie");
        if (cookies != null && !cookies.isEmpty()) {
            session.setSessionId(cookies.get(0).split(";")[0]);
        }

        return response.getBody();
    }


    public String addUser(User user, Session session) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", session.getSessionId());

        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.POST, entity, String.class);

        return response.getBody();
    }


    public String updateUser(User user, Session session) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", session.getSessionId());

        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.PUT, entity, String.class);

        return response.getBody();
    }


    public String deleteUser(Long id, Session session) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", session.getSessionId());

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(baseUrl + "/" + id, HttpMethod.DELETE, entity, String.class);

        return response.getBody();
    }


    public static class Session {
        private String sessionId;

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }
    }
}