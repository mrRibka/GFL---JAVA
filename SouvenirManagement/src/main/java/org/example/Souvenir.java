package org.example;
import java.time.LocalDate;

class Souvenir {

    private String name;

    private Manufacturer manufacturerDetails;

    private LocalDate releaseDate;

    private double price;

    public Souvenir(){}
    public Souvenir(String name, Manufacturer manufacturerDetails, LocalDate releaseDate, double price) {
        this.name = name;
        this.manufacturerDetails = manufacturerDetails;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Manufacturer getManufacturerDetails() {
        return manufacturerDetails;
    }

    public void setManufacturerDetails(Manufacturer manufacturerDetails) {
        this.manufacturerDetails = manufacturerDetails;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Souvenir{" +
                "name='" + name + '\'' +
                ", manufacturerDetails=" + manufacturerDetails.toString() +
                ", releaseDate=" + releaseDate +
                ", price=" + price +
                '}';
    }
}
