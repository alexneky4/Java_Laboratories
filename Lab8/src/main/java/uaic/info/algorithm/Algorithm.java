package uaic.info.algorithm;

import uaic.info.entities.Album;
import uaic.info.entities.Genre;
import uaic.info.entities.Playlist;

import java.util.*;

public class Algorithm {

    public static boolean[][] createMatrix(List<Album> albumList)
    {
        int n = albumList.size();
        boolean[][] matrix = new boolean[n][n];
        for(boolean[] row : matrix)
            Arrays.fill(row,false);
        for(int i = 0; i < n-1; i++)
            for(int j = i + 1; j < n; j++)
            {
                matrix[i][j] = matrix[j][i] = true;
                Album album1 = albumList.get(i);
                Album album2 = albumList.get(j);
                if(album1.getId() == album2.getId()
                    || album1.getArtist() == album2.getArtist()
                    || album1.getReleaseYear() == album2.getReleaseYear()
                    || album1.getName() == album2.getName())
                {
                    matrix[i][j] = matrix[j][i] = false;
                    continue;
                }
                for(Genre genre : album1.getGenreList())
                    if(album2.getGenreList().contains(genre)) {
                        matrix[i][j] = matrix[j][i] = false;
                        break;
                    }
            }

        return matrix;
    }

    public static boolean[][] createComplementMatrix(List<Album> albums)
    {
        boolean[][] complementMatrix = createMatrix(albums);
        for(int i = 0; i < complementMatrix[0].length - 1; i++)
            for(int j = i + 1; j < complementMatrix[0].length; j++)
            {
                complementMatrix[i][j] = !complementMatrix[i][j];
                complementMatrix[j][i] = !complementMatrix[j][i];
            }
        return complementMatrix;
    }

    public static List<Playlist> maximalPlaylists(List<Album> albums)
    {
        boolean[][] matrix = createComplementMatrix(albums);
        Set<Set<Integer>> cliques = new HashSet<>();
        Set<Integer> p = new HashSet<>();
        for(int i = 0; i < matrix[0].length; i++)
            p.add(i);
        Set<Integer> r = new HashSet<>();
        Set<Integer> x = new HashSet<>();

        bronKerbosch(cliques,r,p,x,matrix);
        List<Playlist> maximalPlaylists = new LinkedList<>();
        int id = 1;
        for(Set<Integer> clique : cliques)
        {
            Playlist playlist = new Playlist(id,"playlist" + id, 2023);
            id++;
            for (Integer vertex : clique)
                playlist.addAlbum(albums.get(vertex));
            maximalPlaylists.add(playlist);
        }
        return maximalPlaylists;
    }

    private static void bronKerbosch(Set<Set<Integer>> cliques, Set<Integer> r, Set<Integer> p, Set<Integer> x,
                                     boolean[][] matrix)
    {
        if(p.isEmpty() && x.isEmpty())
        {
            cliques.add(r);
            return;
        }
        int pivot = selectPivotVertex(p,x,matrix);
        Set<Integer> neighbours = new HashSet<>();
        for(int i = 0; i < matrix[pivot].length; i++)
            if(matrix[pivot][i] == true)
                neighbours.add(i);
        for(int v : new HashSet<>(p))
        {
            if(!neighbours.contains(v))
            {
                Set<Integer> newP = new HashSet<>(p);
                Set<Integer> neighbourV = new HashSet<>();
                for(int i = 0; i < matrix[v].length; i++)
                    if(matrix[v][i] == true)
                        neighbourV.add(i);
                newP.retainAll(neighbourV);
                Set<Integer> newR = new HashSet<>(r);
                newR.add(v);
                Set<Integer> newX = new HashSet<>(x);
                newX.retainAll(neighbourV);
                bronKerbosch(cliques,newR,newP,newX,matrix);
                p.remove(v);
                x.add(v);
            }
        }

    }

    private static int selectPivotVertex(Set<Integer> p, Set<Integer> x, boolean[][] matrix)
    {
        Set<Integer> candidates = new HashSet<>(p);
        candidates.addAll(x);
        int pivot = -1;
        int maxDegree = -1;
        for(int v : candidates)
        {
            int degree = 0;
            for(boolean value : matrix[v])
                if(value == true)
                    degree++;
            if(degree > maxDegree)
            {
                maxDegree = degree;
                pivot = v;
            }
        }

        return pivot;
    }

}
