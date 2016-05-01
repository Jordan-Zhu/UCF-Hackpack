import java.util.ArrayList;

/**
 * Bellman-Ford Algorithm: This algorithm is useful when the graph is sparse, has negative
 * edge weights and you only need distances from one vertex.
 * Bellman-Ford finds shortest distances from a source vertex
 * for directed graphs with or without negative edge weights.
 */
public class BellmanFord {

    final public static int oo = (int) 1e9;     // infinity

    Graph graph;

    BellmanFord(Graph graph) {
        this.graph = graph;
    }

    public static void main(String[] args) {
        // Read in graph.
        int [][] adj = new int[5][5];
        adj = new int[][] {
                {0, 6, oo, oo, 7},
                {oo, 0, 5, -4, 8},
                {oo, -2, 0, oo, oo},
                {2, oo, 7, 0, oo},
                {oo, oo, -3, 9, 0}};

        // Prints shortest path answer 0 6 9 2 7.

        Graph graph = new Graph(adj);
        BellmanFord bellmanford = new BellmanFord(graph);

        bellmanford.printShortestPath(bellmanford.run(0));


//        Scanner fin = new Scanner(System.in);
//        int numEdges = 0;
//        for(int i = 0; i < 25; i++) {
//            adj[i/5][i%5] = fin.nextInt();
//            // These are the vertices.
//            if (adj[i/5][i%5] != 0) {
//                numEdges++;
//            }
//        }

        // Form edge list.
//        Edge[] eList = new Edge[numEdges];
//        int eCnt = 0;
//        for(int i = 0; i < 25; i++) {
//            if (adj[i/5][i%5] != 0) {
//                eList[eCnt++] = new Edge(i/5, i%5, adj[i/5][i%5]);
//            }
//        }

        // Run algorithm and print out shortest distances.
//        int[] answers = bellmanford(eList, 5, 0);
//        for(int i = 0; i < 5; i++) {
//            System.out.print(answers[i] + " ");
//        }
//        System.out.println();
    }

    public int[] run(int source) {
        // This array will store our estimates of shortest distances.
        int numVertices = graph.vertices.length;
        int[] estimates = new int[numVertices];

        // Set these to a very large number, larger than any distance
        // that could possibly be reachable on the graph.
        for(int i = 0; i < estimates.length; i++)
        {
            estimates[i] = oo;
        }

        // We are already at our source vertex.
        estimates[source] = 0;

        // Runs v-1 times since the max number of edges on any shortest path is
        // v-1, if there are no negative weight cycles.
        for(int i = 0; i < numVertices - 1; i++) {
            Vertex u = graph.vertices[i];
            // Update all estimates based on this particular edge only.
            for (Edge e : u) {
                // If our current estimate to vertex d is greater
                // than our estimate from the sum of vertex s to edge sd, improve it!
                if (estimates[e.s] + e.w < estimates[e.d]) {
                    estimates[e.d] = estimates[e.s] + e.w;
                }
            }
        }
        return estimates;
    }

    public void printShortestPath(int[] answers) {
        for(int i = 0; i < answers.length; i++) {
            System.out.print(answers[i] + " ");
        }
        System.out.println();
    }
}

class Graph {
    Vertex[] vertices;

    Graph(int[][] matrix) {
        vertices = graphFromAdjacencyMatrix(matrix);
    }

    public Vertex[] graphFromAdjacencyMatrix(int[][] matrix) {
        // Add all vertices with its edges stored internally.
        Vertex[] list = new Vertex[matrix.length];
        for(int vertex = 0; vertex < matrix.length; vertex++) {
            Vertex newVertex = new Vertex(vertex);
            for(int edge = 0; edge < matrix[vertex].length; edge++) {
                if(matrix[vertex][edge] != 0) {
                    newVertex.add(new Edge(vertex, edge, matrix[vertex][edge]));
                }
            }
            list[vertex] = newVertex;
        }
        return list;
    }
}

class Vertex extends ArrayList<Edge>{
    int id;

    Vertex(int id){
        this.id = id;
    }
}

class Edge{
    int s;
    int d;
    int w;

    Edge(int s, int d, int w){
        this.s = s;
        this.d = d;
        this.w = w;
    }

    public void negate() {
        w = -w;
    }

    public String toString(){
        return "S: " + s + ", D: " + d + ", W: " + w + " | ";
    }
}
