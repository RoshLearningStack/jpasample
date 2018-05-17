package com.learn.persistance.service;

import com.learn.persistance.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QueryService extends BaseService {

    private static final Logger LOGGER = Logger.getLogger(QueryService.class.getName());

    public QueryService(EntityManager entityManager, EntityTransaction transaction) {
        super(entityManager, transaction);
    }

    public List<Book> queryBooks() {
        List<Book> result = Collections.EMPTY_LIST;
        try {
            Query query = entityManager.createQuery("SELECT b from Book b where b.id=910");
            List books = query.getResultList();

            result = new ArrayList<Book>();
            for (Object object : books) {
                if (object instanceof Book) {
                    Book book = (Book) object;
                    result.add(book);
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            transaction.rollback();
        }
        return result;
    }

    public List<Book> queryBooksTyped() {
        List<Book> result = Collections.emptyList();
        try {
            Query query = entityManager.createNamedQuery(Book.FIND_ALL, Book.class);
            result = query.getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            transaction.rollback();
        }
        return result;
    }

}