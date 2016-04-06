import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Jordan on 4/5/2016.
 */

class node {
    public int x;
    public int y;
    public int z;

    public node(int x, int y, int z) {
        // set the class's variables to the input variables
        this.x = x;
        this.y = y;
        this.z = z;
    }
}


public class relatives {
    public static void main(String[] args)
    {
        new relatives();
    }

    public relatives()
    {
        Scanner in = new Scanner(System.in);

        int numCases = Integer.parseInt(in.nextLine());

        for(int loop = 0; loop < numCases; loop++)
        {
            solve(in);
        }
    }

    public void BuildTable(int locations)
    {

    }

    public void solve(Scanner in)
    {
        int locations = Integer.parseInt(in.nextLine());

        // Create table to store travel costs.
        node[] nodes = new node[locations];

        // Read in travel costs.
        for(int i = 0; i < locations; i++)
        {
            StringTokenizer tok = new StringTokenizer(in.nextLine());
            int x = Integer.parseInt(tok.nextToken());
            int y = Integer.parseInt(tok.nextToken());
            int p = Integer.parseInt(tok.nextToken());

            nodes[i] = new node(x, y, p);
        }

    }
}
