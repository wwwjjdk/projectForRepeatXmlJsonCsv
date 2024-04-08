package collection;

import java.util.Comparator;

public class Worker implements  Comparable<Worker>{
    private int grade;
    private String name;

    public Worker(int grade, String name) {
        this.grade = grade;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "grade=" + grade +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Worker altWorker) {
        return grade - altWorker.grade;
    }
}
