package com.tuwaiq.lab11_blogsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Sorry, the category name can't be empty, please try again")
    @Size(min = 3, max = 15, message = "Sorry, the name can't be less than 4 or longer than 15 characters, please try again")
    @Column(columnDefinition = "varchar(15) not null")
    private String name;
}
