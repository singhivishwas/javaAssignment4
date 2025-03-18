package lambton.studentapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)  // Load only the specific controller
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetStudentsReturnsStatus200() throws Exception {
        when(studentService.getAllStudents()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(view().name("students"))
                .andExpect(model().attributeExists("students"));
    }

    @Test
    void testPostSaveStudentWithInvalidDataReturnsFormView() throws Exception {
        mockMvc.perform(post("/students/save")
                        .param("name", "") // Invalid name
                        .param("email", "invalid-email") // Invalid email
                        .param("age", "15")) // Invalid age (less than 18)
                .andExpect(status().isOk())
                .andExpect(view().name("new-student"))
                .andExpect(model().attributeHasFieldErrors("student", "name", "email", "age"));
    }
}
