package org.example;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.Scanner;

import static org.example.SearchAllCustomers.getAllCustomers;
import static org.example.SearchCustomers.searchCustomer;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in, "UTF-8");

        writeMenuOptions();

        String input = sc.nextLine();
        if (input == null) {
            System.out.println("Špatná volba.");
            return;
        }

        try {
            while (!input.equals("4")) {
                switch (input) {
                    case "1" -> addCustomer(sc);
                    case "2" -> getAllCustomers(sc);
                    case "3" -> searchCustomer(sc);
                    default -> {
                        System.out.println("Špatná volba: " + input);
                        return;
                    }
                }

                input = sc.nextLine();
                if (input == null) {
                    System.out.println("Špatná volba.");
                    return;
                }
            }
        } catch (Exception e) {
            sc.close();
            System.out.println(e.getMessage());
        }

        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1 -> {
                String name = getAndValidateName(sc);
                String surname = getAndValidateSurname(sc);
                String phoneNumber = getAndValidatePhoneNumber(sc);
                int age = getAndValidateAge(sc);
                Customer addCustomers = new Customer(name, surname, phoneNumber, age);
            }
            case 2 -> {
                SearchAllCustomers searchCAllustomers = new SearchAllCustomers();
            }
            case 3 -> {
                SearchCustomers searchCustomers = new SearchCustomers();
            }
            case 4 -> System.out.println("Děkuji za použití našeho registračního portálu :)");
        }

    }

    private static void writeMenuOptions() {
        System.out.println("-------------------------");
        System.out.println("Evidence pojištěných");
        System.out.println("-------------------------");

        System.out.println("Vyberte si akci: ");
        System.out.println("1 - Přidat nového pojištěného");
        System.out.println("2 - Vypsat všechny pojištěné");
        System.out.println("3 - Vyhledat pojištěného");
        System.out.println("4 - Konec");
    }


    private static void addCustomer(Scanner sc) throws SQLException {
        System.out.println("Zadejte jméno: ");
        String name = getAndValidateName(sc);

        System.out.println("Zadejte příjmení: ");
        String surname = getAndValidateSurname(sc);

        System.out.println("Zadejte telefonní číslo ve formátu +420123456789: ");
        String phoneNumber = getAndValidatePhoneNumber(sc);

        System.out.println("Zadejte věk: ");
        int age = getAndValidateAge(sc);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/customers", "root", "");
        PreparedStatement query;
        try {
            query = connection.prepareStatement("INSERT INTO customer (name, surname, phoneNumber, age) VALUES (?, ?, ?, ?)");
            query.setString(1, name);
            query.setString(2, surname);
            query.setString(3, phoneNumber);
            query.setInt(4, age);
            query.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Pojištěnec úspěšně přidán!");
    }

    private static @NotNull String getAndValidateSurname(@NotNull Scanner sc) {
        String surname = sc.nextLine();

        if (surname == null || surname.length() > 55) {
            throw new IllegalStateException("Špatná volba! Zadejte příjmení znovu.");
        }

        return surname;
    }

    private static @NotNull String getAndValidatePhoneNumber(@NotNull Scanner sc) {
        String phoneNumber = sc.nextLine();

        if (phoneNumber == null || phoneNumber.length() > 13 ) {
            throw new IllegalStateException("Špatná volba! Zadejte telefonní číslo znovu.");
        }

        return phoneNumber;
    }

    private static @NotNull String getAndValidateName(@NotNull Scanner sc) {
        String name = sc.nextLine();

        if (name == null || name.length() > 55) {
            throw new IllegalStateException("špatná volba! Zadejte jméno znovu.");
        }

        return name;
    }

    private static int getAndValidateAge(@org.jetbrains.annotations.NotNull Scanner sc) {
        String ageStr = sc.nextLine();

        if (!StringUtils.isNumeric(ageStr)) {
            throw new IllegalStateException("špatná volba! Zadejte číslo znovu.");
        }

        int age = Integer.parseInt(ageStr);

        if (age < 0 || age > 150) {
            throw new IllegalStateException("Špatná volba! Věk musí být v rozmezí 0 - 150.");
        }

        return age;
    }
}

