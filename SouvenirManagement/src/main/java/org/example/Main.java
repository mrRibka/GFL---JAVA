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
            System.out.println("1. Add manufacturer");
            System.out.println("2. Add souvenir");
            System.out.println("3. View all manufacturers");
            System.out.println("4. View all souvenirs");
            System.out.println("5. Edit manufacturer");
            System.out.println("6. Edit souvenir");
            System.out.println("7. View all manufacturers and their souvenirs");
            System.out.println("8. View souvenirs by manufacturer");
            System.out.println("9. View souvenirs by country");
            System.out.println("10. View manufacturers by price");
            System.out.println("11. View manufacturers by souvenir and release year");
            System.out.println("12. View souvenirs sorted by release year");
            System.out.println("13. Delete manufacturer and related souvenirs");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Manufacturer name: ");
                    String manufacturerName = scanner.nextLine();
                    System.out.print("Manufacturer country: ");
                    String manufacturerCountry = scanner.nextLine();
                    manager.addManufacturer(manufacturerName, manufacturerCountry);
                    break;
                case 2:
                    System.out.print("Souvenir name: ");
                    String name = scanner.nextLine();

                    manager.viewAllManufacturers();
                    System.out.print("Enter the index of the manufacturer you want to use: ");
                    int manufacturerIndex = scanner.nextInt();

                    if (manufacturerIndex >= 0 && manufacturerIndex < manager.getManufacturers().size()) {
                        Manufacturer selectedManufacturer = manager.getManufacturers().get(manufacturerIndex);

                        System.out.print("Release date (yyyy-MM-dd): ");
                        scanner.nextLine();
                        String releaseDateStr = scanner.nextLine();
                        LocalDate releaseDate = LocalDate.parse(releaseDateStr);
                        System.out.print("Price: ");
                        double price = scanner.nextDouble();

                        manager.addSouvenir(name, selectedManufacturer, releaseDate, price);
                    } else {
                        System.out.println("Invalid manufacturer index. Souvenir not added.");
                    }
                    break;
                case 3:
                    manager.viewAllManufacturers();
                    break;
                case 4:
                    manager.viewAllSouvenirs();
                    break;
                case 5:
                    System.out.print("Enter the manufacturer index to edit: ");
                    int manufacturerIndexToEdit = scanner.nextInt();
                    scanner.nextLine();

                    if (manufacturerIndexToEdit >= 0 && manufacturerIndexToEdit < manager.getManufacturers().size()) {
                        Manufacturer oldManufacturer = manager.getManufacturers().get(manufacturerIndexToEdit);

                        System.out.print("New manufacturer name: ");
                        String newManufacturerName = scanner.nextLine();
                        System.out.print("New manufacturer country: ");
                        String newManufacturerCountry = scanner.nextLine();

                        manager.updateManufacturerInSouvenirs(oldManufacturer, new Manufacturer(newManufacturerName, newManufacturerCountry));
                        manager.updateManufacturer(manufacturerIndexToEdit, newManufacturerName, newManufacturerCountry);
                    } else {
                        System.out.println("Invalid manufacturer index. Manufacturer not updated.");
                    }
                    break;
                case 6:
                    System.out.print("Enter the souvenir index to edit: ");
                    int souvenirIndexToEdit = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("New souvenir name: ");
                    String newSouvenirName = scanner.nextLine();

                    manager.viewAllManufacturers();
                    System.out.print("Enter the index of the manufacturer you want to use: ");
                    int newManufacturerIndex = scanner.nextInt();

                    if (newManufacturerIndex >= 0 && newManufacturerIndex < manager.getManufacturers().size()) {
                        Manufacturer selectedManufacturer = manager.getManufacturers().get(newManufacturerIndex);

                        System.out.print("New release date (yyyy-MM-dd): ");
                        scanner.nextLine();
                        String newReleaseDateStr = scanner.nextLine();
                        LocalDate newReleaseDate = LocalDate.parse(newReleaseDateStr);
                        System.out.print("New price: ");
                        double newPrice = scanner.nextDouble();

                        manager.updateSouvenir(souvenirIndexToEdit, newSouvenirName, selectedManufacturer, newReleaseDate, newPrice);
                    } else {
                        System.out.println("Invalid manufacturer index. Souvenir not updated.");
                    }
                    break;
                case 7:
                    manager.viewAll();
                    break;
                case 8:
                    manager.viewAllManufacturers();
                    System.out.print("Enter manufacturer name: ");
                    manufacturerName = scanner.nextLine();
                    manager.viewSouvenirsByManufacturer(manufacturerName);
                    break;
                case 9:
                    System.out.print("Enter country: ");
                    String country = scanner.nextLine();
                    manager.viewSouvenirsByCountry(country);
                    break;
                case 10:
                    System.out.print("Enter maximum price: ");
                    double maxPrice = scanner.nextDouble();
                    manager.viewManufacturersByPrice(maxPrice);
                    break;
                case 11:
                    System.out.print("Enter souvenir name: ");
                    String souvenirName = scanner.nextLine();
                    System.out.print("Enter release year: ");
                    int year = scanner.nextInt();
                    manager.viewManufacturersBySouvenirAndYear(souvenirName, year);
                    break;
                case 12:
                    manager.viewSouvenirsSortedByYear();
                    break;
                case 13:
                    manager.viewAllManufacturers();
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
