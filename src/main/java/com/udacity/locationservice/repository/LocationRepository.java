package com.udacity.locationservice.repository;

import com.udacity.locationservice.entity.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends CrudRepository<Location,Long> {

    public Location findByName(String name);

    public Location findById(long id);
}
