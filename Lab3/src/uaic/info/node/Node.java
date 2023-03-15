package uaic.info.node;

import java.util.List;
import java.util.Map;

public interface Node {

    String getName();

    List<Node> getAdjacencyList();

    int getWeight();
}
