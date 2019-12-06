package com.udacity.locationservice.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.udacity.locationservice.entity.Location;
import com.udacity.locationservice.exception.LocationNotFoundException;
import com.udacity.locationservice.service.LocationService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {

    private LocationService locationService;

    public Query(LocationService locationService){
        this.locationService = locationService;
    }

    public Iterable<Location> findAllLocation(){
       return this.locationService.getAllLocation();
    }

    public Location findLocationByName(String name){
        Optional<Location> optionalLocation = this.locationService.getLocationByName(name);
        Location location = optionalLocation.orElseThrow(LocationNotFoundException::new);
        return location;
    }

}
