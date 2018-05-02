package com.learn.persistance;

import com.learn.persistance.jdbc.JdbcPersistance;
import com.learn.persistance.model.Book;

public class Application {

    public static void main(String[] args) {
        JdbcPersistance.initDb();
        JdbcPersistance.persistBook(new Book(1L, "H2G2", "Best Scifi Book", 12.5f, "1234-5678-5678"));
        Book book = JdbcPersistance.findBook(1L);
        System.out.println(book.toString());
    }

}