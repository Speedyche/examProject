package org.example;

import java.sql.*;
public class SearchAllCustomers {
    private Connection connection;

    public void DatabaseReader(String name, String surname, String phoneNumber, int age) throws SQLException {
        connection = DriverManager.getConnection(name);
    }

    private void getConnection(String name, String surname, String phoneNumber, int age) {
    }

    public void getAllRecords(String customers) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM " + customers;
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            // Retrieve the values from the table
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String phoneNumber = resultSet.getString("phoneNumber");
            int age = resultSet.getInt("age");

            System.out.println("Name: " + name + ", Surname: " + surname + ", Phone Number: " + phoneNumber + ", Age: " + age);
        }

        resultSet.close();
        statement.close();
    }

    public void close() throws SQLException {
        connection.close();
    }


    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/customers";
        String username = "root";
        String password = "";
        String tableName = "customers";

        try {
            DatabaseReader reader = new DatabaseReader(url, username, password);
            reader.getAllRecords();
            reader.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

