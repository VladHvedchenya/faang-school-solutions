package Multithreading.Task24;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class School {
    @Getter
    private final String name;
    private final Set<Student> students;

    public School(String name, Set<Student> students) {
        //null checks for string and set types
        this.name = name;
        this.students = students;
    }

    public int getTotalPoints() {
        return students.stream()
                .mapToInt(Student::getPoints)
                .sum();
    }

    public List<Student> getStudents(){
        return new ArrayList<>(students);
    }
}