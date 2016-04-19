public class Combination{

    public static void printCombos(int[] arr,int r){
        int data[]=new int[r];
        int n = arr.length;
        combinationUtil(arr, n, r, 0, data, 0);
    }

    private static void combinationUtil(int[] arr, int n, int r, int index, int[] data, int i){
        // Process here
        if (index == r) {
            for (int j=0; j<r; j++){
                System.out.print(data[j]+" ");
            }
            System.out.println("");
            return;
        }
        // When no more elements are there to put in data[]
        if (i >= n)
            return;

        // current is included, put next at next location
        data[index] = arr[i];
        combinationUtil(arr, n, r, index+1, data, i+1);

        // current is excluded, replace it with next (Note that
        // i+1 is passed, but index is not changed)
        combinationUtil(arr, n, r, index, data, i+1);
    }

    public static void main(String[] args){
        int arr[] = {1, 2, 3, 4, 5};
        int r = 3;
        printCombos(arr, r);
    }

}
