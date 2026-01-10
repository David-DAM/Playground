package com.david.libraries.jackson.geojson;

import java.util.List;
import java.util.Objects;

public class Geometry {

    private final String type = "Point";
    private List<Double> coordinates;

    public Geometry() {
    }

    public String getType() {
        return type;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Geometry geometry = (Geometry) o;
        return Objects.equals(type, geometry.type) && Objects.equals(coordinates, geometry.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, coordinates);
    }

    @Override
    public String toString() {
        return "Geometry{" +
                "type='" + type + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
}
