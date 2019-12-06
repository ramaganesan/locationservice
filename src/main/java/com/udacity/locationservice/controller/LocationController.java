package com.udacity.locationservice.controller;

import com.udacity.locationservice.entity.Location;
import com.udacity.locationservice.exception.LocationNotFoundException;
import com.udacity.locationservice.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/locations")
    public ResponseEntity<List<Location>> getAllLocation(){
        List<Location> locations = this.locationService.getAllLocation();
        return new ResponseEntity<>(locations,HttpStatus.OK);

    }


    @GetMapping("/locations/{name}")
    public ResponseEntity<Location> getLocationByName(@PathVariable(required = true) String name){

        Optional<Location> optionalLocation = this.locationService.getLocationByName(name);
        Location location = optionalLocation.orElseThrow(LocationNotFoundException::new);
        return new ResponseEntity<>(location,HttpStatus.OK);

    }
}
