public class MatrixChainMultiplication{
    static int oo= (int)1e9;
    public static void main(String[] args){
        int [] dimension = {2,4,2,3,1,4};
		int size= dimension.length-1;
		int [][] k = new int [size][size];
		int temp = matrixchaining(dimension,k,size);
		System.out.print(temp);
    }
    static int matrixchaining(int[]x,int[][]y,int z)
	{
		
		for(int i =0;i<z;i++)
		{
			for(int j=0;j<z;j++)
			{	
			y[i][j]= oo; 
			}
			y[i][i]=0;
		}
		for(int i = 0;i<=z;i++)
			{
			for(int j =0;j<=(z-i);j++)
				{
				 for(int k = j; k < (j+i-1);k++)
				 {
					 if(y[j][j+i-1]> y[j][k]+y[k+1][j+i-1]+(x[j]*x[k+1]*x[i+j]))
					 {
						 
						y[j][j+i-1] = y[j][k]+y[k+1][j+i-1]+(x[j]*x[k+1]*x[i+j]);
					 }
				 }
				}
				
			}
		return y[0][z-1];
				}
}
