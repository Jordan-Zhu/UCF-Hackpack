import java.util.Scanner;

/**
 * Created by Jordan on 3/26/2016.
 */
public class countseq {
    String seq, subseq;
    int[][] memo;

    public static void main(String[] args)
    {
        new countseq();
    }

    public countseq() {
        Scanner in = new Scanner(System.in);
        SolveIt(in);
    }

    public void SolveIt(Scanner in)
    {
        int numCases = Integer.parseInt(in.nextLine());

        for(int loop = 0; loop < numCases; loop++)
        {
            seq = in.nextLine();
            subseq = in.nextLine();
            System.out.println(countMatches());
        }
    }

    public int countMatches()
    {
        // create the look-up table
        memo = new int[seq.length()+1][subseq.length()+1];

        for(int row = 0; row < memo.length; row++)
        {
            for(int col = 0; col < memo[row].length; col++)
            {
                memo[row][col] = countMatchesFor(row, col);
            }
        }

        return memo[seq.length()][subseq.length()];
    }

    public int countMatchesFor(int seqDigitsLeft, int subseqDigitsLeft)
    {
        // if the subsequence is empty a match is found (no letters left to match)
        // and we return 1
        if(subseqDigitsLeft == 0)
            return 1;

        // if the input sequence is empty, we've depleted our letters and can't possibly
        // find a match thus we return 0
        if(seqDigitsLeft == 0)
            return 0;

        char currSeqDigit = seq.charAt(seq.length()-seqDigitsLeft);
        char currSubseqDigit = subseq.charAt(subseq.length()-subseqDigitsLeft);

        int result = 0;

        // if sequence at row row and subseq at column col start with
        // the same character, add the value found at [row-1][col-1]
        // to the value just written to [row][col].
        if (currSeqDigit == currSubseqDigit)
            result += memo[seqDigitsLeft - 1][subseqDigitsLeft - 1];

        // In cell [row][col] write the value found at [row-1][col].
        result += memo[seqDigitsLeft - 1][subseqDigitsLeft];

        return result;
    }

//    public static int lcsdyn(String x, String y)
//    {
//        int i, j;
//        int lenx = x.length();
//        int leny = y.length();
//        int[][] table = new int[lenx + 1][leny + 1];
//
//        // initialize the table that will store LCS's of all the prefix strings
//        // this initialization case is for all empty string cases
//        for(i = 0; i <= lenx; i++)
//            table[i][0] = 0;
//        for(i = 0; i <= leny; i++)
//            table[0][i] = 0;
//
//        // Fill in each LCS value in order from top row to bottom row,
//        // moving left to right
//        for(i = 1; i <= lenx; i++)
//        {
//            for(j = 1; j <= leny; j++)
//            {
//                // if the last characters of the prefix match, add one to former value
//                if(x.charAt(i-1) == y.charAt(j-1))
//                    table[i][j] = 1+table[i-1][j-1];
//
//                // otherwise, take maximum of the two adjacent cases
//                else
//                    table[i][j] = Math.max(table[i][j-1], table[i-1][j]);
//
//                System.out.print(table[i][j]+" ");
//            }
//            System.out.println();
//        }
//
//        // our answer
//        return table[lenx][leny];
//    }
}
