/*
 *  * This file is subject to the terms and conditions defined in
 *  * file 'LICENSE.txt', which is part of this source code package.
 */

package com.learn.persistance.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = Book.FIND_ALL, query = "select b from Book b")
})
public class Book extends Item {
    public static final String FIND_ALL = "Book.All";

    @Column(length = 15)
    private String isbn;

    @Column(name = "nb_of_pages")
    private Integer nbOfPages;

    @Column(name = "publication_date")
    @Temporal(TemporalType.DATE)
    private Date publicationDate;

    public Book() {
    }

    public Book(String title, String description, Float unitCost, String isbn, Integer nbOfPages, Date publicationDate) {
        super.setTitle(title);
        super.setDescription(description);
        super.setUnitCost(unitCost);
        this.isbn = isbn;
        this.nbOfPages = nbOfPages;
        this.publicationDate = publicationDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        if (!super.equals(o)) return false;

        Book book = (Book) o;

        if (getIsbn() != null ? !getIsbn().equals(book.getIsbn()) : book.getIsbn() != null) return false;
        if (nbOfPages != null ? !nbOfPages.equals(book.nbOfPages) : book.nbOfPages != null) return false;
        return publicationDate != null ? publicationDate.equals(book.publicationDate) : book.publicationDate == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getIsbn() != null ? getIsbn().hashCode() : 0);
        result = 31 * result + (nbOfPages != null ? nbOfPages.hashCode() : 0);
        result = 31 * result + (publicationDate != null ? publicationDate.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", nbOfPages=" + nbOfPages +
                ", publicationDate=" + publicationDate +
                "} " + super.toString();
    }
}