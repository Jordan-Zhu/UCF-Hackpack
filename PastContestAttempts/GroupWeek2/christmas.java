import java.util.*;
public class christmas{
    long[] memo;
    long[] sumMemo;

    public void doit(){
        Scanner stdin = new Scanner(System.in);
        memo = new long[1000001];
        sumMemo = new long[1000001];
        memo[0] = 0;
        memo[1] = 1;
        sumMemo[0] = 1;
        sumMemo[1] = 1;

        for (int i = 2; i <= 1000000; i++){
            sumMemo[i] = sumMemo[i-1] + i;
            memo[i] = memo[i-1] + sumMemo[i] ;
        }

        for (;;){
            int n = Integer.parseInt(stdin.nextLine());
            if (n == 0) break;
            System.out.println(memo[n]);
        } 
    }

    public long sum(int n){
        if (n <= 1){
            return 1;
        }

        if (sumMemo[n-1] != 0){
            return sumMemo[n-1] + n; 
        } else{
            sumMemo[n-1] = sum(n-1);
            return sumMemo[n-1] + n;
        }
    }

    public static void main(String[] args){
        new christmas().doit();
    }
}
