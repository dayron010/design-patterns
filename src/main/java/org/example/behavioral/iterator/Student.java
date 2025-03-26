package org.example.behavioral.iterator;

import lombok.Data;

@Data
public class Student {

    private String name;
    private Integer grade;

    public Student(String name, Integer grade) {
        this.name = name;
        this.grade = grade;
    }
}
