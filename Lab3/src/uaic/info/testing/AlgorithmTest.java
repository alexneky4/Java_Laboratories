package uaic.info.testing;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import uaic.info.algorithm.Algorithm;
import uaic.info.employee.Person;
import uaic.info.network.Network;
import uaic.info.node.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmTest {

    @Test
    void k5Test() {
        Person p1 = new Person("1","1");
        Person p2 = new Person("2","2");
        Person p3 = new Person("3","3");
        Person p4 = new Person("4","4");
        Person p5 = new Person("5","5");
        p1.addNewRelationship(p2,"friends");
        p1.addNewRelationship(p3,"friends");
        p1.addNewRelationship(p4,"friends");
        p1.addNewRelationship(p5,"friends");
        p2.addNewRelationship(p3,"friends");
        p2.addNewRelationship(p4,"friends");
        p2.addNewRelationship(p5,"friends");
        p3.addNewRelationship(p4,"friends");
        p3.addNewRelationship(p5,"friends");
        p4.addNewRelationship(p5,"friends");

        Network network = new Network(Arrays.asList(p1,p2,p3,p4,p5));
        Algorithm algorithm = new Algorithm();
        List<Node> cutPoints= algorithm.getCutPoints(network);
        List<Node> expectedCutPoints = List.of(p1,p2,p3,p4,p5);
        Assert.assertEquals(new ArrayList<>(),cutPoints);
    }

    @Test
    void c5Test() {
        Person p1 = new Person("1","1");
        Person p2 = new Person("2","2");
        Person p3 = new Person("3","3");
        Person p4 = new Person("4","4");
        Person p5 = new Person("5","5");
        p1.addNewRelationship(p2,"friends");
        p2.addNewRelationship(p3,"friends");
        p3.addNewRelationship(p4,"friends");
        p4.addNewRelationship(p5,"friends");
        p5.addNewRelationship(p1,"friends");

        Network network = new Network(Arrays.asList(p1,p2,p3,p4,p5));
        Algorithm algorithm = new Algorithm();
        List<Node> cutPoints= algorithm.getCutPoints(network);
        List<Node> expectedCutPoints = List.of(p1,p2,p3,p4,p5);
        Assert.assertEquals(new ArrayList<>(),cutPoints);
    }

    @Test
    void treeTest() {
        Person p1 = new Person("1","1");
        Person p2 = new Person("2","2");
        Person p3 = new Person("3","3");
        Person p4 = new Person("4","4");
        Person p5 = new Person("5","5");
        p3.addNewRelationship(p1,"friends");
        p3.addNewRelationship(p2,"friends");
        p2.addNewRelationship(p4,"friends");
        p2.addNewRelationship(p5,"friends");
        p4.addNewRelationship(p5,"friends");

        Network network = new Network(Arrays.asList(p1,p2,p3,p4,p5));
        Algorithm algorithm = new Algorithm();
        List<Node> cutPoints= algorithm.getCutPoints(network);
        List<Node> expectedCutPoints = List.of(p2,p3);
        Assert.assertEquals(expectedCutPoints,cutPoints);
    }
    @org.junit.jupiter.api.Test
    void getCutPoints() {
    }

    @org.junit.jupiter.api.Test
    void getBicconnectedComponents() {
    }
}