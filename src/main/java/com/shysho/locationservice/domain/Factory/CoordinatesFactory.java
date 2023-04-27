package com.shysho.locationservice.domain.Factory;

import com.shysho.locationservice.domain.Coordinates;
import com.shysho.locationservice.domain.Point;
import com.shysho.locationservice.domain.Type;
import com.shysho.locationservice.dto.CoordinatesDTO;
import org.springframework.stereotype.Service;


@Service
public class CoordinatesFactory {

    public Coordinates createCoordinates(double longitude, double latitude)
    {
        if (longitude < -180d || longitude > 180d) throw new IllegalArgumentException("longitude can be only in range of [-180:180]");

        if (latitude < -90d || latitude > 90d) throw new IllegalArgumentException("latitude can be in range of [-90:90]");

        Coordinates coordinates = new Coordinates();
        coordinates.setLatitude( latitude );
        coordinates.setLongitude( longitude );
        return coordinates;
    }

    public Coordinates createCoordinatesFromDTO(CoordinatesDTO dto)
    {
        Coordinates coordinates = createCoordinates(dto.getLongitude(), dto.getLatitude());
        if (dto.getType() == null) coordinates.setType(Type.Standard);
        else coordinates.setType(dto.getType());
        coordinates.setName(dto.getName());
        return coordinates;
    }

    public Point createPointFromString(String pointsString)
    {
        if (!pointsString.contains(",")) throw new IllegalArgumentException("You should provide exactly two values for a given point");
        String[] splited = pointsString.split(",");
        if (splited.length != 2) throw new IllegalArgumentException("You should provide exactly two values for a given point");
        Point p = new Point();
        double longitude = Double.parseDouble(splited[1].trim());
        double latitude = Double.parseDouble(splited[0].trim());
        if (longitude < -180d || longitude > 180d) throw new IllegalArgumentException("longitude can be only in range of [-180:180]");
        if (latitude < -90d || latitude > 90d) throw new IllegalArgumentException("latitude can be in range of [-90:90]");
        p.setLatitude(latitude);
        p.setLongitude(longitude);

        return p;
    }
}
