import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boilerplate {
    public static void main(String[] args) throws Exception {
        // Read in data.
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(stdin.readLine().trim());

        // Read in multiple numbers from one line.
        StringTokenizer tok = new StringTokenizer(stdin.readLine());

        // Use tokenizer.
        int m = Integer.parseInt(tok.nextToken());
    }
}
