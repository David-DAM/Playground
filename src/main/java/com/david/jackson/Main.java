package com.david.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws IOException {

        String PATH_FILE = "src/main/java/com/david/jackson/products_locations.json";

        Faker faker = new Faker(Locale.forLanguageTag("es-ES"));

        FeatureCollection featureCollection = new FeatureCollection();

        List<Feature> featureList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {

            Double latitude = Double.parseDouble(faker.address().latitude().replaceAll(",", "."));

            Double longitude = Double.parseDouble(faker.address().longitude().replaceAll(",", "."));

            Feature feature = new Feature();

            Geometry geometry = new Geometry();

            geometry.setCoordinates(List.of(latitude, longitude));

            feature.setGeometry(geometry);

            feature.setProperties(new ArrayList<>());

            featureList.add(feature);
        }

        featureCollection.setFeatures(featureList);

        ObjectMapper objectMapper = new ObjectMapper();

        String geoJson = objectMapper.writeValueAsString(featureCollection);

        Path file = Paths.get(PATH_FILE);

        if (!Files.exists(file)) Files.createFile(file);

        byte[] bytes = geoJson.getBytes();

        Files.write(file, bytes, StandardOpenOption.CREATE);

        System.out.println("Geojson file created successfully");

    }
}
