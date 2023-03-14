import uaic.info.company.Company;
import uaic.info.employee.Person;
import uaic.info.network.Network;

public class Main {
    public static void main(String[] args)
    {
        Person person1 = new Person("Alex","2 Februarie");
        Person person2 = new Person("Razvan","4 Mai");
        Person person3 = new Person("Paul", "18 Aprilie");
        Person person4 = new Person("Anca","22 Martie");
        Company company1 = new Company("Flanco");
        Company company2 = new Company("Cetelem");

        person1.addNewRelationship(person3, "prieten");
        person1.addNewRelationship(person4, "prieten");
        person2.addNewRelationship(person1, "cunostiinta");
        person3.addNewRelationship(person2,"prieten");
        person3.addNewRelationship(person4,"cunostiinta");
        person1.addNewRelationship(company1,"angajat");
        person1.addNewRelationship(company2,"fost angajat");
        person4.addNewRelationship(company1, "angajat");

        Network network = new Network();
        network.addNewNode(person1);
        network.addNewNode(person2);
        network.addNewNode(person3);
        network.addNewNode(person4);
        network.addNewNode(company1);
        network.addNewNode(company2);

        System.out.println(network.toString());
    }
}