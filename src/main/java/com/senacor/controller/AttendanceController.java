package com.senacor.controller;

import com.senacor.service.AttendanceService;
import com.senacor.service.AuthenticationService;
import com.senacor.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by saba on 13.12.16.
 */
@RestController
@RequestMapping("/events/{eventID}/attendees")
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final AuthenticationService authenticationService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService, AuthenticationService authenticationService){
        this.attendanceService = attendanceService;
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Boolean> getAttendeeStatus(@PathVariable("eventID") String eventID, @PathVariable("userId")String userId){

            return Collections.singletonMap("isAttending", attendanceService.getAttendeeStatus(eventID, userId));

    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addAttendeeToEvent(@PathVariable("eventID") String eventID, @PathVariable("userId")String userId, @RequestHeader("Authorization") String tokenId,
                                   HttpServletResponse response){
        if (authenticationService.isAuthenticatedUser(tokenId)) {
            attendanceService.updateAttendeesList(eventID, userId);
        }else{
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
