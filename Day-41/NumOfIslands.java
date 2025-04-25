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

class DisjointSet {
	private int n;
	private int[] parent;
	private int[] rank;

	public DisjointSet(int n) {
		this.n = n;
		this.parent = new int[n];
		this.rank = new int[n];
		init();
	}

	private void init() {
		for (int i = 0; i < n; i++) {
			this.parent[i] = i;
		}
	}

	public int findUParent(int node) {
		if (parent[node] == node) {
			return node;
		}

		return parent[node] = findUParent(parent[node]);
	}

	public void unionByRank(int u, int v) {
		int pu = findUParent(u);
		int pv = findUParent(v);

		if (pu == pv)
			return; // do not add a edge between them since they are in same component

		if (rank[pu] == rank[pv]) {
			parent[pv] = pu;
			rank[pu]++;
		} else if (rank[pv] < rank[pu]) {
			parent[pv] = pu;
		} else {
			parent[pu] = pv;
		}

	}

	public int getNoOfComponents(int[][] grid, int rows, int cols) {
		int cnt = 0;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (grid[i][j] == 1) {
					int nodeId = i * cols + j;
					if (findUParent(nodeId) == nodeId) {
						cnt++;
					}
				}
			}
		}

		return cnt;
	}

}

public class NumOfIslands {
	private final int[] dx = { 0, -1, 0, 1 };
	private final int[] dy = { 1, 0, -1, 0 };

	private int numIslands(int[][] grid, int m, int n) {
		
		DisjointSet dsu = new DisjointSet(m * n);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					int nodeId = i * n + j;

					for (int k = 0; k < 4; k++) {
						int ni = i + dx[k];
						int nj = j + dy[k];

						if (ni >= 0 && ni < m && nj >= 0 && nj < n && grid[ni][nj] == 1) {

							int nbrId = ni * n + nj;

							dsu.unionByRank(nodeId, nbrId);
						}
					}
				}
			}
		}

		return dsu.getNoOfComponents(grid, m, n);
	}

	public static void main(String args[]) {
		Scanner s = new Scanner(System.in);
		int row = s.nextInt();
		int col = s.nextInt();
		int arr[][] = new int[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				arr[i][j] = s.nextInt();
			}
		}
		System.out.println(new NumOfIslands().numIslands(arr, row, col));
	}
}