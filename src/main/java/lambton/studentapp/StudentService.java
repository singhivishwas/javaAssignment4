package lambton.studentapp;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StudentService {

    private final List<Student> students = new ArrayList<>();
    private final AtomicInteger counter = new AtomicInteger(1);

    // Add student
    public void addStudent(Student student) {
        student.setId(counter.getAndIncrement());
        students.add(student);
    }

    // Get all students
    public List<Student> getAllStudents() {
        return students;
    }

    // Delete student by ID
    public void deleteStudent(Integer id) {
        students.removeIf(student -> student.getId().equals(id));
    }
}
