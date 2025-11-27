package org.library.app;

import org.library.exception.BookNotFoundException;
import org.library.exception.NoCopiesAvailableException;
import org.library.model.Book;
import org.library.model.Student;
import org.library.model.IssueRecord;
import org.library.service.LibraryService;
import org.library.service.impl.LibraryServiceImpl;

import java.util.List;
import java.util.Scanner;

public class MainApp {
    private static LibraryService service = new LibraryServiceImpl();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n==== Library Management System ====");
            System.out.println("1. Add Book");
            System.out.println("2. List All Books");
            System.out.println("3. Add Student");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. List Active Issues for Student");
            System.out.println("7. List All Students");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addBook();
                case 2 -> listBooks();
                case 3 -> addStudent();
                case 4 -> issueBook();
                case 5 -> returnBook();
                case 6 -> listIssues();
                case 7 -> listStudents();
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private static void addBook() {
        System.out.print("Title: ");
        String title = sc.nextLine();
        System.out.print("Author: ");
        String author = sc.nextLine();
        System.out.print("Category: ");
        String category = sc.nextLine();
        System.out.print("Quantity: ");
        int qty = sc.nextInt();
        sc.nextLine();

        service.addBook(new Book(0, title, author, category, qty));
        System.out.println("Book added successfully!");
    }

    private static void listBooks() {
        List<Book> books = service.listAllBooks();
        System.out.println("\n--- All Books ---");
        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }
        for (Book b : books) {
            System.out.println(b);
        }
    }

    private static void addStudent() {
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();

        service.addStudent(new Student(0, name, email));
        System.out.println("Student added successfully!");
    }

    private static void listStudents() {
        List<Student> students = service.listAllStudents();
        if (students == null || students.isEmpty()) {
            System.out.println("\nNo students found.");
            return;
        }
        System.out.println("\n--- All Students ---");
        for (Student s : students) {
            System.out.println("ID: " + s.getId() + ", Name: " + s.getName() + ", Email: " + s.getEmail());
        }
    }


    private static void issueBook() {
        System.out.print("Book ID: ");
        int bookId = sc.nextInt();
        System.out.print("Student ID: ");
        int studentId = sc.nextInt();
        sc.nextLine();

        try {
            service.issueBook(bookId, studentId);
            System.out.println("Book issued successfully!");
        } catch (BookNotFoundException | NoCopiesAvailableException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void returnBook() {
        System.out.print("Book ID: ");
        int bookId = sc.nextInt();
        System.out.print("Student ID: ");
        int studentId = sc.nextInt();
        sc.nextLine();

        try {
            service.returnBook(bookId, studentId);
            System.out.println("Book returned successfully!");
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void listIssues() {
        System.out.print("Student ID: ");
        int studentId = sc.nextInt();
        sc.nextLine();

        List<IssueRecord> issues = service.activeIssuesForStudent(studentId);
        System.out.println("\n--- Active Issues ---");
        if (issues.isEmpty()) {
            System.out.println("No active issues for this student.");
        } else {
            for (IssueRecord i : issues) {
                System.out.println(i);
            }
        }
    }
}
