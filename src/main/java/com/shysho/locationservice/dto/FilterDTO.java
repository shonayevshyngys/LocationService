package com.shysho.locationservice.dto;

import com.shysho.locationservice.domain.Type;
import lombok.Data;

@Data
public class FilterDTO {
    private String p1;
    private String p2;
    private Type type;
    private int limit;
}
