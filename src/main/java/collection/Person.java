package collection;

public class Person /* implements Comparable<Person>*/ {
    private int id;

    public int getId(){
        return id;
    }

    public Person(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                '}';
    }

    //@Override
   // public int compareTo(Person altPerson) {
    //    return id - altPerson.id;
    //}
}
