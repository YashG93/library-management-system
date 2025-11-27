package org.library.dao;

import org.library.model.Student;
import java.util.List;

public interface StudentDao {
    void addStudent(Student student);
    Student getStudentById(int studentId);
    List<Student> getAllStudents();
}
