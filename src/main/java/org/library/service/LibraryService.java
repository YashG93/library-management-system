package org.library.service;

import org.library.exception.BookNotFoundException;
import org.library.exception.NoCopiesAvailableException;
import org.library.model.Book;
import org.library.model.IssueRecord;
import org.library.model.Student;

import java.util.List;

public interface LibraryService {
    void addBook(Book book);
    void removeBook(int bookId) throws BookNotFoundException;
    List<Book> listAllBooks();
    void addStudent(Student student);
    List<Student> listAllStudents();
    void issueBook(int bookId, int studentId) throws BookNotFoundException, NoCopiesAvailableException;
    void returnBook(int bookId, int studentId);
    List<IssueRecord> activeIssuesForStudent(int studentId);
}
