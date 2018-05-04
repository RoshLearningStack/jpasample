/*
 *  * This file is subject to the terms and conditions defined in
 *  * file 'LICENSE.txt', which is part of this source code package.
 */

package com.learn.persistance;

import com.learn.persistance.model.Book;
import com.learn.persistance.model.CD;
import com.learn.persistance.service.BookService;
import com.learn.persistance.service.CDService;

public class Application {

    public static void main(String[] args) {
        invokeBookService();
        invokeCDService();
    }

    private static void invokeBookService() {
        BookService bookService = new BookService();
        bookService.start();

        Book book = bookService.createBook("H2G2", "Scifi Book", 12.5f, "1234-4567-4657");
        System.out.println("Book Persisted : " + book);

        book = bookService.findBook(404L);
        System.out.println("Book found: " + book);

        bookService.raiseUnitCost(404L, 12.5f);
        System.out.println("Book updated: " + book);

        bookService.removeBook(404L);
        System.out.println("Book removed");

        book = bookService.findBook(404L);
        System.out.println("Book Not Found: " + book);

        bookService.stop();
    }

    private static void invokeCDService() {
        CDService cdService = new CDService();
        cdService.start();

        CD cd = cdService.createCD("Sinhala Rap", "2nd album", "Next Generation Rap", 12.5f, 100.0f);
        System.out.println("CD Persisted : " + cd);

        cd = cdService.findCD(404L);
        System.out.println("CD found: " + cd);

        cdService.removeCD(404L);
        System.out.println("CD removed");

        cd = cdService.findCD(404L);
        System.out.println("CD not found: " + cd);

        cdService.stop();
    }

}
