/**
 * Make Change: Given a positive integer n, how many ways can
 * we make change for n cents using pennies, nickels, dimes,
 * and quarters? Solved using DP.
 */
public class MakeChange {

    // To make change for n cents using the largest coin d.
    public static int makeChangeDyn(int n, int d) {
        // Simple cases.
        if (n < 0) {
            return 0;
        } else if ((n >= 0) && (n < 5)) {
            return 1;
        }

        // Build table.
        else {
            int[] denominations = {1, 5, 10, 25};
            int[][] table = new int[4][n + 1];

            // Initialize table with starting values.
            for (int i = 0; i < n + 1; i++) {
                table[0][i] = 1;
            }
            for (int i = 0; i < 5; i++) {
                table[1][i] = 1;
                table[2][i] = 1;
                table[3][i] = 1;
            }
            for (int i = 5; i < n + 1; i++) {
                table[1][i] = 0;
                table[2][i] = 0;
                table[3][i] = 0;
            }

            // Fill in table.
            for (int i = 1; i < 4; i++) {
                for (int j = 5; j < n + 1; j++) {
                    for (int k = 0; k <= i; k++) {
                        if (j >= denominations[i]) {
                            table[i][j] = table[i - 1][j] + table[i][j - denominations[k]];
                        } else {
                            table[i][j] = table[i - 1][j];
                        }
                    }
                }
            }
            return table[d][n];
        }
    }
}
