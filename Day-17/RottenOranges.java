/*
 * You are given an m x n grid where each cell can have one of three values:
-> 0 representing an empty cell,
-> 1 representing a fresh orange, or
-> 2 representing a rotten orange.

Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. 
If this is impossible, return -1.


Input Format:
-------------
Line-1: Two integers, m and n, represents grid[] sizes
Line-2 to m: n Space separated integers, represents grid[i][j]

Output Format:
--------------
Line-1: An integer

Sample Input-1:
---------------
3 3
2 1 1
1 1 0
0 1 1

Sample Output-1:
----------------
4

Sample Input-2:
---------------
3 3
2 1 1
0 1 1
1 0 1

Sample Output-2:
----------------
-1

Explanation: 
-------------
The orange in the bottom left corner (row 2, column 0) is never rotten, 
because rotting only happens 4-directionally.

Sample Input-3:
---------------
1 1
0 2

Sample Output-3:
----------------
0

Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
 
Constraints:
------------
m == grid.length
n == grid[i].length
1 <= m, n <= 10
grid[i][j] is 0, 1, or 2.
 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class RottenOranges {
    private static final int[] dx = {0,-1,0,1};
    private static final int[] dy = {1,0,-1,0};

    private static int bfs(int[][] grid,int m,int n){
        Queue<int[]> qu = new LinkedList<>();

        int freshCnt = 0;
        // get the pos of first rotten orange 
        boolean[][] visited = new boolean[m][n];
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(grid[i][j] == 2) {
                    qu.offer(new int[]{i,j}); // this is a multi source BFS 
                    visited[i][j] = true;
                }else if(grid[i][j] == 1){
                    freshCnt++;
                }
                    
            }
        }

        if(freshCnt == 0) return 0;

        int time = 0;
        while(!qu.isEmpty()){

            int size = qu.size();
            boolean isRotted = false;
            for(int it = 0 ; it < size ; it++){

                int[] pair = qu.poll();

                int x = pair[0];
                int y = pair[1];

                for(int i = 0 ; i < 4 ; i++){
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if(nx >= 0 && ny >= 0 && nx < m && ny < n && !visited[nx][ny] && grid[nx][ny] == 1){
                        visited[nx][ny] = true;
                        qu.offer(new int[]{nx,ny});
                        freshCnt--;
                        isRotted = true;
                    }
                }


            }

            if(isRotted) time++;

          

        }


        return (freshCnt > 0) ? -1 : time;


    }
    private static int orangesRotting(int[][] grid) {
        
        int m = grid.length;
        int n = grid[0].length;

        return bfs(grid,m,n);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] grid = new int[m][n];

        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                grid[i][j] = sc.nextInt();
            }
        }

        System.out.println(orangesRotting(grid));
        
        sc.close();
    }
}