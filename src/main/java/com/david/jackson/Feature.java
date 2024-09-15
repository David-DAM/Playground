package com.david.jackson;

import java.util.List;
import java.util.Objects;

public class Feature {

    private final String type = "Feature";
    private List<String> properties;
    private Geometry geometry;

    public Feature() {
    }

    public String getType() {
        return type;
    }

    public List<String> getProperties() {
        return properties;
    }

    public void setProperties(List<String> properties) {
        this.properties = properties;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feature feature = (Feature) o;
        return Objects.equals(type, feature.type) && Objects.equals(properties, feature.properties) && Objects.equals(geometry, feature.geometry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, properties, geometry);
    }

    @Override
    public String toString() {
        return "Feature{" +
                "type='" + type + '\'' +
                ", properties=" + properties +
                ", geometry=" + geometry +
                '}';
    }
}
