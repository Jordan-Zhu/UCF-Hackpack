import java.util.*;
public class spidey{
    Scanner stdin;
    boolean[][] adj;
    boolean endOfWorld;

    public void doit(){
        stdin = new Scanner(System.in);
        int numCases = Integer.parseInt(stdin.nextLine());
        for (int caseNum = 1; caseNum <= numCases; caseNum++){
            endOfWorld = false;
            StringTokenizer strTok = new StringTokenizer(stdin.nextLine());
            int e = Integer.parseInt(strTok.nextToken());
            int v = Integer.parseInt(strTok.nextToken());
            List<edge> edges = new ArrayList<edge>();
            edge[] edges2 = new edge[v];

            if (v < e-1){
                System.out.println("It's the end of the world!");
            }else{
                adj = new boolean[v][v];

                for (int i = 0; i < v; i++){
                    StringTokenizer strTok2 = new StringTokenizer(stdin.nextLine());
                    int a = Integer.parseInt(strTok2.nextToken());
                    int b = Integer.parseInt(strTok2.nextToken());

                    if (a % 2 == 0 && b % 2 == 0) {
                        endOfWorld = true;
                        break;
                    }
                    if (a % 2 == 1 && b % 2 == 1) {
                        endOfWorld = true;
                        break;
                    }

                    adj[a][b] = true;
                    adj[b][a] = true;
                    edge newEdge = new edge(a,b, 0);
                    edges2[edges2.length-1] = newEdge;
                }

                if (endOfWorld){
                    System.out.println("It's the end of the world!");
                }else{
                    int ans = kruskals.mst(edges2,edges2.length);
                    if (ans != -1){
                        System.out.println("Way to go, Spider-Man!");
                    }else{
                        System.out.println("It's the end of the world!");
                    }
                }
            }
        }
    }

    public static void main(String[] args){
        new spidey().doit();
    }
}

class dset {

	public int[] parent;
	public int[] height;
	public int n;

	public dset(int size) {
		parent = new int[size];
		height = new int[size];
		for (int i=0; i<size; i++)
			parent[i] = i;
	}

	public int find(int v) {
		if (parent[v] == v) return v;
		parent[v] = find(parent[v]);
		height[v] = 1;
		return parent[v];
	}

	public boolean union(int v1, int v2) {

		int p1 = find(v1);
		int p2 = find(v2);
		if (p1 == p2) return false;

		if (height[p2] < height[p1]) parent[p2] = p1;
		else if (height[p1] < height[p2]) parent[p1] = p2;
		else {
			parent[p2] = p1;
			height[p1]++;
		}
		return true;
	}
}

class edge implements Comparable<edge> {

	public int v1;
	public int v2;
	public int w;

	public edge(int a, int b, int weight) {
		v1 = a;
		v2 = b;
		w = weight;
	}

	public int compareTo(edge other) {
		return this.w - other.w;
	}
}

class kruskals {

	public static int mst(edge[] list, int n) {
		Arrays.sort(list);

		dset trees = new dset(n);
		int numEdges = 0, res = 0;

		// Consider edges in order.
		for (int i=0; i<list.length; i++) {

			// Try to put together these two trees.
			boolean merged = trees.union(list[i].v1, list[i].v2);
			if (!merged) continue;

			// Bookkeepping.
			numEdges++;
			res += list[i].w;
			if (numEdges == n-1) break;
		}

		// -1 indicates no MST, so not connected.
		return numEdges == n-1 ? res : -1;
	}
}
