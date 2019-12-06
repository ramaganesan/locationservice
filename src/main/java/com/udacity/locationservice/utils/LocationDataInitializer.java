package com.udacity.locationservice.utils;

import com.udacity.locationservice.entity.Location;
import com.udacity.locationservice.repository.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class LocationDataInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final LocationRepository locationRepository;

    public LocationDataInitializer(LocationRepository locationRepository){
        this.locationRepository = locationRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        log.info("Initializing Location Data");
        String[] names = {"IAD", "MCO", "BWI","DCA"};
        Arrays.stream(names).forEach(s -> {
            locationRepository.save(getLocation(s, s+"Airport address location"));
        });
    }

    private Location getLocation(String name, String address){
        Location location = Location.builder().name(name).address(address).build();
        return location;
    }
}
