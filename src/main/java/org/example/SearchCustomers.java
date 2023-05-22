package org.example;


import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.Scanner;

public class SearchCustomers {
    private Connection connection;

    public static void searchCustomer(@NotNull Scanner sc) throws SQLException {
        System.out.println("Zadejte jméno nebo příjmení pojištěnce: ");
        String searchQuery = sc.nextLine();

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/customers", "root", "");
        PreparedStatement query;
        try {
            query = connection.prepareStatement("SELECT * FROM customer WHERE name LIKE ? OR surname LIKE ?");
            query.setString(1, "%" + searchQuery + "%");
            query.setString(2, "%" + searchQuery + "%");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ResultSet result = query.executeQuery();

        if (result.next()) {
            System.out.println("Nalezené shody v pojištěncích: ");
            do {
                String name = result.getString("name");
                String surname = result.getString("surname");
                String phoneNumber = result.getString("phoneNumber");
                int age = result.getInt("age");
                System.out.println(MessageFormat.format("Jméno: {0}\nPříjmení: {1}\nTelefon: {2}\nVěk: {3}\n", name, surname, phoneNumber, age));
            } while (result.next());
        } else {
            System.out.println("Nebyly nalezeny žádné záznamy.");
        }
    }

    public void close() throws SQLException {
        connection.close();
    }
}

