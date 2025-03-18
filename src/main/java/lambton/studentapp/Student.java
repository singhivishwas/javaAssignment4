package lambton.studentapp;

import jakarta.validation.constraints.*;

public class Student {

    private Integer id;

    @NotNull
    @Size(min = 2, max = 40)
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotNull
    @Min(18)
    private Integer age;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
