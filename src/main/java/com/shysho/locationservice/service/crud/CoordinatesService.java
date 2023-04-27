package com.shysho.locationservice.service.crud;

import com.shysho.locationservice.domain.Coordinates;
import com.shysho.locationservice.domain.Factory.CoordinatesFactory;
import com.shysho.locationservice.domain.Point;
import com.shysho.locationservice.domain.Type;
import com.shysho.locationservice.dto.CoordinatesDTO;
import com.shysho.locationservice.dto.FilterDTO;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoordinatesService {

    @Autowired
    private CoordinatesRepository repository;

    @Autowired
    private CoordinatesFactory factory;

    public ResponseEntity<Coordinates> save(CoordinatesDTO coordinates)
    {
        return new ResponseEntity<>(repository.save(factory.createCoordinatesFromDTO(coordinates)), HttpStatus.CREATED);
    }

    public ResponseEntity<List<Coordinates>> getAll()
    {
        List<Coordinates> allCoordinates = new ArrayList<>();

        repository.findAll().forEach(allCoordinates::add);

        return new ResponseEntity<>(allCoordinates, HttpStatus.OK);
    }

    public ResponseEntity<List<Coordinates>> getAllInBetweenTwoPoints(
           Optional<String> p1,
           Optional<String> p2,
           Optional<Type> type,
           Pageable pageable
    )
    {
        List<Coordinates> allCoordinates = repository.findAll((Specification<Coordinates>) (root, cq, cb) -> {
            Predicate p = cb.conjunction(); //it's a criteria predication , not Java util
            if (p1.isPresent() && p2.isPresent()) {
                Point point1 = factory.createPointFromString(p1.get());
                Point point2 = factory.createPointFromString(p2.get());
                p = cb.and(p, cb.between(root.get("latitude"), point1.getLatitude(), point2.getLatitude()));
            }
            if (type.isPresent()) {
                p = cb.and(p, cb.equal(root.get("type"), type.get()));
            }
            cq.orderBy(cb.asc(root.get("type")));

            return p;
        }, pageable).stream().toList();


        return new ResponseEntity<>(allCoordinates, HttpStatus.OK);
    }

}
