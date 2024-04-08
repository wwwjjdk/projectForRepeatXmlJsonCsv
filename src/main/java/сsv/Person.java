package сsv;

public class Person {
    private long id;
    private String name;
    private String age;
    private long count;

    public Person(long id, String name, String age, long count) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.count = count;
    }

    public Person() {}

    @Override
    public String toString() {
        return "workTithCsv.Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", count=" + count +
                '}';
    }
}
