import java.util.*;
public class DFS{

    public static void dfs(Graph graph){
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(4);
        boolean[] visited = new boolean[graph.vertices.length];
        visited[4] = true;
        List<Integer>[] vertices = graph.vertices;
        while (!stack.isEmpty()){
            int currVertex = stack.pop();
            // Process here.
            System.out.println("Vertex: " + currVertex + 
                    ", Edges: " + vertices[currVertex].toString());
            for (Integer i : vertices[currVertex]){
                if (!visited[i]){
                    stack.push(i);
                    visited[i] = true;
                }
            }
        }
    }

    public static void main(String[] args){
        boolean[][] matrix = new boolean[6][6];
        matrix[0][1] = true;
        matrix[0][2] = true;  
        matrix[1][0] = true;  // 0 1 1 0 0 0
        matrix[1][3] = true;  // 1 0 0 1 0 0
        matrix[2][0] = true;  // 1 0 0 1 0 0
        matrix[2][3] = true;  // 0 1 1 0 1 0
        matrix[3][1] = true;  // 0 0 0 1 0 1
        matrix[3][2] = true;  // 0 0 0 0 1 0
        matrix[3][4] = true;  
        matrix[4][3] = true;
        matrix[4][5] = true;
        matrix[5][4] = true;
        Graph graph = new Graph(matrix); 
        dfs(graph);
    }
}

class Graph{
    Vertex[] vertices;

    Graph(boolean[][] matrix){
        this.vertices = graphFromAdjacencyMatrix(matrix);
    }

    public Vertex[] graphFromAdjacencyMatrix(boolean[][] matrix){
        //Add all vertices with its edges stored internally.
        Vertex[] list = new Vertex[matrix.length];
        for (int vertex = 0; vertex < matrix.length; vertex++){
            Vertex newVertex = new Vertex(); 
            for (int edge = 0; edge < matrix[vertex].length; edge++){
                if (matrix[vertex][edge] == true){
                    newVertex.add(edge);
                }
            }
            list[vertex] = newVertex;
        } 

        // We reverse the order of the edges so that the dfs works as a topological sort.
        // It makes the stack process vertex A before Vertex B if 
        // encountered at the same time. (Lexicographical Order)
        for (Vertex edges : list){
            Collections.reverse(edges);
        }
        return list;
    }
}

class Vertex extends ArrayList<Integer>{
}
