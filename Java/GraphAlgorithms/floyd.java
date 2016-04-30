 package algorithims;
import java.util.*;
public class floyd {
static int G [][];
static int D[][];
static int P[][];
static int vert = 0;
final static int oo = (int) 1e9;
public static void main (String[] args)
{
	Scanner en = new Scanner(System.in);
	 vert = en.nextInt();
	G = new int [vert][vert];

			G   =new int[][] {  {0,oo,3,0},
					{ -2,0,oo,1},
					{ oo,oo,0,5},
					 {oo,4,oo,0}};
		

	floyd();
	for(int start =0 ; start<vert;start++)
	{
		for(int finish =0 ; finish<vert;finish++)
		{
			LinkedList<Integer> path = thispath(start,finish);
			System.out.printf("Length of shortest path from %d to %d: %d%n",start,finish,D[start][finish]);
			if (path != null)
				System.out.printf("   Path: %s%n%n",path);
			else
				System.out.printf("   Path does not exist!%n%n");
			
		}
	}
	en.close();
}
static void floyd()
{   D = new int[vert][vert];
	P = new int[vert][vert];
	for(int i =0; i<vert;i++)
	{
		for(int j =0 ; j<vert;j++)
		{
			G[i][j] = D[i][j];
			if(G[i][j]<oo)
			{
				P[i][j]= i;
			}
			else
			{
			P[i][j] = -1;
			}
		}
}
    for(int k =0; k<vert;k++)
    {
    	for(int i=0;i<vert;i++)
    	{
    		for(int j = 0; j<vert;j++)
    		{
    			if(D[i][j]>D[i][k]+D[k][j] &&D[i][k]<oo && D[k][j]<oo)
    			{
    				D[i][j] = D[i][k]+D[k][j];
    				P[i][j] = P[k][j];
    			}
    		}
    	}
    }
   
}


static LinkedList<Integer> thispath(int start,int finish)
{
	if(!(D[start][finish]<oo))
	{
		return null;
	}
	LinkedList<Integer> path =  new LinkedList<Integer>();
	path.addFirst(finish);
	while(start != finish)
	{
		finish = P[start][finish];
		path.add(finish);
	}
	return path;
}
}
