package com.example.registrationservice.models;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@MappedSuperclass
public class Voter {
    @NotEmpty(message="Name cannot be empty")
    @NotNull(message="Name cannot be null")
    @NotBlank(message="Name cannot be blank")
    private String firstName;
    @NotEmpty(message="Name cannot be empty")
    @NotNull(message="Name cannot be null")
    @NotBlank(message="Name cannot be blank")
    private String lastName;
    private String token;
    @Email
    private String email;
}
