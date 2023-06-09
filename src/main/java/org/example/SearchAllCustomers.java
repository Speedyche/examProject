package org.example;

import java.sql.*;
import java.util.Scanner;

public class SearchAllCustomers {
    private Connection connection;


    public static void getAllCustomers(Scanner sc) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/customers", "root", "");
        PreparedStatement querry;
        try {
            querry = connection.prepareStatement("SELECT * FROM  customer");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet outcome = querry.executeQuery();

        while (outcome.next()) {
            String name = outcome.getString("name");
            String surname = outcome.getString("surname");
            String phoneNumber = outcome.getString("phoneNumber");
            int age = outcome.getInt("age");

            System.out.println(MessageFormat.format("Jméno: {0}\nPříjmení: {1}\nTelefon: {2}\nVěk: {3}\n", name, surname, phoneNumber, age));
        }
    }

    public void close() throws SQLException {
        connection.close();
    }

}

