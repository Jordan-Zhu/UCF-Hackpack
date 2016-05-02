import java.util.*;
public class NetworkFlow{

    private final static boolean DEBUG = false;
    private final static boolean PRINTPATH = false; 

    private Edge[][] adjMat;
    private int source;
    private int destination;

    NetworkFlow(int[][] matrix, int start, int end){
        source = start;
        destination = end;
        adjMat = new Edge[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[i].length; j++){
                if (matrix[i][j] > 0){
                    adjMat[i][j] = new Edge(matrix[i][j]);
                }else{
                    adjMat[i][j] = null;
                }
            }
        }
    }

    public ArrayList<Direction> findAugmentingPath(){
        Direction[] prev = new Direction[adjMat.length];
        boolean[] inQueue = new boolean[adjMat.length];

        prev[source] = new Direction(-1, true);

        Queue<Integer> bfs_queue = new LinkedList<Integer>();
        bfs_queue.offer(new Integer(source));
        inQueue[source] = true;

        while(!bfs_queue.isEmpty()){
            int next = bfs_queue.poll();

            // Add forward edges to queue.
            for (int i = 0; i < adjMat.length; i++){
                if (!inQueue[i] && adjMat[next][i] != null && adjMat[next][i].maxPushForward() > 0){
                    bfs_queue.offer(new Integer(i));
                    inQueue[i] = true;
                    prev[i] = new Direction(next, true);
                }
            }

            // Add backward edges
            for (int i = 0; i < adjMat.length; i++){
                if (!inQueue[i] && adjMat[i][next] != null && adjMat[i][next].maxPushBackward() > 0){
                    bfs_queue.offer(new Integer(i));
                    inQueue[i] = true;
                    prev[i] = new Direction(next, false);
                }
            }
        }

        if (!inQueue[destination]){
            return null;
        }

        ArrayList<Direction> path = new ArrayList<Direction>();
        Direction place = prev[destination];
        Direction dummy = new Direction(destination, true);
        path.add(dummy);
         
        // Build the path backwards.
        while(place.previous != -1){
            path.add(place);
            place = prev[place.previous];
        }

        Collections.reverse(path);
        return path;
    }

    public int getMaxFlow(){
        int flow = 0;

        ArrayList<Direction> nextPath = findAugmentingPath();

        if (DEBUG || PRINTPATH){
            System.out.println("Found one augmenting path.");
            for (int i = 0; i < nextPath.size(); i++){
                System.out.print(nextPath.get(i)+" ");
            }
            System.out.println();
        }

        while (nextPath != null){
            int thisFlow = Integer.MAX_VALUE;
            for (int i = 0; i < nextPath.size()-1; i++){
                if (nextPath.get(i).forward){
                    thisFlow = Math.min(thisFlow, 
                            adjMat[nextPath.get(i).previous][nextPath.get(i+1).previous].maxPushForward());
                }else{
                    thisFlow = Math.min(thisFlow, 
                            adjMat[nextPath.get(i+1).previous][nextPath.get(i).previous].maxPushBackward());
                }
            }

            for (int i = 0; i < nextPath.size()-1; i++){
                if (nextPath.get(i).forward){
                    adjMat[nextPath.get(i).previous][nextPath.get(i+1).previous].pushForward(thisFlow);
                }else{
                    adjMat[nextPath.get(i+1).previous][nextPath.get(i).previous].pushBack(thisFlow);
                }
            }

            if (DEBUG || PRINTPATH){
                System.out.println("Adding " + thisFlow);
            }

            flow += thisFlow;
            nextPath = findAugmentingPath();
            if (nextPath != null && (DEBUG || PRINTPATH)) {
                System.out.println("Found another augmenting path.");
                for (int i = 0; i < nextPath.size(); i++){
                    System.out.print(nextPath.get(i) + " ");
                }
                System.out.println();
            }
        }
        return flow;
    }

    public static void main(String[] args){
        int[][] matrix = new int[6][6];

        matrix[0][1] = 16;
        matrix[0][2] = 13;
        matrix[1][2] = 10;
        matrix[2][1] =  4;
        matrix[1][3] = 12;
        matrix[3][2] =  9;
        matrix[2][4] = 14;
        matrix[3][5] = 20;
        matrix[4][3] =  7;
        matrix[4][5] =  4;
    
        NetworkFlow networkFlow = new NetworkFlow(matrix, 0, 5);
        int answer = networkFlow.getMaxFlow();
        System.out.println("The flow is " + answer);
                            
    }
}

class Direction{
    int previous;
    boolean forward;

    Direction(int previousNode, boolean dir){
        this.previous = previousNode;
        this.forward = dir;
    }

    public String toString(){
        if (forward){
            return "" + previous + "->";
        }else{
            return "" + previous + "<-";
        }
    }
}

class Edge{
    int capacity;
    int flow;

    Edge(int capacity){
        this.capacity = capacity; 
        this.flow = 0;
    }

    public int maxPushForward(){
        return capacity - flow;
    }

    public int maxPushBackward(){
        return flow;
    }

    public boolean pushForward(int moreFlow){
        if (flow+moreFlow <= capacity){
            flow += moreFlow;
            return true;
        }
        return false;
    }

    public boolean pushBack(int lessFlow){
        if (lessFlow > flow){
            flow -= lessFlow;
            return true;
        }
        return false;
    }
}



