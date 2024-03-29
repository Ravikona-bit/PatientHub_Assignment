package com.wengerwatson.patienthub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "Name is mandatory")
    @Size(min = 5, max = 100)
    private String name;
    private int age;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp="(^[0-9]{10}$)", message = "Invalid phone number")
    //@Size(min = 10, max = 13)
    private int phoneNumber;

    @NotNull(message = "Email is mandatory")
    @NotBlank(message = "Email is mandatory")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE, message = "Invalid email format")
   // @Size(min = 10, max = 100)
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
