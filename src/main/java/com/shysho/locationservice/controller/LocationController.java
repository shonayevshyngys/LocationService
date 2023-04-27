package com.shysho.locationservice.controller;

import com.shysho.locationservice.domain.Coordinates;
import com.shysho.locationservice.domain.Type;
import com.shysho.locationservice.dto.CoordinatesDTO;

import com.shysho.locationservice.service.crud.CoordinatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
public class LocationController {

    @Autowired
    CoordinatesService service;

    @GetMapping("/location")
    ResponseEntity<List<Coordinates>> allLocations()
    {
        return service.getAll();
    }

    @PostMapping("/location")
    ResponseEntity<Coordinates> createCoordinates(@RequestBody CoordinatesDTO body)
    {
        return service.save(body);
    }

    @GetMapping("location/search")
    ResponseEntity<List<Coordinates>> getLocationByFilter(
            @RequestParam Optional<String> p1,
            @RequestParam Optional<String> p2,
            @RequestParam Optional<Type> type,
            Pageable pageable
    )
    {
        return service.getAllInBetweenTwoPoints(p1, p2, type, pageable);
    }

}
