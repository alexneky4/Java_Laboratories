package uaic.info.network;

import uaic.info.employee.Person;
import uaic.info.node.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Network {
    private List<Node> graphNodes = new ArrayList<>();
    public Network() {
    }

    public Network(List<Node> graphNodes) {
        this.graphNodes = graphNodes;
    }

    public void addNewNode(Node node)
    {
        if(!graphNodes.contains(node))
            graphNodes.add(node);
    }
    public int importanceNode(Node node)
    {
         return node.getWeight();
    }

    public List<Node> getGraphNodes() {
        return graphNodes;
    }

    public void setGraphNodes(List<Node> graphNodes) {
        this.graphNodes = graphNodes;
    }

    @Override
    public String toString() {

        graphNodes.sort((Node n1, Node n2) -> importanceNode(n1) - importanceNode(n2));
        return "Network{" +
                "graphNodes=" + graphNodes +
                '}';

    }
}
