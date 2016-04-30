
public class Knapsack {
    static int knapsack(int capacity, int[] weight, int[] val, int numItems)
    {
        int [][] K = new int[numItems + 1][capacity + 1];

        // Build table K[][] in bottom up manner.
        for(int i = 0; i <= numItems; i++)
        {
            for(int w = 0; w <= capacity; w++)
            {
                if(i == 0 || w == 0) {
                    K[i][w] = 0;
                }
                else if(weight[i - 1] <= w) {
                    K[i][w] = Math.max(val[i - 1] + K[i - 1][w - weight[i - 1]], K[i - 1][w]);
                }
                else {
                    K[i][w] = K[i - 1][w];
                }
            }
        }
        return K[numItems][capacity];
    }

    public static void main(String[] args)
    {
        int capacity = 150;
        int items = 5;
        int [] wts = {1, 56, 42, 78, 12};
        int [] val = {50, 30, 20, 10, 50};
        System.out.printf("Max value that can be put in a knapsack of capacity %d is: %d", capacity, knapsack(capacity, wts, val, items));

    }
}
