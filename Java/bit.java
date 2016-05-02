/**
 * Binary Index Tree: A data structure providing efficient methods for calculating
 * and manipulating of the prefix sums of a table of values, or a cumulative frequency
 * array.
 */
public class bit {
    public long[] cumfreq;

    // Do indexes 1 to n.
    public bit(int n) {
        int size = 1;
        // Ensure array is a multiple of 2 plus 1.
        while (size < n) {
            size <<= 1;
        }
        n = size;

        cumfreq = new long[n + 1];
    }

    // Uses 1 based indexing. Index 0 is the root of the tree.
    public void add(int index, long val) {
        while (index < cumfreq.length) {
            cumfreq[index] += val;
            // Index goes to nearest 2-bit.
            index += Integer.lowestOneBit(index);
        }
    }

    // Returns the sum of everything up to index.
    public long sum(int index) {
        long ans = 0;
        while (index > 0) {
            ans += cumfreq[index];
            index -= Integer.lowestOneBit(index);
        }
        return ans;
    }

    // Uses 1 based indexing.
    public long sum(int low, int high) {
        return sum(high) - sum(low - 1);
    }

    // Return the total number of items in the BIT.
    public long all() {
        return sum(cumfreq.length - 1);
    }

    // Return the total number of items in the BIT above index.
    public long atOrAbove(int index) {
        long sub = 0;
        if (index > 0) {
            sub = sum(index - 1);
        }
        return all() - sub;
    }

    // Return the total number of items in the BIT below index.
    public long below(int index) {
        return sum(index - 1);
    }

    public static void main(String[] args) {

    }
}
