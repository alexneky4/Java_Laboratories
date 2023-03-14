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
    LinkedList<Node> cutPoints = new LinkedList<>();
    LinkedList<LinkedList<Node>> bicconnectedComponents = new LinkedList<>();
    Stack<Node> auxiliarStack = new Stack<>();
    private void DFS(Network network,int v, int p)
    {
        visited[v] = true;
        tin[v] = low[v] = timer++;
        int children = 0;
        Node node = network.getGraphNodes().get(v);
        auxiliarStack.push(node);
        if(node instanceof Person)
        {
            for(Node adjacentNode : ((Person)node).getRelationship().keySet())
            {
                int to = network.getGraphNodes().indexOf(adjacentNode);
                if(to == p)
                    continue;
                if(visited[to] == true)
                    low[v] = Math.min(low[v],tin[to]);
                else
                {
                    low[v] = Math.min(low[v],low[to]);
                    if(low[to] >= tin[v] && p != -1)
                    {
                        LinkedList<Node> biconnectedComponent = new LinkedList<>();
                        cutPoints.add(adjacentNode);
                        while(auxiliarStack.peek() != node)
                        {
                            biconnectedComponent.add(auxiliarStack.pop());
                        }
                        biconnectedComponent.add(auxiliarStack.pop());
                        bicconnectedComponents.add(biconnectedComponent);
                    }
                    children++;
                }
            }
            if(p == -1 && children > 1)
                cutPoints.add(network.getGraphNodes().get(v));
        }
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
        }
    }

    public LinkedList<Node> getCutPoints(Network network)
    {
        torjanAlgorithm(network);
        return this.cutPoints;
    }

    public LinkedList<LinkedList<Node>> getBicconnectedComponents(Network network)
    {
        torjanAlgorithm(network);
        return this.bicconnectedComponents;
    }
}
