/*
 *  * This file is subject to the terms and conditions defined in
 *  * file 'LICENSE.txt', which is part of this source code package.
 */

package com.learn.persistance;

import com.learn.persistance.model.Author;
import com.learn.persistance.model.Book;
import com.learn.persistance.model.CD;
import com.learn.persistance.model.Musician;
import com.learn.persistance.service.AuthorService;
import com.learn.persistance.service.BookService;
import com.learn.persistance.service.CDService;
import com.learn.persistance.service.QueryService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transaction;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application {

    private static Logger LOGGER = Logger.getLogger(Application.class.getName());

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction transaction;

    public static void main(String[] args) {

        // set handler for uncaught exceptions
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread t, Throwable e) {
                LOGGER.log(Level.SEVERE, e.getMessage());
            }
        });

        // call application services
        Application application = new Application();
//        application.invokeBookService();
//      application.invokeCDService();
//        application.invokeQueryService();
        application.invokeAuthorService();

        application.exit();
    }

    public Application() {
        entityManagerFactory = Persistence.createEntityManagerFactory("myPU");
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
    }

    private void invokeBookService() {
        BookService bookService = new BookService(entityManager, transaction);
        Book book = bookService.createBook(new Book("H2G2", "Scifi Book", 12.5f, "1234-4567-4657", 454, new Date()));
        LOGGER.log(Level.INFO, "Book Persisted : " + book);

//        book = bookService.findBook(book.getId());
//        System.out.println("Book found: " + book);
    }

    private void invokeCDService() {
        CDService cdService = new CDService(entityManager, transaction);
        Set<Musician> beatles = new HashSet<Musician>();
        beatles.add(new Musician("John", "Lennon", "a", new Date(), "Gitar"));
        beatles.add(new Musician("Paul", "McCartney", "a", new Date(), "Gitar"));
        beatles.add(new Musician("Ringo", "Starr", "a", new Date(), "Gitar"));
        beatles.add(new Musician("Georges", "Harrison", "a", new Date(), "Gitar"));
        CD sergentPepper = new CD("Sergent", "New Album", 12.3f, 60f, "A");
        sergentPepper.setMusicians(beatles);

        CD cd = cdService.createCD(sergentPepper);

        LOGGER.log(Level.INFO, "CD Persisted : " + cd);

        cd = cdService.findCD(cd.getId());
        LOGGER.log(Level.INFO, "CD found : " + cd);
    }

    private void invokeQueryService() {
        QueryService queryService = new QueryService(entityManager, transaction);
        List<Book> bookList = queryService.queryBooksTyped();
        for (Book book : bookList) {
            LOGGER.log(Level.INFO, book.toString());
        }
    }

    private void invokeAuthorService() {
        AuthorService authorService = new AuthorService(entityManager, transaction);
//        Author author = authorService.createAuthor(new Author(null, null, null, null));
        Author author = authorService.createAuthor(new Author("Roshan", "Deniyage", new Date(81, 5, 23), "En"));

        // query again
        Author myAuthor = authorService.findAuthor(author.getId());
        LOGGER.log(Level.INFO, myAuthor.toString());
    }

    private void exit() {
        if(entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

}