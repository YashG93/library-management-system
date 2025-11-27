package org.library.dao.impl;

import org.library.dao.IssueDao;
import org.library.model.IssueRecord;
import org.library.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IssueDaoImpl implements IssueDao {

    @Override
    public void issueBook(IssueRecord record) {
        String sql = "INSERT INTO issues(book_id, student_id, issue_date) VALUES(?,?,?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, record.getBookId());
            stmt.setInt(2, record.getStudentId());
            stmt.setDate(3, Date.valueOf(LocalDate.now()));
            stmt.executeUpdate();
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    @Override
    public void returnBook(int bookId, int studentId) {
        String sql = "UPDATE issues SET return_date=? WHERE book_id=? AND student_id=? AND return_date IS NULL";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(LocalDate.now()));
            stmt.setInt(2, bookId);
            stmt.setInt(3, studentId);
            stmt.executeUpdate();
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    @Override
    public List<IssueRecord> getActiveIssuesForStudent(int studentId) {
        List<IssueRecord> list = new ArrayList<>();
        String sql = "SELECT * FROM issues WHERE student_id=? AND return_date IS NULL";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new IssueRecord(
                        rs.getInt("id"),
                        rs.getInt("book_id"),
                        rs.getInt("student_id"),
                        rs.getDate("issue_date").toLocalDate(),
                        null
                ));
            }
        } catch (Exception e) { throw new RuntimeException(e); }
        return list;
    }
}
