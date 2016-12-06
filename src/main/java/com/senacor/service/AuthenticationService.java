package com.senacor.service;

import com.senacor.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by saba on 11.11.16.
 */

@Service
public class AuthenticationService {

    final String userUri = "http://localhost:8081/user/";
    RestTemplate restTemplate = new RestTemplate();


   public boolean isAuthenticatedUser(String tokenId) {
       ResponseEntity<String> response = restTemplate.getForEntity(userUri + "validateToken?tokenId=" + tokenId, String.class);
       if (response.getBody().equals(tokenId)) {
           return true;
       }else {
           return false;
       }

    }
}
