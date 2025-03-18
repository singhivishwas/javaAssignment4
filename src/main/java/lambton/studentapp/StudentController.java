package lambton.studentapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final ObjectMapper objectMapper;

    public StudentController(StudentService studentService, ObjectMapper objectMapper) {
        this.studentService = studentService;
        this.objectMapper = objectMapper;
    }

    // Show all students
    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    // Show form to add a new student
    @GetMapping("/new")
    public String newStudent(Model model) {
        model.addAttribute("student", new Student());
        return "new-student";
    }

    // Save student
    @PostMapping("/save")
    public String saveStudent(@Valid @ModelAttribute Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "new-student";
        }
        studentService.addStudent(student);
        return "redirect:/students";
    }

    // Delete student
    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

    // Export as JSON
    @GetMapping("/json")
    public ResponseEntity<String> exportStudentsAsJson() throws Exception {
        List<Student> students = studentService.getAllStudents();
        String json = objectMapper.writeValueAsString(students);
        return ResponseEntity.ok(json);
    }
}
