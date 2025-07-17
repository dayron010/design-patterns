package org.example.behavioral.iterator.example1;

public class Classroom implements StudentCollection {

    private Student[] students;

    public Classroom(Student[] students) {
        this.students = students;
    }

    @Override
    public Iterator createIterator(int grade) {
        return new GradeFilterIterator(students, grade);
    }
}
