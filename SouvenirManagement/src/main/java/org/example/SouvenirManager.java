package org.example;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SouvenirManager {
    private List<Souvenir> souvenirs = new ArrayList<>();
    private List<Manufacturer> manufacturers = new ArrayList<>();
    private static final String SOUVENIRS_JSON_FILENAME = "souvenirs.json";
    private static final String MANUFACTURERS_JSON_FILENAME = "manufacturers.json";


    public void addSouvenir(String name, Manufacturer manufacturerDetails, LocalDate releaseDate, double price) {
        Souvenir souvenir = new Souvenir(name, manufacturerDetails, releaseDate, price);
        souvenirs.add(souvenir);
    }

    public void addManufacturer(String name, String country) {
        Manufacturer manufacturer = new Manufacturer(name, country);
        manufacturers.add(manufacturer);
    }

    public void viewAll() {
        manufacturers.forEach(manufacturer -> {
            System.out.println(manufacturer + ":");
            souvenirs.stream()
                    .filter(souvenir -> souvenir.getManufacturerDetails().equals(manufacturer))
                    .forEach(souvenir -> System.out.println("   " + souvenir));
        });
    }

    public void viewSouvenirsByManufacturer(String manufacturerName) {
        souvenirs.stream()
                .filter(souvenir -> souvenir.getManufacturerDetails().getName().equals(manufacturerName))
                .forEach(System.out::println);
    }

    public void viewSouvenirsByCountry(String country) {
        souvenirs.stream()
                .filter(souvenir -> souvenir.getManufacturerDetails().getCountry().equals(country))
                .forEach(System.out::println);
    }

    public void viewManufacturersByPrice(double maxPrice) {
        manufacturers.stream()
                .filter(manufacturer -> {
                    List<Souvenir> manufacturerSouvenirs = souvenirs.stream()
                            .filter(souvenir -> souvenir.getManufacturerDetails().getName().equals(manufacturer.getName()))
                            .toList();
                    return manufacturerSouvenirs.stream().anyMatch(souvenir -> souvenir.getPrice() < maxPrice);
                })
                .forEach(System.out::println);
    }

    public void viewManufacturersBySouvenirAndYear(String souvenirName, int year) {
        manufacturers.stream()
                .filter(manufacturer -> {
                    List<Souvenir> manufacturerSouvenirs = souvenirs.stream()
                            .filter(souvenir -> souvenir.getManufacturerDetails().getName().equals(manufacturer.getName()))
                            .toList();
                    return manufacturerSouvenirs.stream()
                            .anyMatch(souvenir -> souvenir.getName().equals(souvenirName) && souvenir.getReleaseDate().getYear() == year);
                })
                .forEach(System.out::println);
    }

    public void viewSouvenirsSortedByYear() {
        Map<Integer, List<Souvenir>> souvenirsByYear = new HashMap<>();
        for (Souvenir souvenir : souvenirs) {
            int year = souvenir.getReleaseDate().getYear();
            souvenirsByYear.computeIfAbsent(year, k -> new ArrayList<>()).add(souvenir);
        }

        for (Map.Entry<Integer, List<Souvenir>> entry : souvenirsByYear.entrySet()) {
            int year = entry.getKey();
            List<Souvenir> souvenirsInYear = entry.getValue();

            System.out.println("Souvenirs made in " + year + ":");
            for (Souvenir souvenir : souvenirsInYear) {
                System.out.println(souvenir);
            }
        }

    }

    public void deleteManufacturerAndSouvenirs(String manufacturerName) {
        manufacturers.removeIf(manufacturer -> manufacturer.getName().equals(manufacturerName));
        souvenirs.removeIf(souvenir -> souvenir.getManufacturerDetails().getName().equals(manufacturerName));
    }
    public void saveSouvenirsToJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.writeValue(new File(SOUVENIRS_JSON_FILENAME), souvenirs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadSouvenirsFromJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, Souvenir.class);
            souvenirs = mapper.readValue(new File(SOUVENIRS_JSON_FILENAME), collectionType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveManufacturersToJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.writeValue(new File(MANUFACTURERS_JSON_FILENAME), manufacturers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadManufacturersFromJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, Manufacturer.class);
            manufacturers = mapper.readValue(new File(MANUFACTURERS_JSON_FILENAME), collectionType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
