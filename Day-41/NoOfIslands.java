/*

Ibrahim is an interior designer wants to color wall of size M*N. 
He plans to color the wall and put lights of two different colors

The designer can lit the wall using M*N lights.The lights are Blue or pink
in color. Blue colored lights represented with digit-1 and pink colored lights 
represented with digit-0.

The Blue colored lights forms different shapes, that are connected 4 directonally.
The directons are upwards, downwards, left, and right. Ibrahim got an idea to 
count the number of shapes formed by blue colored lights.

You will be given the decorated wall as a matrix wall[][].
Your task is to help Ibrahim to count the number of shapes by the lights.

Input Format:
-------------
Line-1: Two space separated integers M and N, size of the wall.
Next M lines: N space separated integers, either 0 or 1.

Output Format:
--------------
Print an integer, Number of distinct shapes formed by Blue Lights.


Sample Input-1:
---------------
4 5
1 1 0 1 1
1 1 0 0 1
0 0 0 0 0
1 1 0 0 0

Sample Output-1:
----------------
3


Sample Input-2:
---------------
5 5
1 1 0 1 1
1 0 0 0 1
0 0 0 0 0
1 0 0 0 1
1 1 0 1 1

Sample Output-2:
----------------
4
 */

import java.util.*;
public class NumOfIslands 
{
	private int[] sz;
	private int[] parent;
	private int rows, cols;
	
	NumOfIslands(int n)
	{
	    parent = new int[n];
	    sz = new int[n];
	    makeSet();
	}
	
	public void makeSet()
	{
	    for(int i = 0; i < parent.length; i++)
	    {
	        parent[i] = i;
	        sz[i] = 1;
	    }
	}
	
	public int find(int p) 
	{
		while (parent[p] != p) 
			p = parent[p];
		return p;
	}

	public void union(int p, int q) 
	{
		int rootP = find(p);
		int rootQ = find(q);

		if (rootP == rootQ) return;
		
		if (sz[rootP] < sz[rootQ])	
		{
			sz[rootQ] += sz[rootP]; 
			parent[rootP] = rootQ;
		}
		else
		{
			sz[rootP] += sz[rootQ]; 
			parent[rootQ] = rootP;
		}
	}

	public int numIslands(int[][] grid) 
	{
		//Write your code here and return an integer
		int n = grid.length;
		int m = grid[0].length;
		int[] dx = {-1, 0, 1, 0};
		int[] dy = {0, 1, 0, -1};
		
		for(int i = 0; i < n; i++)
		{
		    for(int j = 0; j < m; j++)
		    {
		        if(grid[i][j] == 1)
		        {
		            for(int k = 0; k < 4; k++)
		            {
		                int di = i + dx[k];
		                int dj = j + dy[k];
		                
		                if(di >= 0 && di < n && dj >= 0 && dj < m && grid[di][dj] == 1)
		                {
		                    union(i * m + j, di * m + dj);
		                }
		            }
		        }
		    }
		}
		
		Set<Integer> noOfIslands = new HashSet<>();
		
		for(int i = 0; i < n; i++)
		{
		    for(int j = 0; j < m; j++)
		    {
		        if(grid[i][j] == 1)
		            noOfIslands.add(find(i * m + j));
		    }
		}
		
		return noOfIslands.size();
	}

	public static void main(String args[])
	{
		Scanner s=new Scanner(System.in);
		int row=s.nextInt();
		int col=s.nextInt();	
		int arr[][]=new int[row][col];
		for (int i=0;i<row ;i++ )
		{
			for (int j=0;j<col ;j++ )
			{
				arr[i][j]=s.nextInt();
			}
		}
		System.out.println(new NumOfIslands(row * col).numIslands(arr));
	}
}