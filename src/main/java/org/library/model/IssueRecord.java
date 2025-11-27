package org.library.model;

import java.time.LocalDate;

public class IssueRecord {
    private int id;
    private int bookId;
    private int studentId;
    private LocalDate issueDate;
    private LocalDate returnDate;

    public IssueRecord() {}
    public IssueRecord(int id, int bookId, int studentId, LocalDate issueDate, LocalDate returnDate) {
        this.id = id;
        this.bookId = bookId;
        this.studentId = studentId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    @Override
    public String toString() {
        return "ID: " + id + ", BookID: " + bookId + ", StudentID: " + studentId +
                ", IssueDate: " + issueDate + ", ReturnDate: " + returnDate;
    }
}
