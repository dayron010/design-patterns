package org.example.behavioral.iterator;

public class Main {
    public static void main(String[] args) {
        Student[] students = {
                new Student("Ali", 9),
                new Student("Vali", 10),
                new Student("Sardor", 11),
                new Student("Jasur", 10),
                new Student("Nodir", 9)
        };

        Classroom classroom = new Classroom(students);
        Iterator iterator = classroom.createIterator(9); // Faqat 9-sinf talabalarini olish uchun

        System.out.println("9-sinf talabalari: ");
        while (iterator.hasNext()) {
            Student student = (Student) iterator.next();
            System.out.println(student);
        }

    }
}
