package com.udacity.locationservice.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.udacity.locationservice.entity.Location;
import com.udacity.locationservice.service.LocationService;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {
   private LocationService locationService;

   public Mutation(LocationService locationService){
       this.locationService = locationService;
   }

   public Location newLocation(String name, String address){
       return this.locationService.addLocation(name,address);
   }

   public Boolean deleteLocation(Long id){
       return this.locationService.deleteLocation(id);
   }

   public Location updateLocation(String name, Long id){
       return this.locationService.updateLocation(name,id);
   }
}
