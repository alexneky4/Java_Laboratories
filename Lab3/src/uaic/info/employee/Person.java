package uaic.info.employee;

import uaic.info.node.Node;

import java.util.HashMap;

public class Person implements Node,Comparable<Person> {

    private String name;
    private String birthdate;
    private HashMap<Node,String> relationship;

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
        relationship.put(node,relation);
    }

    @Override
    public double getWeight()
    {
        return relationship.size();
    }
}
