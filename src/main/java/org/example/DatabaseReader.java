package org.example;

import java.sql.*;

public class DatabaseReader {

    private Connection connection;

    DatabaseReader(String url, String username, String password) throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
    }

    public void getAllRecords(String customers) throws SQLException {
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM " + customers;
        ResultSet resultSet = statement.executeQuery(query);

        // Process the result set
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String phoneNumber = resultSet.getString("phoneNumber");
            int age = resultSet.getInt("age");

            System.out.println("Name: " + name + ", Surname: " + surname + ", Phone Number: " + phoneNumber + ", Age: " + age);
        }

        resultSet.close();
        statement.close();
    }
    

    public void close() {
    }


    public void getAllRecords() {
    }

    public void searchCustomer(String tableName, String searchName, String searchSurname) {
    }
}
