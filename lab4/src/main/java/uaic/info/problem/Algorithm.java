package uaic.info.problem;

import org.graph4j.Graph;
import org.graph4j.GraphBuilder;
import org.graph4j.alg.matching.HopcroftKarpMaximumMatching;
import org.graph4j.util.Matching;
import org.jgrapht.alg.interfaces.MatchingAlgorithm;
import org.jgrapht.alg.matching.HopcroftKarpMaximumCardinalityBipartiteMatching;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import uaic.info.projects.Project;
import uaic.info.student.Student;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Algorithm {

    public static Map<Student, Project> greedyAlgorithm(ProblemInstance instance)
    {
        Map<Student,Project> greedyMatching = new HashMap<>();
        instance.getPreferences().keySet().stream()
                .forEach(s -> greedyMatching.put(s, instance.getPreferences().get(s).stream()
                        .filter(p -> greedyMatching.containsValue(p)).findFirst().orElse(null)));
        return greedyMatching;
    }

    public static Matching maximumMatchingGraph4J(ProblemInstance instance)
    {
        Graph graph = GraphBuilder.empty().buildGraph();
        int i = 0;
        for(Student student : instance.getStudents())
        {
            graph.addVertex(i,student);
            i++;
        }
       for(Project project : instance.getProjects())
       {
           graph.addVertex(i,project);
           i++;
       }

       for(Student student : instance.getPreferences().keySet())
       {
           for(Project project : instance.getPreferences().get(student))
           {
               graph.addEdge(student,project);
           }
       }

        System.out.println(graph);
       return new HopcroftKarpMaximumMatching(graph).getMatching();
    }

    public static MatchingAlgorithm.Matching<GraphNode, DefaultEdge> maximumMatchingJGraphT(ProblemInstance instance)
    {
        org.jgrapht.Graph<GraphNode,DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        for(GraphNode node : instance.getStudents())
            graph.addVertex(node);
        for(GraphNode node : instance.getProjects())
            graph.addVertex(node);
        for(GraphNode student : instance.getPreferences().keySet())
        {
            for(GraphNode project : instance.getPreferences().get(student))
            {
                graph.addEdge(student,project);
            }
        }

        System.out.println(graph);
        Set<GraphNode> partition1 = new TreeSet<>(instance.getProjects());
        Set<GraphNode> partition2 = new TreeSet<>(instance.getStudents());
        HopcroftKarpMaximumCardinalityBipartiteMatching<GraphNode,DefaultEdge> matching
                                        = new HopcroftKarpMaximumCardinalityBipartiteMatching<>(graph,
                                                                        partition1,partition2);
        return matching.getMatching();
    }

    private static boolean bfs(int[][] rGraph, int source, int sink, int parent[], int V)
    {
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; ++i)
            visited[i] = false;

        LinkedList<Integer> queue
                = new LinkedList<Integer>();
        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        // Standard BFS Loop
        while (queue.size() != 0) {
            int u = queue.poll();

            for (int v = 0; v < V; v++) {
                if (visited[v] == false
                        && rGraph[u][v] > 0) {
                    if (v == sink) {
                        parent[v] = u;
                        return true;
                    }
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        return false;
    }

    public static Map<Student,Project> maximumMatching(ProblemInstance instance)
    {
        int nrOfStudents = instance.getStudents().size();
        int nrOfProjects = instance.getProjects().size();
        int nrOfVertices = nrOfProjects + nrOfStudents + 2;
        int source = 0;
        int sink = nrOfVertices - 1;
       int[][] rGraph = new int[nrOfVertices][nrOfVertices];
       for(int i = 1; i <= nrOfStudents; i++)
           rGraph[source][i] = 1;
       for(int i = nrOfStudents + 1; i < nrOfVertices - 1; i++)
             rGraph[i][sink] = 1;

       for(int i = 1; i <= nrOfStudents; i++)
       {
          Student student = instance.getStudents().get(i - 1);
          for(Project project : instance.getPreferences().get(student))
          {
              int index = instance.getProjects().indexOf(project) + 1;
              rGraph[i][index + nrOfStudents] = Integer.MAX_VALUE;
          //    rGraph[index + nrOfStudents][i] = 1;
          }
       }
        for(int i = 0; i < nrOfVertices; i++)
        {
            for(int j = 0; j < nrOfVertices; j++)
                System.out.print(rGraph[i][j] + " ");
            System.out.println();
        }
        System.out.println();
       int parent[] = new int[nrOfVertices];
       int u,v;
        while (bfs(rGraph, source, sink, parent,nrOfVertices)) {
            int path_flow = Integer.MAX_VALUE;
            for (v = sink; v != source; v = parent[v]) {
                u = parent[v];
                path_flow
                        = Math.min(path_flow, rGraph[u][v]);
            }
            System.out.println(path_flow);
            for (v = sink; v != source; v = parent[v]) {
                u = parent[v];
                if(rGraph[u][v] == Integer.MAX_VALUE)
                    rGraph[u][v] = path_flow;
                else
                    rGraph[u][v] -= path_flow;
                if(rGraph[v][u] == Integer.MAX_VALUE)
                    rGraph[v][u] = path_flow;
                else
                    rGraph[v][u] += path_flow;
            }

        }
        for(int i = 0; i < nrOfVertices; i++)
        {
            for(int j = 0; j < nrOfVertices; j++)
                System.out.print(rGraph[i][j] + " ");
            System.out.println();
        }
        Map<Student,Project> matching = new HashMap<>();
        for(int i = 1; i <= nrOfStudents; i++)
        {
            for (int j = nrOfStudents + 1; j < sink; j++)
                if (rGraph[i][j] == 1) {
                    Student student = instance.getStudents().get(i - 1);
                    Project project = instance.getProjects().get(j - nrOfStudents - 1);
                    matching.put(student, project);
                    break;
                }
            matching.putIfAbsent(instance.getStudents().get(i - 1),null);
        }
        return matching;
    }

    public static Set<GraphNode> vertexCover(ProblemInstance instance)
    {
        Map<Student,Project> matching = maximumMatching(instance);
        Set<GraphNode> vertexCover = new HashSet<>();
        for(Student student : matching.keySet())
        {
            if(matching.get(student) != null)
            {
                vertexCover.add(student);
                vertexCover.add(matching.get(student));
            }

        }
        for(Project project : instance.getProjects())
        {
            if (vertexCover.contains(project))
                vertexCover.remove(project);
            else
                vertexCover.add(project);
        }

        return vertexCover;
    }

    public static Set<GraphNode> stableSet(ProblemInstance instance)
    {
        Map<Student,Project> matching = maximumMatching(instance);
        Set<GraphNode> stableSet = new HashSet<>();
        for(Student student : matching.keySet())
        {
            if(matching.get(student) == null)
                stableSet.add(student);

        }
        for(Project project : instance.getProjects())
        {
            if(!instance.getPreferences().containsValue(project))
                stableSet.add(project);
        }

        return stableSet;
    }
}
