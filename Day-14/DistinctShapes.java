/*
Viraj Aanand is a wedding planner, He ordered his assistant to decorate a wall.
The decorator plans to decorate the wall with two different colored balloons.
The wall size is M*N, The decorator can decorate the wall using M*N balloons
the balloons are blue or white in color.

Blue colored ballons represented with digit-1 and 
White colored ballons represented with digit-0.

The blue colored balloons forms different shapes, that are connected 4 directonally.
The directons are upwards, downwards, left, and right. Viraj Aanand got an idea to 
count the unique shapes formed by blue colored ballons.

You will be given the decorated wall as a matrix wall[][].
Your task is to help, Viraj Aanand to count the unique shapes.

Input Format:
-------------
Line-1: Two space separated integers M and N, size of the wall.
Next M lines: N space separated integers, either 0 or 1.

Output Format:
--------------
Print an integer, Number of distinct shapes formed by blue balloons.


Sample Input-1:
---------------
4 5
1 1 0 0 0
1 1 0 0 0
0 0 0 1 1
0 0 0 1 1

Sample Output-1:
----------------
1


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
public class DistinctShapes {

    private static final int[] dx = {0,-1,0,1}; 
    private static final int[] dy = {1,0,-1,0};

    private static void dfs(int row,int col,int baserow,int basecol,int[][] grid,boolean[][] visited,int m,int n,List<String> shape){

        shape.add((row - baserow) + "," + (col - basecol));
        visited[row][col] = true;

        for(int i = 0 ; i < 4 ; i++){
            int nx = row + dx[i];
            int ny = col + dy[i];

            if(nx >= 0 && nx < m && ny >=0 && ny < n && !visited[nx][ny] && grid[nx][ny] == 1){
                dfs(nx,ny,baserow,basecol,grid,visited,m,n,shape);
            }
        }
    }
    private static int countDistinctIslands(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        HashSet<String> uniqueIslands = new HashSet<>();
        boolean[][] visited = new boolean[m][n];
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(!visited[i][j] && grid[i][j] == 1){
                    List<String> shape = new ArrayList<>();
                    dfs(i,j,i,j,grid,visited,m,n,shape);
                    // Collections.sort(shape); 
                    uniqueIslands.add(String.join(";", shape));
                }
            }
        }

        return uniqueIslands.size();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int m = sc.nextInt();
        int n = sc.nextInt();

        int[][] grid = new int[m][n];
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                grid[i][j] = sc.nextInt();
            }
        }

        System.out.print(countDistinctIslands(grid));
        
        sc.close();
    }
}