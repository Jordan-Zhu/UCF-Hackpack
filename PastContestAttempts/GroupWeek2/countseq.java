import java.util.*;
public class countseq{

    Scanner stdin;
    	int[][] table;
    
    public void doit(){
        stdin = new Scanner(System.in);

        int numCases = Integer.parseInt(stdin.nextLine());

        for (int caseNum = 1; caseNum <= numCases; caseNum++){
            String input = stdin.nextLine();
            String word = stdin.nextLine();

            table = new int[input.length()+1][word.length()+1];

            for (int i = 1; i <= word.length(); i++){
                for (int j = i; j <= input.length(); j++){
                    if (word.charAt(i-1) == input.charAt(j-1)){
                        table[i][j]
                    }
                }
            }
        }
    }

    public static void main(String[] args){

    }
}
