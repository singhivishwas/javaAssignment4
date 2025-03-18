package lambton.studentapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    private final StudentService studentService = new StudentService();

    @Test
    void testAddStudentIncreasesListSize() {
        Student student = new Student();
        student.setName("Vishwas Singhi");
        student.setEmail("vishwas@gmail.com");
        student.setAge(20);

        studentService.addStudent(student);

        assertEquals(1, studentService.getAllStudents().size());
    }
}
