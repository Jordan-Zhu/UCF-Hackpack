import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Jordan on 4/23/2016.
 */
public class bullseye {
    BigInteger two = new BigInteger("2");

    public static void main(String[] args) throws Exception {
        bullseye bullseye = new bullseye();
        bullseye.run();

//        int cases = Integer.parseInt(in.nextLine());
//
//        for(int loop = 1; loop <= cases; loop++)
//        {
//            StringTokenizer tok = new StringTokenizer(in.nextLine());
//            long r = Long.parseLong(tok.nextToken());
//            long t = Long.parseLong(tok.nextToken());
//        }
//
    }

    private void run() throws Exception {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(stdin.readLine());
        for(int loop = 1; loop <= T; loop++)
        {
            System.out.printf("Case #%d: ", loop);
            String[] lines = stdin.readLine().split("\\s");
            long r = Long.parseLong(lines[0]);
            long paint = Long.parseLong(lines[1]);

            String ret = solve(r, paint);
            System.out.println(ret);
        }
    }

    private String solve(long r, long paint) {
        BigInteger rr = new BigInteger(Long.toString(r));
        BigInteger tt = new BigInteger(Long.toString(paint));
        BigInteger left = BigInteger.ZERO;
        BigInteger right = tt;

        while(left.compareTo(right) < 0)
        {
            BigInteger mid = (right.add(left).add(BigInteger.ONE).divide(two));

            if(check(mid, rr, tt)) {
                left = mid;
            }
            else
            {
                right = mid.subtract(BigInteger.ONE);
            }
        }
        return left.toString();
    }

    private boolean check(BigInteger x, BigInteger r, BigInteger paint) {
        return x.multiply(
                x.multiply(two).add(
                        r.multiply(two).subtract(new BigInteger("1"))
                )
        ).compareTo(paint) <= 0;
    }

//    public static long[] drawRings()
//    {
//        long[] rings = new long[(int)1e19];
//
//        for(int i = 1; i < 20; i++)
//        {
//            long paint = (long)(Math.pow(i, 2) - Math.pow(i - 1, 2));
//            rings[i] = paint;
//            System.out.println("rings[" + i + "] = " + rings[i]);
//        }
//
//        return rings;
//    }
}
