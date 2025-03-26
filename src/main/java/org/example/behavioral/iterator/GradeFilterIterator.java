package org.example.behavioral.iterator;

public class GradeFilterIterator implements Iterator {

    private Student[] students;
    private int position;
    private int filterGrade;

    public GradeFilterIterator(Student[] students, int filterGrade) {
        this.students = students;
        this.filterGrade = filterGrade;
    }

    @Override
    public boolean hasNext() {
        while (position < students.length) {
            if (students[position].getGrade() == filterGrade) {
                return true;
            }
            position++;
        }
        return false;
    }

    @Override
    public Object next() {
        if (this.hasNext()) {
            Student student = students[position];
            position++;
            return student;
        }
        return null;
    }
}
