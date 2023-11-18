package com.tpe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("bookName") // !!! JSON ciktida name fieldinin "bookName" olmasini sagladik
    private String name; //

    @ManyToOne
    @JsonIgnore // StackOverFlow önlemek için eklendi. Student yazdırılmayacak
    @JoinColumn(name = "student_id")
    private Student student;
}