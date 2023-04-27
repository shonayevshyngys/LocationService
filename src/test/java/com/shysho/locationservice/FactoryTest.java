package com.shysho.locationservice;

import com.shysho.locationservice.domain.Coordinates;
import com.shysho.locationservice.domain.Factory.CoordinatesFactory;
import com.shysho.locationservice.domain.Point;
import com.shysho.locationservice.domain.Type;
import com.shysho.locationservice.dto.CoordinatesDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FactoryTest {

    private final CoordinatesFactory factory = new CoordinatesFactory();

    @Test
    public void outOfBoundValues()
    {
        assertAll(
                //long test
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    double lon = 190d;
                    double lat = 150d;
                    factory.createCoordinates(lon, lat);
                }),
                //lat test
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    double lon = 50d;
                    double lat = 150d;
                    factory.createCoordinates(lon, lat);
                }),
                //point creation
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    String test = "test";
                    factory.createPointFromString(test);
                }),
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    String test = "test,test";
                    factory.createPointFromString(test);
                }),
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    String test = "10.0,lat";
                    factory.createPointFromString(test);
                }),
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    String test = "190,160";
                    factory.createPointFromString(test);
                })
        );
    }

    @Test
    public void coordinatesCreatingTypeTest()
    {
        CoordinatesDTO dto = new CoordinatesDTO();
        dto.setLatitude(14);
        dto.setLongitude(20d);
        Coordinates coordinates = factory.createCoordinatesFromDTO(dto);
        assertEquals(Type.Standard, coordinates.getType());
    }

    @Test
    public void createPointFromString()
    {
        String test = "10.5,23.6";
        Point p = factory.createPointFromString(test);

        assertAll(
                () -> assertEquals(p.getLatitude(),10.5d),
                () -> assertEquals(p.getLongitude(), 23.6d)
        );
    }

}
