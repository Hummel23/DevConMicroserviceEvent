package com.senacor.service;

import com.senacor.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by saba on 11.11.16.
 */

@Service
public class UserService {

    final String userUri = "http://localhost:8081/user/";
    RestTemplate restTemplate = new RestTemplate();


    public ResponseEntity<User> authenticateUser(User user) {
        System.out.println("authenticating user");
        HttpEntity<User> entity = new HttpEntity<>(user);
        user = null;
        ResponseEntity<User> response = new ResponseEntity<>(user, HttpStatus.UNAUTHORIZED);
        try{
            System.out.println("in try catch block");
            response =restTemplate.exchange(userUri + "auth", HttpMethod.POST, entity, User.class);
            System.out.println(response.toString());
            System.out.println("Response: " + response.getStatusCodeValue());
            return response;
        }catch(Exception e){
            e.printStackTrace();
        }
        return response;

    }
}
