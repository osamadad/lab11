package com.tuwaiq.lab11_blogsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Sorry, the title can't be empty, please try again")
    @Size(min = 5, max = 35, message = "Sorry, the title can't be less than 5 or longer than 35 character, please try again")
    @Column(columnDefinition = "varchar(35) not null")
    private String title;
    @NotEmpty(message = "Sorry, the content can't be empty, please try again")
    @Size(min = 25, max = 150, message = "Sorry, the content can't be less than 25 or longer then 150 characters, please try again")
    @Column(columnDefinition = "varchar(150) not null")
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd@HH-mm-ss")
    @Column(columnDefinition = "datetime not null")
    private LocalDateTime publishDate;
    @NotNull(message = "Sorry, the category id can't be empty, please try again")
    @Column(columnDefinition = "int not null")
    private Integer categoryId;
    @NotNull(message = "Sorry, the user id can't be empty, please try again")
    @Column(columnDefinition = "int not null")
    private Integer userId;
}
