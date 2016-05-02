public class LongestCommonSubsequence{
    public static void main(String[] args){
    String one = " in ni nat jag dep y togo il ru pur de tuh huh jon stewart";
	String two = " nat in jag dep huh tuh il de tuh de huh jon y stewart";
	System.out.print("LCSS:");
	int temp=common(one,two);
	System.out.print("\nLCSS:"+temp);
}
	
	static int common(String one,String two)
	{
		String[] words = one.split(" ");
		String[] words2 = two.split(" ");
		int table[][] = new int[words.length][words2.length];
		String table2[][] = new String [words.length][words2.length]; 
		for(int k = 0; k<words.length;k++)
		{
			for(int c = 0; c<words2.length;c++)
			{
			table[k][c]=0;	
			}
		}
		for(int i=1;i<words.length;i++)
		{
			for(int j=1;j<words2.length;j++)
			{
				
				if(words[i].equals(words2[j])== true)
					{
						table[i][j] = table[i-1][j-1]+1 ;
						table2[i][j] = "same";
						
					}
				else if(table[i-1][j]<table[i][j-1])
					{
						table[i][j] = table[i][j-1];
						table2[i][j]="j";
					}
					else
					{
						table [i][j] = table[i-1][j];
						table2[i][j] = "i";
					}
				
				}
		}
		int temp = table[words.length-1][words2.length-1];
		printLcss(table2,words,words.length-1,words2.length-1);
		return temp;
		
		
	}
		
	
	static void printLcss (String table2[][],String[] words,int i,int j )
	{
		
	
		if(i ==0 || j ==0)
		{
			return;
		}
		if(table2[i][j].equals("same"))
		{
			
			printLcss(table2,words,i-1,j-1);
			System.out.print(words[i]+" ");
		}
		else if(table2[i][j].equals("i"))
		{
			printLcss(table2,words,i-1,j);
			
		}
		else
		{
			printLcss(table2,words,i,j-1);
			
		}
	}
	
}
