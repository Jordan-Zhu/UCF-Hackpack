import java.util.*;
class Dijkstra{
    Graph graph;

    Dijkstra(Graph graph){
        this.graph = graph; 
    }

    public void run(int source){
        boolean[] visited = new boolean[graph.vertices.length];
        PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
        pq.add(graph.vertices[source]);
        graph.vertices[source].minDistance = 0;
        while (!pq.isEmpty()){
            Vertex u = pq.remove();
            for (Edge e : u){
                Vertex v = graph.vertices[e.d];
                double weight = e.w;
                double distanceThroughU = u.minDistance + e.w;
                if (distanceThroughU < v.minDistance){
                    v.minDistance = distanceThroughU;
                    v.previous = u;
                    pq.add(v);
                }
            }
        }
    }

    public void printShortestPathTo(int destination){
        StringBuilder builder = new StringBuilder();
        System.out.print("Path: ");
        for (Vertex v = graph.vertices[destination]; v != null; v = v.previous){
            builder.append(v.id + " >- ");
        }
        System.out.println(builder.reverse().toString().substring(4));
    }

    public double getDistanceTo(int destination){
        return graph.vertices[destination].minDistance;
    }

    public static void main(String[] args){
        int[][] matrix = new int[6][6];
        matrix[0][1] = 7;
        matrix[0][2] = 2;  // 00 07 02 00 00 99  
        matrix[0][5] = 99; // 00 00 00 03 00 00  
        matrix[1][3] = 3;  // 00 00 00 04 00 00    
        matrix[2][3] = 4;  // 00 00 00 00 05 00    
        matrix[3][4] = 5;  // 00 00 00 00 00 01    
        matrix[4][5] = 1;  // 00 00 00 00 00 00    
        Graph graph = new Graph(matrix); 
        Dijkstra dijkstra = new Dijkstra(graph);

        dijkstra.run(0);
        dijkstra.printShortestPathTo(5);
        System.out.println("Distance: " + dijkstra.getDistanceTo(5));
    }
}

class Graph{
    Vertex[] vertices;

    Graph(int[][] matrix){
        vertices = graphFromAdjacencyMatrix(matrix);
    }

    public Vertex[] graphFromAdjacencyMatrix(int[][] matrix){
        //Add all vertices with its edges stored internally.
        Vertex[] list = new Vertex[matrix.length];
        for (int vertex = 0; vertex < matrix.length; vertex++){
            Vertex newVertex = new Vertex(vertex); 
            for (int edge = 0; edge < matrix[vertex].length; edge++){
                if (matrix[vertex][edge] > 0){
                    newVertex.add(new Edge(vertex,edge,matrix[vertex][edge]));
                }
            }
            list[vertex] = newVertex;
        } 
        return list;
    }
}

class Vertex extends ArrayList<Edge> implements Comparable<Vertex>{
    double minDistance = Double.POSITIVE_INFINITY;
    int id;
    Vertex previous = null;

    Vertex(int id){
        this.id = id;
    }

    public int compareTo(Vertex other){
        return Double.compare(minDistance, other.minDistance);
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

    public String toString(){
        return "S: " + s + ", D: " + d + ", W: " + w + " | ";
    }
}
