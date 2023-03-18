import uaic.info.algorithm.Algorithm;
import uaic.info.company.Company;
import uaic.info.employee.Designer;
import uaic.info.employee.Person;
import uaic.info.employee.Programmer;
import uaic.info.network.Network;
import uaic.info.node.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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

        List<Node> nodes = new ArrayList<>();
        nodes.add(person1);
        nodes.add(person2);
        nodes.add(person3);
        nodes.add(person4);
        nodes.add(company1);
        nodes.add(company2);
/*
        for(Node node : nodes)
        {
            System.out.println(node.toString());
        }
*/
        Company bitdefender = new Company("Bitdefender");
        Company mambu = new Company("Mambu");
        Company centric = new Company("Centric");


        Programmer razvan = new Programmer("Razvan", "22-09-2002", Arrays.asList("C++", "Java", "Python", "JavaScript"));
        Designer alex = new Designer("Alex", "11-05-2003", "Graphical Designer");
        Person tudor = new Person("Tudor", "8-11-2001");

        razvan.addNewRelationship(alex, "Friend");
        razvan.addNewRelationship(tudor, "Colleagues in college");
        razvan.addNewRelationship(bitdefender, "Software Engineer");
        alex.addNewRelationship(mambu, "Graphical Designer");
        tudor.addNewRelationship(centric, "Internship");

        Network network = new Network(Arrays.asList(bitdefender, mambu, centric, razvan, alex ,tudor));
        Algorithm algorithm = new Algorithm();
        for(Node node : algorithm.getCutPoints(network))
        {
            System.out.println("Cutpoint : " + node.toString());
        }

        /*
       for(Set<Node> component : algorithm.getBicconnectedComponents(network))
        {
            System.out.println("Bipartited component: ");
            for(Node node : component)
                System.out.println(node.toString());
            System.out.println();
        }

         */


    }
}