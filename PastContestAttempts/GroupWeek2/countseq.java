import java.util.Scanner;

/**
 * Created by Jordan on 3/26/2016.
 */
public class countseq {
    String seq, subseq;
    long[][] memo;

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
            seq = in.nextLine().trim();
            subseq = in.nextLine().trim();
            System.out.println(countMatches());
        }
    }

    public long countMatches()
    {
        // create the look-up table
        memo = new long[seq.length()+1][subseq.length()+1];

        for(int row = 0; row < memo.length; row++)
        {
            for(int col = 0; col < memo[row].length; col++)
            {
                memo[row][col] = countMatchesFor(row, col);
            }
        }

        return memo[seq.length()][subseq.length()];
    }

    public long countMatchesFor(int seqDigitsLeft, int subseqDigitsLeft)
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

        long result = 0;

        // if sequence at row row and subseq at column col start with
        // the same character, add the value found at [row-1][col-1]
        // to the value just written to [row][col].
        if (currSeqDigit == currSubseqDigit)
            result += memo[seqDigitsLeft - 1][subseqDigitsLeft - 1];

        // In cell [row][col] write the value found at [row-1][col].
        result += memo[seqDigitsLeft - 1][subseqDigitsLeft];

        return result;
    }
}
