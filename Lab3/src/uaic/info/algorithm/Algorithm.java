package uaic.info.algorithm;

import uaic.info.company.Company;
import uaic.info.employee.Person;
import uaic.info.network.Network;
import uaic.info.node.Node;

import java.util.*;

public class Algorithm {
    private boolean[] visited;
    private int[] tin;
    private int[] low;
    private int timer;
    List<Node> cutPoints = new LinkedList<>();
    List<List<Node>> bicconnectedComponents = new LinkedList<>();
    Stack<Node> auxiliarStack = new Stack<>();
    private void DFS(Network network,int v, int p)
    {
        visited[v] = true;
        tin[v] = low[v] = timer++;
        int children = 0;
        Node node = network.getGraphNodes().get(v);
        if(!auxiliarStack.contains(node))
            auxiliarStack.push(node);
            for(Node adjacentNode : node.getAdjacencyList()) {
                int to = network.getGraphNodes().indexOf(adjacentNode);
                if (to == p)
                    continue;
                if (visited[to] == true)
                    low[v] = Math.min(low[v], tin[to]);
                else {
                    DFS(network,to,v);
                    low[v] = Math.min(low[v], low[to]);
                    children++;
                    if (low[to] >= tin[v] && p != -1) {
                        LinkedList<Node> biconnectedComponent = new LinkedList<>();
                        boolean addedNodes = false;
                        cutPoints.add(adjacentNode);
                        while (!auxiliarStack.empty() && auxiliarStack.peek() != node) {
                            biconnectedComponent.add(auxiliarStack.pop());
                            addedNodes = true;
                        }
                        //biconnectedComponent.add(auxiliarStack.pop());
                        if(addedNodes == true)
                            bicconnectedComponents.add(biconnectedComponent);
                    }
                }
            }
            if(p == -1 && children > 1)
                cutPoints.add(network.getGraphNodes().get(v));

    }
    private void torjanAlgorithm(Network network)
    {
        cutPoints.clear();
        visited = new boolean[network.getGraphNodes().size()];
        tin = new int[network.getGraphNodes().size()];
        low = new int[network.getGraphNodes().size()];
        timer = 0;
        Arrays.fill(visited,false);
        Arrays.fill(tin,-1);
        Arrays.fill(low,-1);
        ArrayList<Node> cutPoints = new ArrayList<>();
        for(int i = 0; i < visited.length; i++)
        {
            if(visited[i] == false)
                DFS(network,i,-1);
            List<Node> stackList = new LinkedList<>();
            boolean addedNodes = false;
            while(!auxiliarStack.empty())
            {
                addedNodes = true;
                stackList.add(auxiliarStack.pop());
            }
            if(addedNodes == true)
                bicconnectedComponents.add(stackList);
        }

    }

    public List<Node> getCutPoints(Network network)
    {
        torjanAlgorithm(network);
        return this.cutPoints;
    }

    public List<List<Node>> getBicconnectedComponents(Network network)
    {
        torjanAlgorithm(network);
        return this.bicconnectedComponents;
    }
}
