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
public class UserService {

    final String userUri = "http://localhost:8081/";
    RestTemplate restTemplate = new RestTemplate();


    public boolean authenticateUser(User user) {

        HttpEntity<User> entity = new HttpEntity<User>(user);
        try{

            ResponseEntity<String> response =restTemplate.exchange(userUri + "auth", HttpMethod.POST, entity, String.class);
            System.out.println("Response: " + response.getStatusCodeValue());
        }catch(Exception e){
            return false;
        }
        return true;
    }
}
