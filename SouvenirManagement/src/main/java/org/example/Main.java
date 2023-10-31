package org.example;

import java.time.LocalDate;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        SouvenirManager manager = new SouvenirManager();
        Scanner scanner = new Scanner(System.in);

        manager.loadSouvenirsFromJson();
        manager.loadManufacturersFromJson();
        while (true) {
            System.out.println("Select an action:");
            System.out.println("1. Add a souvenir");
            System.out.println("2. Add a manufacturer");
            System.out.println("3. View all manufacturers and souvenirs");
            System.out.println("4. View souvenirs by manufacturer");
            System.out.println("5. View souvenirs by country");
            System.out.println("6. View manufacturers by price");
            System.out.println("7. View manufacturers by souvenir and release year");
            System.out.println("8. View souvenirs sorted by release year");
            System.out.println("9. Delete a manufacturer and related souvenirs");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    // Add a souvenir
                    System.out.print("Souvenir name: ");
                    String name = scanner.nextLine();
                    System.out.print("Manufacturer name: ");
                    String manufacturerName = scanner.nextLine();
                    System.out.print("Manufacturer country: ");
                    String manufacturerCountry = scanner.nextLine();
                    Manufacturer manufacturer = new Manufacturer(manufacturerName, manufacturerCountry);
                    System.out.print("Release date (yyyy-MM-dd): ");
                    String releaseDateStr = scanner.nextLine();
                    LocalDate releaseDate = LocalDate.parse(releaseDateStr);
                    System.out.print("Price: ");
                    double price = scanner.nextDouble();
                    manager.addSouvenir(name, manufacturer, releaseDate, price);
                    break;
                case 2:
                    // Add a manufacturer
                    System.out.print("Manufacturer name: ");
                    manufacturerName = scanner.nextLine();
                    System.out.print("Manufacturer country: ");
                    manufacturerCountry = scanner.nextLine();
                    manager.addManufacturer(manufacturerName, manufacturerCountry);
                    break;
                case 3:
                    manager.viewAll();
                    break;
                case 4:
                    System.out.print("Enter manufacturer name: ");
                    manufacturerName = scanner.nextLine();
                    manager.viewSouvenirsByManufacturer(manufacturerName);
                    break;
                case 5:
                    System.out.print("Enter country: ");
                    String country = scanner.nextLine();
                    manager.viewSouvenirsByCountry(country);
                    break;
                case 6:
                    System.out.print("Enter maximum price: ");
                    double maxPrice = scanner.nextDouble();
                    manager.viewManufacturersByPrice(maxPrice);
                    break;
                case 7:
                    System.out.print("Enter souvenir name: ");
                    String souvenirName = scanner.nextLine();
                    System.out.print("Enter release year: ");
                    int year = scanner.nextInt();
                    manager.viewManufacturersBySouvenirAndYear(souvenirName, year);
                    break;
                case 8:
                    manager.viewSouvenirsSortedByYear();
                    break;
                case 9:
                    System.out.print("Enter manufacturer name: ");
                    manufacturerName = scanner.nextLine();
                    manager.deleteManufacturerAndSouvenirs(manufacturerName);
                    break;
                case 0:
                    System.out.println("Exiting the application.");
                    manager.saveSouvenirsToJson();
                    manager.saveManufacturersToJson();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }
}
