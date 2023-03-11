package uaic.info.network;

import uaic.info.node.Node;

import java.util.ArrayList;
import java.util.List;

public class Network {
    private List<Node> graphNodes = new ArrayList<>();

    public double importanceNode(Node node)
    {
        if(!graphNodes.contains(node))
            return 0;
        
    }
}
