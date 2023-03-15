package uaic.info.company;

import uaic.info.employee.Person;
import uaic.info.node.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Company implements Node,Comparable<Company> {

    private String name;
    private List<Person> employees = new ArrayList<>();

    public Company(String name) {
        this.name = name;
    }

    public Company() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(name, company.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                //", employees=" + employees +
                '}';
    }

    public List<Person> getEmployees() {
        return employees;
    }

    public void addEmployee(Person person)
    {
        employees.add(person);
    }

    @Override
    public int getWeight()
    {
        return employees.size();
    }
    public void setEmployees(List<Person> employees) {
        this.employees = employees;
    }



    @Override
    public int compareTo(Company company) {
        return this.name.compareTo(company.name);
    }
    @Override
    public List<Node> getAdjacencyList()
    {
        return new ArrayList<Node>(this.employees);
    }
}
