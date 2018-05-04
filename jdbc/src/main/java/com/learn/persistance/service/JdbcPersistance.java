package com.learn.persistance.service;

import com.learn.persistance.model.Book;

import java.sql.*;

public class JdbcPersistance {

    private static Connection CONNECTION = null;

    static {
        try {
            System.setProperty("derby.system.home", ".\\.derby");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void initDb() {
        CONNECTION = getConnection();
        createTable("BOOK");
    }

    private static Connection getConnection() {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:derby:.\\module01;create=true");
        } catch (SQLException e) {
            // NOP
        }
        return connection;

    }

    private static void createTable(String tableName) {
        try {
            Statement statement = CONNECTION.createStatement();
            DatabaseMetaData databaseMetadata = CONNECTION.getMetaData();
            ResultSet resultSet = databaseMetadata.getTables(null, null, tableName, null);
            if (resultSet.next()) {
                System.out.println("TABLE " + tableName + " ALREADY EXISTS");
            } else {
                statement.execute("CREATE TABLE " + tableName + "(ID INT, TITLE VARCHAR(20), DESCRIPTION VARCHAR(20), UNITCOST VARCHAR(20), ISBN VARCHAR(20))");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void persistBook(Book book) {
        String query = "INSERT INTO BOOK (ID, TITLE, DESCRIPTION, UNITCOST, ISBN) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setLong(1, book.getId());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getDescription());
            statement.setFloat(4, book.getUnitCost());
            statement.setString(5, book.getIsbn());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Book findBook(Long id) {
        Book book = new Book();
        book.setId(id);

        String query = "SELECT * FROM BOOK WHERE ID = ?";

        try {
            PreparedStatement statement = CONNECTION.prepareStatement(query);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                book.setTitle(resultSet.getString("TITLE"));
                book.setDescription(resultSet.getString("DESCRIPTION"));
                book.setUnitCost(resultSet.getFloat("UNITCOST"));
                book.setIsbn(resultSet.getString("ISBN"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

}