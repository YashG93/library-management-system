package org.library.dao.impl;

import org.library.dao.BookDao;
import org.library.model.Book;
import org.library.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {

    @Override
    public void addBook(Book book) {
        String sql = "INSERT INTO books(title, author, category, quantity) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getCategory());
            stmt.setInt(4, book.getQuantity());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Error adding book: " + e.getMessage(), e);
        }
    }

    @Override
    public void removeBook(int bookId) {
        String sql = "DELETE FROM books WHERE id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("Book with ID " + bookId + " does not exist.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error removing book: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getInt("quantity")
                ));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching books: " + e.getMessage(), e);
        }
        return books;
    }

    @Override
    public Book getBookById(int bookId) {
        String sql = "SELECT * FROM books WHERE id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getInt("quantity")
                );
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching book: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void updateBookQuantity(int bookId, int quantity) {
        String sql = "UPDATE books SET quantity=? WHERE id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, bookId);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Error updating book quantity: " + e.getMessage(), e);
        }
    }
}
