import java.util.*;

public class Permutation{

    // Processes all permutations of List arr in the lower if block.
    // Call with k = 0
    public void permute(List<Integer> arr, int k){
        for(int i = k; i < arr.size(); i++){
            Collections.swap(arr, i, k);
            permute(arr, k+1);
            Collections.swap(arr, k, i);
        }

        if (k == arr.size() -1){
            System.out.println(Arrays.toString(arr.toArray()));
        }
    }

    public static void main(String[] args){
        new Permutation().permute(Arrays.asList(3,4,6,2,1), 0);
    }

}
