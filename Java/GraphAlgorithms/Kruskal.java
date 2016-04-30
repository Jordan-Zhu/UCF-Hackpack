import java.util.*;
public class Kruskal{

    // Returns a min spanning tree of graph
    public Graph run(Graph graph){
        DisjointSets sets = new DisjointSets();
        Set<Edge> minSpanningTreeEdges = new HashSet<Edge>();
        for (Vertex v : graph.vertices){
            sets.makeSet(v);
        }
        for (Edge e : graph.edges){
            Set<Vertex> set1 = sets.findSet(graph.vertices[e.u]);
            Set<Vertex> set2 = sets.findSet(graph.vertices[e.v]);
            if (sets.findSet(graph.vertices[e.u]) != sets.findSet(graph.vertices[e.v])){
                minSpanningTreeEdges.add(e);
                sets.union(set1, set2);
            }        
        }
        return new Graph(graph.vertices, minSpanningTreeEdges); 
    } 

    public static void main(String[] args){
        int[][] matrix = new int[4][4];
        matrix[0][1] = 5;
        matrix[0][2] = 4;
        matrix[0][3] = 7; // 0 5 4 7
        matrix[1][0] = 5; // 5 0 0 3
        matrix[1][3] = 3; // 4 0 0 1
        matrix[2][0] = 4; // 7 3 1 0
        matrix[2][3] = 1;
        matrix[3][0] = 7;
        matrix[3][2] = 1;
        matrix[3][1] = 3;
        Graph graph = new Graph(matrix); 

        Graph minSpanningTree = new Kruskal().run(graph);

        for (Edge e : minSpanningTree.edges){
            System.out.println(e);
        }
    }
}

class Graph{
    Vertex[] vertices;
    List<Edge> edges;
    
    // Make graph from set of vertices
    Graph(Vertex[] vertices, Set<Edge> set){
        this.vertices = vertices;
        edges = new ArrayList<Edge>();
        for (Edge e : set){
            edges.add(e);
        }
    }

    // Make graph from Adjacency Matrix
    Graph(int[][] matrix){
        graphFromAdjacencyMatrix(matrix);
        Collections.sort(edges); // We sort the edges by increasing weight for kruskal's algorithm.
    }

    // REMEMBER THIS FUNCTION DOESNT WORK FOR DIRECTED GRAPHS. ONLY SEARCHES HALF THE MATRIX
    public void graphFromAdjacencyMatrix(int[][] matrix){
        this.vertices = new Vertex[matrix.length];
        this.edges = new ArrayList<Edge>();

        //Add all vertices with its edges stored internally.
        for (int source = 0; source < matrix.length; source++){
            Vertex newVertex = new Vertex(source); 
            for (int destination = source; destination < matrix[source].length; destination++){
                if (matrix[source][destination] > 0){
                    Edge e = new Edge(source,destination,matrix[source][destination]);
                    newVertex.add(e);
                    this.edges.add(e);
                }
            }
            this.vertices[source] = newVertex;
        } 
    }
}

class Vertex extends ArrayList<Edge>{
    int id;

    Vertex(int id){
        this.id = id;
    }
}

class Edge implements Comparable<Edge>{
    int u;
    int v;
    int w;
    
    Edge(int u, int v, int w){
        this.u = u;
        this.v = v;
        this.w = w;
    }

    public int compareTo(Edge other){
        return new Integer(this.w).compareTo(new Integer(other.w));
    }

    public String toString(){
        return "Vertices: " + u + " -> " + v + ", Weight: " + w;
    }
}

class DisjointSets{
    List<Set<Vertex>> sets;

    DisjointSets(){
        sets = new ArrayList<Set<Vertex>>();
    }
    
    // Makes a singleton set. Stores it in sets.
    public void makeSet(Vertex v){
        Set<Vertex> newSet = new HashSet<Vertex>();
        newSet.add(v); 
        sets.add(newSet);
    }

    // Find which set contains Vertex v
    public Set<Vertex> findSet(Vertex v){
        for (Set<Vertex> set : sets){
            if (set.contains(v)){
                return set;
            }
        }
        return null;
    }

    // Returns the union of two sets. Only the returned set remains in sets.
    public Set<Vertex> union(Set<Vertex> set1, Set<Vertex> set2){
        for (Vertex v : set2){
            set1.add(v);
        }
        sets.remove(set2);
        return set1;
    }
}
