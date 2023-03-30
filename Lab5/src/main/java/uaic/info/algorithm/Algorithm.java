package uaic.info.algorithm;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solution;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.variables.IntVar;
import uaic.info.catalog.Catalog;
import uaic.info.document.Document;

import java.util.Arrays;
import java.util.List;

public class Algorithm {

    public static void coloringAlgorithm(Catalog catalog)
    {
        List<Document> docs = catalog.getDocs();
        int[][] matrix = new int[docs.size()][docs.size()];
        for(int[] row : matrix)
            Arrays.fill(row,0);
        for(int i = 0; i < docs.size() - 1; i++)
            for(int j = i + 1; j < docs.size(); j++)
            {
                Document doc1 = docs.get(i);
                Document doc2 = docs.get(j);
                for(String tag : doc1.getTags().keySet())
                    if(doc2.getTags().containsKey(tag))
                    {
                        matrix[i][j] = matrix[j][i] = 1;
                        break;
                    }
            }

        int numberOfVertices = docs.size();
        int numColor = numberOfVertices;

        Model model = new Model("Graph Coloring");

        IntVar[] colors = model.intVarArray("colors", numberOfVertices, 1, numColor);

        for(int i = 0; i < numberOfVertices - 1; i++)
            for(int j = i + 1; j < numberOfVertices; j++)
                if(matrix[i][j] == 1)
                {
                    Constraint c = model.arithm(colors[i], "!=", colors[j]);
                    model.post(c);
                }

        IntVar numUsedColors = model.intVar("numUsedColors", 1, numberOfVertices);
        Constraint c = model.count(numUsedColors,colors,numUsedColors);
        model.post(c);
        model.setObjective(Model.MINIMIZE, numUsedColors);

        Solution solution = model.getSolver().findOptimalSolution(numUsedColors, Model.MINIMIZE);

        // Print the solution
        if (solution != null) {
            System.out.println("Minimum number of colors: " + solution.getIntVal(numUsedColors));
            for (int i = 0; i < numberOfVertices; i++) {
                System.out.println("Vertex " + i + " has color " + solution.getIntVal(colors[i]));
            }
        } else {
            System.out.println("No solution found");
        }
    }

}
