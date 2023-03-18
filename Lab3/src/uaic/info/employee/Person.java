package uaic.info.employee;

import uaic.info.company.Company;
import uaic.info.node.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Person implements Node,Comparable<Person> {

    private String name;
    private String birthdate;
    private HashMap<Node,String> relationship = new HashMap<>();

    public Person() {
    }

    public Person(String name, String birthdate) {
        this.name = name;
        this.birthdate = birthdate;
    }

    public Person(String name, String birthdate, HashMap<Node, String> relationship) {
        this.name = name;
        this.birthdate = birthdate;
        this.relationship = relationship;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public HashMap<Node, String> getRelationship() {
        return relationship;
    }

    public void setRelationship(HashMap<Node, String> relationship) {
        this.relationship = relationship;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public int compareTo(Person person) {
        return this.name.compareTo(person.name);
    }

    public void addNewRelationship(Node node, String relation)
    {
            relationship.putIfAbsent(node,relation);
        if(node instanceof Person)
        {
            ((Person)node).getRelationship().putIfAbsent(this,relation);
        }
        if(node instanceof Company)
        {
            ((Company)node).addEmployee(this);
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthdate='" + birthdate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(birthdate, person.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthdate);
    }

    @Override
    public int getWeight()
    {
        return relationship.size();
    }

    @Override
    public List<Node> getAdjacencyList()
    {
        return new ArrayList<>(this.relationship.keySet());
    }
}
