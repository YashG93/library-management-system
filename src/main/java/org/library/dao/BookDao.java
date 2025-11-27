package org.library.dao;

import org.library.model.Book;
import java.util.List;

public interface BookDao {
    void addBook(Book book);
    void removeBook(int bookId);
    List<Book> getAllBooks();
    Book getBookById(int bookId);
    void updateBookQuantity(int bookId, int quantity);
}
