package com.david.libraries.jackson.geojson;

import java.util.List;
import java.util.Objects;

public class FeatureCollection {

    private final String type = "FeatureCollection";
    private List<Feature> features;

    public FeatureCollection() {
    }

    public String getType() {
        return type;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeatureCollection that = (FeatureCollection) o;
        return Objects.equals(type, that.type) && Objects.equals(features, that.features);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, features);
    }

    @Override
    public String toString() {
        return "FeatureCollection{" +
                "type='" + type + '\'' +
                ", features=" + features +
                '}';
    }
}
