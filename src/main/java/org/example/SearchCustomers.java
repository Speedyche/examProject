package org.example;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchCustomers {
    private Connection connection;

    public void searchCustomer(String tableName, String name, String surname) throws SQLException {
        String query = "SELECT * FROM " + tableName + " WHERE name = ? AND surname = ?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, name);
        statement.setString(2, surname);

        ResultSet resultSet = statement.executeQuery();

        // Process the result set
        while (resultSet.next()) {
            String customerName = resultSet.getString("name");
            String customerSurname = resultSet.getString("surname");
            // Add more columns as per your table structure

            System.out.println("Name: " + customerName + ", Surname: " + customerSurname);
        }

        // Close resources
        resultSet.close();
        statement.close();
    }

    public void close() throws SQLException {
        connection.close();
    }
}

