package com.shysho.locationservice.service.crud;

import com.shysho.locationservice.domain.Coordinates;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoordinatesRepository extends CrudRepository<Coordinates, Long>, JpaSpecificationExecutor<Coordinates> {
}
