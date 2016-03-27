import java.math.BigInteger;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Jordan on 3/26/2016.
 */
public class lotto {
    public static final int MAX_N = 10;
    public static final int MAX_M = 2000;
    private BigInteger [][] memo;

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);

        lotto lucky = new lotto();
        lucky.BuildTable();
        lucky.SolveIt(in);
    }

    lotto()
    {
        memo = new BigInteger[MAX_N + 1][MAX_M + 1];
        // initialize all the big nums
        for(int i = 0; i <= MAX_N; i++)
        {
            for(int j = 0; j <= MAX_M; j++)
            {
                memo[i][j] = BigInteger.ZERO;
            }
        }
    }

    // Populate the global array data once, then perform lookups
    void BuildTable()
    {
        BigInteger b;

        // Base case: there are m lists when n = 1
        for(int i = 0; i <= MAX_M; i++)
        {
            memo[1][i] = BigInteger.valueOf((long)i);
        }

        // Inductive case
        for(int i = 2; i <= MAX_N; i++)
        {
            memo[i-1][0] = BigInteger.ZERO;
            for(int j = 1; j <= MAX_M; j++)
            {
                memo[i][j] = BigInteger.ZERO;
                int jj = j / 2;

                for(int k = 1; k <= jj; k++)
                {
                    b = memo[i-1][k];
                    b = b.subtract(memo[i-1][k-1]);
                    b = b.multiply(BigInteger.valueOf((long)(j - 2*k + 1)));
                    memo[i][j] = memo[i][j].add(b);
                }
            }
        }
    }

    void SolveIt(Scanner in)
    {
        int loop = 1;
        BigInteger b;

        while(in.hasNext())
        {
            StringTokenizer strtok = new StringTokenizer(in.nextLine());
            int N = Integer.parseInt(strtok.nextToken());
            int M = Integer.parseInt(strtok.nextToken());

            if((N|M) == 0)
                break;

            b = memo[N][M];

            System.out.printf("Case %d: n = %d, m = %d, # lists = %s\n", loop, N, M, b.toString());

            loop++;
        }
    }
}
