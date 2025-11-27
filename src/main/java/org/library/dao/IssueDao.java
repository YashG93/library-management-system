package org.library.dao;

import org.library.model.IssueRecord;
import java.util.List;

public interface IssueDao {
    void issueBook(IssueRecord record);
    void returnBook(int bookId, int studentId);
    List<IssueRecord> getActiveIssuesForStudent(int studentId);
}
