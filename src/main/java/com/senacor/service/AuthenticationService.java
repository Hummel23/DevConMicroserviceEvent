package com.senacor.service;

import com.senacor.IPAddress;
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

    final String userUri = IPAddress.IPuser;
    RestTemplate restTemplate = new RestTemplate();


   public boolean isAuthenticatedUser(String tokenId) {
       ResponseEntity<String> response = restTemplate.getForEntity(userUri + "validateToken?tokenId=" + tokenId, String.class);
       if (response.getBody() != null) {
           return true;
       }else{
           return false;
       }
       /*System.out.println("this is the response body: " + response.getBody());
       System.out.println(response.getBody());
       return response.getBody();
*/
    }

    public String getUserId(String tokenId) {
        ResponseEntity<String> response = restTemplate.getForEntity(userUri + "getUserId/" + tokenId, String.class);
        System.out.println(response.getBody());
        return response.getBody();
    }
}
