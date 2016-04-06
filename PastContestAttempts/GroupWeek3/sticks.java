import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Jordan on 4/5/2016.
 */
public class sticks {

    private final static int UNKNOWN = -1;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Process each case
        int numCases = Integer.parseInt(in.nextLine());
        for(int loop = 0; loop < numCases; loop++)
        {
            int length = Integer.parseInt(in.nextLine());
            StringTokenizer tok = new StringTokenizer(in.nextLine());
            int numCuts = Integer.parseInt(tok.nextToken());

            // Set up boundaries with endpoints included.
            int[] cuts = new int[numCuts + 2];
            cuts[0] = 0;
            cuts[cuts.length - 1] = length;

            // Read in the distance cuts need to be made.
            for(int i = 1; i <= numCuts; i++)
            {
                cuts[i] = Integer.parseInt(tok.nextToken());
            }

            // Initialize memoization array for DP.
            int[][] dp = new int[cuts.length][cuts.length];
            for(int i = 0; i < dp.length; i++)
            {
                Arrays.fill(dp[i], UNKNOWN);
            }

            System.out.println(solveIt(cuts, 0, cuts.length-1, dp));
        }
    }

    public static int solveIt(int[] cuts, int start, int end, int[][] dp)
    {
        // No cuts to be made.
        if(start >= end-1)
            return 0;

        // Been solved before.
        if(dp[start][end] != UNKNOWN)
            return dp[start][end];

        // Try each split, updating best.
        int best = Integer.MAX_VALUE;
        for(int split = start+1; split < end; split++)
        {
            // When we make a cut, it gives us two sub-problems - the left side and the right side.
            int cost = cuts[end] - cuts[start] + solveIt(cuts, start, split, dp) + solveIt(cuts, split, end, dp);

            if(cost < best)
                best = cost;
        }

        // Store answer and return.
        dp[start][end] = best;
        return best;

    }
}
