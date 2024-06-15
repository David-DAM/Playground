package com.david.jackson;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        FeatureCollection featureCollection = new FeatureCollection();

        Feature featureSpain = new Feature();
        Geometry geometrySpain = new Geometry();
        geometrySpain.setCoordinates(List.of( -5.2263791347520225,40.366788123826325));
        featureSpain.setGeometry(geometrySpain);

        Feature featureFrance = new Feature();
        Geometry geometryFrance = new Geometry();
        geometryFrance.setCoordinates(List.of( -5.2263791347520225,40.366788123826325));
        featureFrance.setGeometry(geometryFrance);

        featureCollection.setFeatures(List.of(featureSpain,featureFrance));

        System.out.println(featureCollection);
    }
}
