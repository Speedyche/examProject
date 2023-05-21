package org.example;

import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in, "Windows-1250");

        writeMenuOptions();

        String input = sc.nextLine();
        if (input == null) {
            System.out.println("Bad request.");
            return;
        }

        try {
            while (!input.equals("4")) {
                switch (input) {
                    case "1" -> addCustomer(sc);
                    case "2" -> getAllCustomers(sc);
                    case "3" -> searchCustomer(sc);
                    default -> {
                        System.out.println("Bad request: " + input);
                        return;
                    }
                }

                input = sc.nextLine();
                if (input == null) {
                    System.out.println("Bad request.");
                    return;
                }
            }
        } catch (Exception e) {
            sc.close();
            System.out.println(e.getMessage());
        }


        System.out.println("-------------------------");
        System.out.println("Evidence pojištěnců");
        System.out.println("-------------------------");

        System.out.println("Vyberte si akci: ");
        System.out.println("1 - Přidat nového pojištěnce");
        System.out.println("2 - Vypsat všechny pojištěnce");
        System.out.println("3 - Vyhledat pojištěnce");
        System.out.println("4 - Konec");
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1 -> {
                Customer addCustomers = new Customer();
            }
                case 2 -> {
                    SearchCustomers searchCustomers = new SearchCustomers();
                }
                case 3 -> {
                    SearchAllCustomers searchAllCustomers = new SearchAllCustomers();
                }
                case 4 -> System.out.println("Děkuji za použití našeho registračního portálu :)");
            }

        }

    private static void searchCustomer(Scanner sc) {

    }

    private static void getAllCustomers(Scanner sc) {

    }

    private static void writeMenuOptions() {
    }


    private static String addCustomer(Scanner sc) throws SQLException {
        System.out.println("Give me a name");
        String name = getAndValidateName(sc);

        System.out.println("Give me a surname");
        String surname = getAndValidateName(sc);

        System.out.println("Give me a phone number");
        String phoneNumber = getAndValidatePhoneNumber(sc);

        System.out.println("Give me an age");
        String age = getAndValidateAge(sc);

        Connection connection = DriverManager.getConnection("jdbc:mysql:localhost:3306/customers?user=root&password=");
        PreparedStatement querry = null;
        try {
            querry = connection.prepareStatement("SELECT * FROM customers");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet outcome = querry.executeQuery();

        while (outcome.next()) {

            name = outcome.getString("name");
            surname = outcome.getString("surname");
            phoneNumber = outcome.getString("phoneNumber");
            age = String.valueOf(outcome.getInt(1));

            System.out.println("Jméno: " + name + "/nPříjmení: " + surname + "/nTelefon: " + phoneNumber + "/nVěk: " + age);
        }


        private static String getAndValidateAge (Scanner sc){
            age = sc.nextLine();

            if (!StringUtils.isNumeric(age)) {
                throw new IllegalStateException("Bad request. Scope phoneNumber.");
            }

            return age;
        }

        public static String getAndValidatephoneNumber (Scanner sc){
            String phoneNumber = sc.nextLine();

            if (phoneNumber == null || phoneNumber.length() > 13 || !phoneNumber.startsWith("+")) {
                throw new IllegalStateException("Bad request. Scope phoneNumber.");
            }

            return phoneNumber;
        }

        private static String getAndValidateName (Scanner sc){
            String name = sc.nextLine();

            if (name == null || name.length() > 55) {
                throw new IllegalStateException("Bad request. Scope name/surname.");
            }

            return name;
        }

        private static String getAndValidateSurname (Scanner sc){
            String surname = sc.nextLine();

            if (name == null || surname.length() > 55) {
                throw new IllegalStateException("Bad request. Scope name/surname.");
            }

            return surname;
        }

        private static void writeMenuOptions () {
            System.out.println("-------------------------");
            System.out.println("Evidence pojištěných");
            System.out.println("-------------------------");

            System.out.println("Vyberte si akci: ");
            System.out.println("1 - Přidat nového pojištěného");
            System.out.println("2 - Vypsat všechny pojištěné");
            System.out.println("3 - Vyhledat pojištěného");
            System.out.println("4 - Konec");
        }


    }

    private static String getAndValidatePhoneNumber(Scanner sc) {
        return null;
    }

    private static String getAndValidateName(Scanner sc) {
        return null;
    }

    private static String getAndValidateAge(Scanner sc) {
        return null;
    }

    public static void {
        String url = "jdbc:mysql://localhost:3306/customers";
        String username = "root";
        String password = "";
        String tableName = "customers";
        String searchName = "John";
        String searchSurname = "Doe";

        try {
            DatabaseReader reader = new DatabaseReader(url, username, password);
            reader.searchCustomer(tableName, searchName, searchSurname);
            reader.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

