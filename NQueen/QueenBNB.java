import java.util.Scanner;

public class QueenBNB {

	int N;
	void setN(int n)
	{
		this.N=n;
	}
	void printSolution(int board[][])
	{
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<N;j++)
			{
				System.out.print(" "+board[i][j]+" ");
			}
			System.out.println();
		}
	}
	boolean isSafe(int row,int col,int slashCode[][],int backSlashCode[][],boolean rowLookUp[],boolean
			slashCodeLookUp[],boolean backSlashCodeLookUp[])
	{
		if(slashCodeLookUp[slashCode[row][col]] || backSlashCodeLookUp[backSlashCode[row][col]] || rowLookUp[row])
			return false;
		return true;
	}
	boolean SolveNQUtil(int board[][],int col,boolean rowLookUp[],int slashCode[][],int backSlashCode[][]
			, boolean slashCodeLookUp[],boolean backSlashCodeLookUp[])
	{
		
		if(col>=N)
			return true;
		
		for(int i=0;i<N;i++)
		{
			if(isSafe(i,col,slashCode,backSlashCode,rowLookUp,slashCodeLookUp,backSlashCodeLookUp))
			{
				board[i][col]=1;
				rowLookUp[i]=true;
				slashCodeLookUp[slashCode[i][col]]=true;
				backSlashCodeLookUp[backSlashCode[i][col]]=true;
				if(SolveNQUtil(board, col+1, rowLookUp, slashCode, backSlashCode, slashCodeLookUp, backSlashCodeLookUp))
				{
					return true;
				}
				board[i][col]=0;
				rowLookUp[i]=false;
				slashCodeLookUp[slashCode[i][col]]=false;
				backSlashCodeLookUp[backSlashCode[i][col]]=false;
				
			}
		}
		return false;
		
	}
	void solve(int n)
	{
		int board[][]=new int[n][n];
		
		int slashCode[][]=new int[N][N];
		int backSlashCode[][]=new int[N][N];
		
		boolean rowLookUp[]=new boolean[N];
		boolean slashCodeLookUp[]=new boolean[2*N-1];
		boolean backSlashCodeLookUp[]=new boolean[2*N-1];
		
		for(int i=0;i<N;i++)
		{
			rowLookUp[i]=false;
		}
		for(int i=0;i<2*N-1;i++)
		{
			
			slashCodeLookUp[i]=false;
			backSlashCodeLookUp[i]=false;
		}
		
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<N;j++)
			{
				slashCode[i][j]=i+j;
				backSlashCode[i][j]=i-j+(n-1);
			}
		}
		
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<N;j++)
			{
				board[i][j]=0;
			}
		}
		
		if(SolveNQUtil(board, 0, rowLookUp,slashCode,backSlashCode,slashCodeLookUp,backSlashCodeLookUp)==false)
		{
			System.out.println("Solution to this problem does not exit");
		}
		else
		{
			printSolution(board);
		}


	}
	public static void main(String[] args) {
		QueenBNB q=new QueenBNB();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter value of N");
		int n=sc.nextInt();
		q.setN(n);
		q.solve(n);
		sc.close();
	}
}

