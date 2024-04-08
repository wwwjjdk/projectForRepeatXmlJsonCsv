package format.hw;

public class Employee {
    private long id;
    private String name;
    private String lastname;
    private String country;
    private int age;

    public Employee(long id, String name, String lastname, String country, int age) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.country = country;
        this.age = age;
    }

    public Employee() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", country='" + country + '\'' +
                ", age=" + age +
                '}';
    }
}
