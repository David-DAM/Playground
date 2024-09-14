package com.david.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws IOException {
        String PATH_FILE =  "src/main/java/com/david/jackson/products_locations.json";
        //Faker faker = new Faker(Locale.forLanguageTag("es-ES"));
        //faker.address().latitude()
        FeatureCollection featureCollection = new FeatureCollection();

        Feature featureSpain = new Feature();
        Geometry geometrySpain = new Geometry();
        geometrySpain.setCoordinates(List.of(-5.2263791347520225,40.366788123826325));
        featureSpain.setGeometry(geometrySpain);

        Feature featureFrance = new Feature();
        Geometry geometryFrance = new Geometry();
        geometryFrance.setCoordinates(List.of(2.885396264005152,47.563935417174235));
        featureFrance.setGeometry(geometryFrance);

        featureCollection.setFeatures(List.of(featureSpain,featureFrance));

        ObjectMapper objectMapper = new ObjectMapper();
        String geoJson = objectMapper.writeValueAsString(featureCollection);

        Path file = Paths.get(PATH_FILE);

        if(!Files.exists(file)) Files.createFile(file);

        byte[] bytes = geoJson.getBytes();

        Files.write(file, bytes, StandardOpenOption.CREATE);

    }
}
