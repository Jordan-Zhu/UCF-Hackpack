import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Jordan on 3/26/2016.
 */
public class spidey {

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        int numCases = Integer.parseInt(in.nextLine());

        for(int loop = 1; loop <= numCases; loop++)
        {
            StringTokenizer strtok = new StringTokenizer(in.nextLine());
            int v = Integer.parseInt(strtok.nextToken());
            int e = Integer.parseInt(strtok.nextToken());

            // adjList[i] would be a list storing all vertices, vertex i is connected to
            ArrayList[] graph = new ArrayList[v];
            for (int i=0; i<v; i++) graph[i] = new ArrayList<Integer>();

            // Read in edges.
            for (int i=0; i<e; i++) {
                StringTokenizer vertok = new StringTokenizer(in.nextLine());
                int v1 = Integer.parseInt(vertok.nextToken());
                int v2 = Integer.parseInt(vertok.nextToken());
                graph[v1].add(v2);
                graph[v2].add(v1);
            }


            if (web(graph))
                System.out.println("Way to go, Spider-Man!\n");
            else
                System.out.println("It's the end of the world!\n");
        }
    }

    public static boolean web(ArrayList[] graph)
    {
        int v = graph.length;

        // check connection requirements
        for(int i = 0; i < v; i++)
        {
            if(graph[i].size() == 0)
            {
                return false;
            }

            // web cannot connect on the same side
            for(int j = 0; j < graph[i].size(); j++)
            {
                if(i % 2 == (Integer)graph[i].get(j) % 2)
                    return false;
            }

        }

        // check graph connectivity
        boolean[] used = new boolean[v];
        dfs(graph, used, 0);
        return allTrue(used);
    }


    // perform a DFS, marking nodes starting from v
    public static void dfs(ArrayList[] graph, boolean[] used, int v) {
        used[v] = true;
        for (Integer next : (ArrayList<Integer>)graph[v])
            if (!used[next])
                dfs(graph, used, next);
    }

    public static boolean allTrue(boolean[] used)
    {
        for(int i = 0; i < used.length; i++)
        {
            if(!used[i])
                return false;
        }
        return true;
    }
}
