import java.util.*;
public class NetworkFlow{

    public static void maxFlow(Graph graph){

        for (Vertex v : graph.vertices){
            System.out.print(v.label + " ");
        }
        for (Edge e : graph.edges){
            System.out.println("S: " + e.source + ", D: " + e.destination + ", C: " +e.capacity);
        }
    }

    public static void main(String[] args){
        int[][] matrix = new int[6][6];

        matrix[0][1] = 40;  // 00 40 68 00 00 00
        matrix[0][2] = 68;  // 00 00 00 12 00 00
        matrix[1][3] = 12;  // 00 00 00 86 00 00
        matrix[2][3] = 86;  // 00 00 00 00 44 00
        matrix[3][4] = 44;  // 00 00 00 00 00 52 
        matrix[4][5] = 52;  // 00 00 00 00 00 00
                            
        Graph graph = new Graph(matrix); 
        maxFlow(graph);
    }
}

class Graph{

    Vertex[] vertices;
    ArrayList<Edge> edges;

    Graph(int[][] matrix){
        initFromAdjacencyMatrix(matrix);
    }

    public void initFromAdjacencyMatrix(int[][] matrix){
        //Add all vertices with its edges stored internally.
        vertices = new Vertex[matrix.length];
        edges = new ArrayList<Edge>();
        for (int i = 0; i < matrix.length; i++){
            vertices[i] = new Vertex();
            for (int j = 0; j < matrix[i].length; j++){
                if (matrix[i][j] != 0){
                    edges.add(new Edge(i,j,matrix[i][j]));
                }
            }
        }
    }

}

class Vertex{
    String label;
    static int id = 65; // Ascii value auto increment

    Vertex(){
        label = ((char)id++) + "";
    }
}

class Edge{
    int source;
    int destination; 
    int capacity;

    Edge(int source, int destination, int capacity){
        this.source = source;
        this.destination = destination;
        this.capacity = capacity; 
    }
}


