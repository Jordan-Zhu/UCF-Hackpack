import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by Jordan on 4/26/2016.
 */
public class tri {
    private static final long MAX = (long) 1e16;
    final public static int[][][] C_DELTA =
            { {{-1,0}, {-1,1}}, {{0,-1},
            {-1,-1}, {-1,0}, {-1,1}},
            {{0,-1}, {-1,-1}, {-1,0}}
    };

    public static void main(String[] args) throws Exception
    {
        tri tri = new tri();
        tri.run();
    }

    public void run() throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int loop = 1;

        while(n != 0)
        {
            // Read in grid data.
            long[][] grid = new long[n][3];
            for(int i = 0; i < n; i++)
            {
                StringTokenizer tok = new StringTokenizer(in.readLine());
                for(int j = 0; j < 3; j++)
                {
                    grid[i][j] = Integer.parseInt(tok.nextToken());
                }
            }

            // DP.
            long[][] dp = new long[n][3];
            dp[0][1] = grid[0][1];
            dp[0][2] = grid[0][1] + grid[0][2];

            // Run DP over positions [i][j].
            for(int i = 1; i < n; i++)
            {
                for(int j = 0; j < C_DELTA.length; j++)
                {
                    // Default setting we will overwrite.
                    dp[i][j] = MAX;
                    for(int k = 0; k < C_DELTA[j].length; k++)
                    {
                        // Square we are coming from.
                        int buildx = i + C_DELTA[j][k][0];
                        int buildy = j + C_DELTA[j][k][1];

                        // Cannot build off square (0, 0).
                        if (buildx == 0 && buildy == 0)
                            continue;
                        dp[i][j] = Math.min(dp[i][j], dp[buildx][buildy] + grid[i][j]);
                    }
                }
            }

            // Print output.
            System.out.println(loop + ". " + dp[n-1][1]);

            // Next case.
            loop++;
            n = Integer.parseInt(in.readLine());
        }
    }
}
