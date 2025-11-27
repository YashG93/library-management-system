package org.library.service.impl;

import org.library.dao.BookDao;
import org.library.dao.StudentDao;
import org.library.dao.impl.BookDaoImpl;
import org.library.dao.impl.StudentDaoImpl;
import org.library.exception.BookNotFoundException;
import org.library.model.Book;
import org.library.model.IssueRecord;
import org.library.model.Student;
import org.library.service.LibraryService;

import java.util.ArrayList;
import java.util.List;

public class LibraryServiceImpl implements LibraryService {

    private BookDao bookDao = new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void removeBook(int bookId) throws BookNotFoundException {
        Book book = bookDao.getBookById(bookId);
        if (book == null) {
            throw new BookNotFoundException("Book with ID " + bookId + " not found.");
        }
        bookDao.removeBook(bookId);
    }

    @Override
    public List<Book> listAllBooks() {
        return bookDao.getAllBooks();
    }

    private StudentDao studentDao = new StudentDaoImpl(); // initialize DAO

    @Override
    public void addStudent(Student student) {
        studentDao.addStudent(student);
    }

    @Override
    public List<Student> listAllStudents() {
        return studentDao.getAllStudents();
    }


    @Override
    public void issueBook(int bookId, int studentId) {
    }

    @Override
    public void returnBook(int bookId, int studentId) {
    }

    @Override
    public List<IssueRecord> activeIssuesForStudent(int studentId) {
        return new ArrayList<>();
    }
}
