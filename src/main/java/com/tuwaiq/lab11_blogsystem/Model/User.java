package com.tuwaiq.lab11_blogsystem.Model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Sorry, the username can't be empty, please try again")
    @Size(min = 3,max = 15,message = "Sorry, the username can't be less than 4 or longer than 15 characters, please try again")
    @Column(columnDefinition = "varchar(15) not null unique")
    private String username;
    @NotEmpty(message = "Sorry, the password can't be empty, please try again")
    @Size(min = 8,max = 16, message = "Sorry, the password can't be less than 8 or longer than 16 characters, please try again")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[0-9-a-zA-Z].*$", message = "Sorry, the password must have at least 1 uppercase, 1 lowercase, and 1 number, please try again")
    @Column(columnDefinition = "varchar(16)")
    private String password;
    @NotEmpty(message = "Sorry, the email can't be empty, please try again")
    @Email(message = "Sorry, the email must be a valid email format, please try again")
    @Size(min = 5, max = 25, message = "Sorry, the email can't be less than 5 or longer than 25 characters, please try again")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd@HH-mm-ss")
    @Column(columnDefinition = "datetime not null")
    private LocalDateTime registrationDate;

}
