import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Jordan on 3/27/2016.
 */

class target {
    public int x;
    public int y;
    public int p;

    public target(int x, int y, int p) {
        // set the class's variables to the input variables
        this.x = x;
        this.y = y;
        this.p = p;
    }
}

public class robot {
    public static void main(String[] args)
    {
        new robot();
    }

    public robot()
    {
        Scanner in = new Scanner(System.in);

        int numTargets = Integer.parseInt(in.nextLine().trim());

        while(numTargets > 0)
        {
            SolveIt(in, numTargets);

            // Read in targets for next case
            numTargets = Integer.parseInt(in.nextLine().trim());
        }

    }

    // Returns the distance between a and b.
    public static double dist(target a, target b) {
        return Math.sqrt( (a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y) );
    }

    public void BuildTable(int numTargets, target[] locs, double[] bestScore, int[] sumPenalty)
    {
        // We start here, so we already know these
        bestScore[0] = 0;
        bestScore[1] = dist(locs[0], locs[1]) + 1;

        // Determine the best score for target i
        for (int i = 2; i < numTargets+2; i++)
        {
            // A very large number for the longest travel distance.
            // Each segment must take fewer than 1000 seconds and
            // there are at most 100 of them.
            double bestSoFar = 100000;

            // Try each of these as your last ending point.
            for(int j = 0; j < i; j++)
            {
                // This would be your score if you last ended at spot j.
                // Plus the penalty for skipping targets j+1, j+2, ..., i-1.
                // Plus the time it would take to go directly from j to i.
                // Plus the one second we are stopping here.
                double score = bestScore[j] + sumPenalty[i-1] - sumPenalty[j] +
                        dist(locs[j], locs[i]) + 1;

                // Update max.
                if (score < bestSoFar)
                    bestSoFar = score;
            }

            // Set the best score for ending at target i.
            bestScore[i] = bestSoFar;
        }
    }

    public void SolveIt(Scanner in, int numTargets)
    {
        // Make locations array with room for start and end.
        // Every array will have +2 to make room for
        // both the begin and end spots
        target[] locs = new target[numTargets+2];

        // We start here.
        locs[0] = new target(0, 0, 0);

        // keep track of the sums of all the penalties up
        // target i.
        int[] sumPenalty = new int[numTargets + 2];
        sumPenalty[0] = 0;

        for(int i = 1; i <= numTargets; i++)
        {
            // Read in the targets.
            StringTokenizer strtok = new StringTokenizer(in.nextLine());
            int x = Integer.parseInt(strtok.nextToken());
            int y = Integer.parseInt(strtok.nextToken());
            int p = Integer.parseInt(strtok.nextToken());

            locs[i] = new target(x, y, p);
            sumPenalty[i] = sumPenalty[i-1] + p;
        }

        // We end here.
        locs[numTargets+1] = new target(100, 100, 0);
        sumPenalty[numTargets+1] = sumPenalty[numTargets];

        // The value stored in bestScore[i] will represent the
        // fastest we can getto target i, if that was our fastest spot
        double[] bestScore = new double[numTargets + 2];

        // Compute the best score
        BuildTable(numTargets, locs, bestScore, sumPenalty);

        // Print the best score and add tolerance for rounding purposes
        System.out.printf("%.3f\n", bestScore[numTargets+1]+1e-9);

    }
}
