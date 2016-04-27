import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by Jordan on 4/26/2016.
 */
public class haircut {
    private static final long MAX = (long) 1e16;

    public static void main(String[] args) throws Exception {
        haircut haircut = new haircut();
        haircut.run();
    }

    public void run() throws Exception {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

        int numCases = Integer.parseInt(stdin.readLine());
        for(int loop = 1; loop <= numCases; loop++) {
            StringTokenizer tok = new StringTokenizer(stdin.readLine());
            int b = Integer.parseInt(tok.nextToken());
            int n = Integer.parseInt(tok.nextToken());

            tok = new StringTokenizer(stdin.readLine());
            int [] m = new int[b];
            for(int i = 0; i < b; i++)
            {
                m[i] = Integer.parseInt(tok.nextToken());
            }

            long left = -1, right = MAX;
            while(right - left > 1)
            {
                long mid = (left + right) / 2;
                long sum = 0;
                for(int i = 0; i < b; i++)
                {
                    sum += 1 + mid / m[i];
                    if (sum > MAX)
                    {
                        sum = MAX;
                    }
                }

                if(sum < n)
                {
                    left = mid;
                }
                else
                    right = mid;
            }

            long countStart = 0;
            long sum = 0;
            for(int i = 0; i < b; i++)
            {
                countStart += 1 + left / m[i];
                if(sum > MAX)
                {
                    sum = MAX;
                }
            }
            if(left == 0)
            {
                countStart = 0;
            }
            boolean found = false;
            for(int i = 0; i < b; i++)
            {
                if(right % m[i] == 0)
                {
                    countStart++;
                }
                if(countStart == n)
                {
                    System.out.println("Case #" + loop + ": " + (i + 1));
                    found = true;
                    break;
                }
            }
            if (!found)
            {
                throw new AssertionError();
            }
//            System.err.printf("%d/%d done\n", (loop + 1), numCases);
        }
    }
}
