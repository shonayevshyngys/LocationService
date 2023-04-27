package com.shysho.locationservice.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import com.shysho.locationservice.domain.Type;
import lombok.Data;

@Data
public class CoordinatesDTO {

    @JsonValue private String name;
    @JsonValue private double latitude;
    @JsonValue private double longitude;
    @JsonValue private Type type;

}
