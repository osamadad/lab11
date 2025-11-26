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
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Sorry, the content can't be empty, please try again")
    @Size(min = 3,max = 50, message = "Sorry, the content can't be less than 3 or longer than 50 characters, please try again")
    @Column(columnDefinition = "varchar(50) not null")
    private String content;
    @NotNull(message = "Sorry, the comment date can't be empty, please try again")
    @DateTimeFormat(pattern = "yyyy-MM-dd@HH-mm-ss")
    @Column(columnDefinition = "datetime not null")
    private LocalDateTime commentDate;
    @NotNull(message = "Sorry, the user id can't be empty, please try again")
    @Column(columnDefinition = "int not null")
    private Integer userId;
    @NotNull(message = "Sorry, the post id can't be empty, please try again")
    @Column(columnDefinition = "int not null")
    private Integer postId;
}
