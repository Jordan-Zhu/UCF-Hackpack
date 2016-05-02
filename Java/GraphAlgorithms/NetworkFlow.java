// Arup Guha
// 8/24/2011
// Uses Edmunds-Karp Algorithm to calculate Maximum Flow in a Flow Network.

import java.util.*;

public class NetworkFlow{

    private final static boolean DEBUG = false;
    private final static boolean PRINTPATH = false;

    Graph graph;
    private int source;
    private int destination;

    // All positive entries in flows should represent valid flows
    // between vertices. All other entries must be 0 or negative.
    NetworkFlow(Graph graph, int source, int destination){
        this.graph = graph;
        this.source = source; 
        this.destination = destination;
    }

    public ArrayList<direction> findAugmentingPath() {

        // This will store the previous node visited in the BFS.
        for (int i=0; i < graph.vertices.length; i++)
            graph.vertices[i].inQueue = false;

        // The source has no previous node.
        graph.vertices[source].dir = new direction(-1, true);

        LinkedList<Integer> bfs_queue = new LinkedList<Integer>();
        bfs_queue.offer(new Integer(source));
        graph.vertices[source].inQueue = true;

        while (!bfs_queue.isEmpty()) {

            // Add all the new neighbors of the current node.
            Integer next = bfs_queue.poll();
            if (DEBUG) System.out.println("Searching " + next);

            // Find all neighbors and add into the queue. These are forward edges.
            for (int i=0; i<graph.edges.length; i++)
                if (!graph.vertices[i].inQueue && graph.edges[next][i] != null && graph.edges[next][i].maxPushForward() > 0) {
                    bfs_queue.offer(new Integer(i));
                    graph.vertices[i].inQueue = true;
                    graph.vertices[i].dir = new direction(next, true);
                }

            // Now look for back edges.
            for (int i=0; i<graph.edges.length; i++)
                if (!graph.vertices[i].inQueue && graph.edges[i][next] != null && graph.edges[i][next].maxPushBackward() > 0) {
                    bfs_queue.offer(new Integer(i));
                    graph.vertices[i].inQueue = true;
                    graph.vertices[i].dir = new direction(next, false);
                }
        }

        if (!graph.vertices[destination].inQueue){
            return null;
        }

        ArrayList<direction> path = new ArrayList<direction>();
        direction place = graph.vertices[destination].dir;
        direction dummy = new direction(destination, true);
        path.add(dummy);

        // Build the path backwards.
        while (place.prev != -1) {
            path.add(place);
            place = graph.vertices[place.prev].dir;
        }

        // Reverse it now.
        Collections.reverse(path);

        return path;
    }

    // Run the Max Flow Algorithm here.
    public int getMaxFlow() {

        int flow = 0;

        ArrayList<direction> nextpath = findAugmentingPath();

        if (DEBUG || PRINTPATH) {
            System.out.println("Found one augmenting path.");
            for (int i=0; i<nextpath.size(); i++)
                System.out.print(nextpath.get(i)+" ");
            System.out.println();
        }

        // Loop until there are no more augmenting paths.
        while (nextpath != null) {

            // Check what the best flow through this path is.
            int this_flow = Integer.MAX_VALUE;
            for (int i=0; i<nextpath.size()-1; i++) {

                if (nextpath.get(i).forward) {
                    this_flow = Math.min(this_flow, graph.edges[nextpath.get(i).prev][nextpath.get(i+1).prev].maxPushForward());
                }
                else {
                    this_flow = Math.min(this_flow, graph.edges[nextpath.get(i+1).prev][nextpath.get(i).prev].maxPushBackward());
                }
            }

            // Now, put this flow through.
            for (int i=0; i<nextpath.size()-1; i++) {

                if (nextpath.get(i).forward) {
                    graph.edges[nextpath.get(i).prev][nextpath.get(i+1).prev].pushForward(this_flow);
                }
                else {
                    graph.edges[nextpath.get(i+1).prev][nextpath.get(i).prev].pushBack(this_flow);
                }
            }

            // Add this flow in and then get the next path.
            if (DEBUG || PRINTPATH) System.out.println("Adding "+this_flow);
            flow += this_flow;
            nextpath = findAugmentingPath();
            if (nextpath != null && (DEBUG || PRINTPATH)) {

                System.out.println("Found another augmenting path.");
                for (int i=0; i<nextpath.size(); i++)
                    System.out.print(nextpath.get(i)+" ");
                System.out.println();
            }

        }

        return flow;
    }

    // Test my algorithm!
    public static void main(String[] args) {

        int[][] matrix = new int[6][6];
        matrix[0][1] = 16;
        matrix[0][2] = 13;
        matrix[1][2] = 10;
        matrix[2][1] = 4;
        matrix[1][3] = 12;
        matrix[3][2] = 9;
        matrix[2][4] = 14;
        matrix[3][5] = 20;
        matrix[4][3] = 7;
        matrix[4][5] = 4;

        Graph graph = new Graph(matrix);

        networkflow mine = new networkflow(graph, 0, 5);

        int answer = mine.getMaxFlow();
        System.out.println("The flow is "+answer);

    }

}

class Graph{
    Vertex[] vertices;
    Edge[][] edges;

    Graph(int[][] matrix){
        graphFromAdjacencyMatrix(matrix);

    }

    public void graphFromAdjacencyMatrix(int[][] matrix){
        vertices = new Vertex[matrix.length];
        edges = new Edge[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++){
            Vertex v = new Vertex();
            for (int j = 0; j < matrix[i].length; j++){
                if (matrix[i][j] > 0){
                    edges[i][j] = new Edge(matrix[i][j]);

                }else{
                    edges[i][j] = null;

                }

            }
            vertices[i] = v;

        }

    }

}

class Vertex{
    direction dir = null;
    boolean inQueue = false;

    Vertex(){}

    Vertex(direction dir, boolean inQueue){
        this.dir = dir;
        this.inQueue = inQueue;

    }

}

class Direction {

    public int prev;
    public boolean forward;

    public direction(int node, boolean dir) {
        prev = node;
        forward = dir;
    }

    public String toString() {
        if (forward)
            return "" + prev + "->";
        else
            return "" + prev + "<-";
    }
}

class Edge{

    private int capacity;
    private int flow;

    public Edge(int cap) {
        capacity = cap;
        flow = 0;
    }

    public int maxPushForward() {
        return capacity - flow;
    }

    public int maxPushBackward() {
        return flow;
    }

    public boolean pushForward(int moreflow) {

        // We can't push through this much flow.
        if (flow+moreflow > capacity)
            return false;

        // Push through.
        flow += moreflow;
        return true;
    }

    public boolean pushBack(int lessflow) {

        // Not enough to push back on.
        if (flow < lessflow)
            return false;

        flow -= lessflow;
        return true;
    }
}
