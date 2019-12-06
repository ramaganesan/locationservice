package com.udacity.locationservice.exception;

import com.udacity.locationservice.entity.Location;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Location Not Found")
public class LocationNotFoundException extends RuntimeException {

    public LocationNotFoundException(){

    }

    public LocationNotFoundException(String message){
        super(message);
    }
}
