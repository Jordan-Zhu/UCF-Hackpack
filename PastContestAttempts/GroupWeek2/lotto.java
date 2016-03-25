import java.util.*;
public class lotto{

    Scanner stdin;
    int counter;
    int caseNum = 0;
    int N;
    int M;

    
    public void doit(){
        Scanner stdin = new Scanner(System.in);
        for (;;){
            caseNum++;
            counter = 0;
            StringTokenizer strTok = new StringTokenizer(stdin.nextLine());
            N = Integer.parseInt(strTok.nextToken());
            M = Integer.parseInt(strTok.nextToken());

            if (N == 0 && M == 0) 
                break;

            runCombos();
            System.out.println("Case " + caseNum + ": n = " + N + ", m = " + M + ", # lists = " + counter);
        }
    }
    
    public void permute(int[] arr, int k){
        for(int i = k; i < arr.length; i++){
            arr = swap(arr, i, k);
            permute(arr, k+1);
            arr = swap(arr, k, i);
        }

        if (k == arr.length-1){
            if (isCounted(arr)){
                counter++;
            }
        }
    }

    public void runCombos() {
        boolean[] items = new boolean[M];
        combination(items, 0, M);
    }

    public void combination(boolean subset[], int k, int n) {
        if (k == n) {
            int[] array = getIncluded(subset);
            if (array != null){
                // System.out.println(Arrays.toString(array));
                if(array.length == N){
                    permute(array, 0);
                }
            }
            
        } else {
            combination(subset, k+1, n);

            subset[k] = true;
            combination(subset, k+1, n);
            subset[k] = false;
        }
    }

    public void printSubsets(boolean subset[], int k) {
        int[] array = getIncluded(subset);
        if (getIncluded(subset) != null){
            for (int i=0; i<k; i++)
                if (subset[i]){
                    System.out.printf("%d ", i+1);
                }
            System.out.println();
        }
    }

    public int[] getIncluded(boolean subset[]){
        int ans = 0;
        int[] array = new int[N];
        int n = 0;
        try{
            for (int i = 0; i < subset.length; i++){
                if (subset[i]){
                    ans++;
                    array[n++] = i+1;
                }
            }
        }catch(Exception e){
            return null;
        }
        if (ans != N) return null;
        return array;
    }

    public boolean isCounted(int[] arr){
        for (int i = 1; i < arr.length; i++){
            if (arr[i] < 2*arr[i-1]){
                return false;
            }
        }
        return true;
    }

    public int[] swap(int[] arr, int i, int k){
        int tmp = arr[i];
        arr[i] = arr[k];
        arr[k] = tmp;
        return arr;
    }
    

    public static void main(String[] args){
        new lotto().doit();
    }
}
