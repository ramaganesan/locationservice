package com.udacity.locationservice.service;

import com.udacity.locationservice.entity.Location;
import com.udacity.locationservice.exception.GraphqlLocationNotFoundException;
import com.udacity.locationservice.exception.LocationNotFoundException;
import com.udacity.locationservice.repository.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public List<Location> getAllLocation(){
        List<Location> allLocations = new ArrayList<>();
        this.locationRepository.findAll().forEach(location -> {
            log.debug("Adding Location" + location);
            allLocations.add(location);
        });
        return allLocations;
    }

    public Optional<Location> getLocationByName(String name){
        Location location = this.locationRepository.findByName(name);
        Optional<Location> optionalLocation = Optional.ofNullable(location);
        return optionalLocation;
    }

    public Location addLocation(String name, String address){
        Location location = Location.builder().name(name).address(address).build();
        return this.locationRepository.save(location);
    }

    public Boolean deleteLocation(long id){
        Location location = this.locationRepository.findById(id);
        if(location != null){
            this.locationRepository.delete(location);
            return true;
        }else{
            return false;
        }
    }

    public Location updateLocation(String name, long id){
        Location location = this.locationRepository.findById(id);
        if(location != null){
            location.setName(name);
            return this.locationRepository.save(location);
        }else{
            throw new GraphqlLocationNotFoundException("Unable to find the location", id);
        }
    }
}
