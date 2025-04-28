
/* 
Ibrahim is an interior designer wants to color wall of size M*N. 
He plans to color the wall and put lights of two different colors

The designer can lit the wall using M*N lights.The lights are Blue or pink
in color. Blue colored lights represented with digit-1 and pink colored lights 
represented with digit-0.

The Blue colored lights forms different shapes, that are connected 4 directonally.
The directons are upwards, downwards, left, and right. Ibrahim got an idea to 
count the unique shapes formed by blue colored lights.

You will be given the decorated wall as a matrix wall[][].
Your task is to help Ibrahim to count the unique shapes by the lights.

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
import java.util.stream.Collectors;

class DisjointSet {
    private final int n;
    private final int[] parent;
    private final int[] rank;

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

}

public class NoDistinctIslands {
    private static final int[] dx = { 0, -1, 0, 1 };
    private static final int[] dy = { 1, 0, -1, 0 };

    private static void buildDSU(DisjointSet dsu,int[][] grid,int m,int n){
        
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(grid[i][j] == 1){
                    int nodeId = i * n + j;
                    for(int k = 0 ; k < 4 ; k++){
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        
                        if(nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 1){
                            int nbrId = nx * n + ny;
                            dsu.unionByRank(nodeId, nbrId);
                        }

                    }
                }
            }
        }
    }
    private static int getNoOfDistinctIslands(int[][] grid, int m, int n) {
        DisjointSet dsu = new DisjointSet(m*n);

        buildDSU(dsu,grid,m,n);

		HashSet<String> seen = new HashSet<>();

        String[] pattern = new String[m*n];
        Arrays.fill(pattern,"");

        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){

                if(grid[i][j] == 1){
                    int nodeId = i * n + j;
                    int parent = dsu.findUParent(nodeId); // get the ultimate parent
                    pattern[parent] += String.valueOf(nodeId - parent); // add in parent pattern
                    
                }
            }
        }

        // for(String str : pattern){
        //     if(str != ""){
        //         seen.add(str);
        //     }
        // }

        seen.addAll(Arrays.stream(pattern)
            .filter(p -> !p.isEmpty())
            .collect(Collectors.toSet())
        );


		return seen.size();
	}

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int m = sc.nextInt();
            int n = sc.nextInt();

            int grid[][] = new int[m][n];

            for (int i = 0; i < m; i++) {

                for (int j = 0; j < n; j++) {

                    grid[i][j] = sc.nextInt();
                }
            }

            System.out.println(getNoOfDistinctIslands(grid, m, n));

        }
    }
}