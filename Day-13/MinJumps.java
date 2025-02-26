
/*
Given an array of integers arr, you are initially positioned at the first index of the array.

In one step you can jump from index i to index:
i + 1 where: i + 1 < arr.length.
i - 1 where: i - 1 >= 0.
j where: arr[i] == arr[j] and i != j.

Return the minimum number of steps to reach the last index of the array.

Notice that you can not jump outside of the array at any time.

Input Format:
-------------
Line-1: An integer n
Line-2: n space separated integers

Output Format:
--------------
Line-1: An integer

Sample Input-1:
---------------
10
100 -23 -23 404 100 23 23 23 3 404

Sample Output-1:
----------------
3

Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.

Sample Input-2:
---------------
1
7

Sample Output-2: 
----------------
0

Explanation: Start index is the last index. You do not need to jump.

Sample Input-3:
---------------
8
7 6 9 6 9 6 9 7

Sample Output-3:
----------------
1

Explanation: You can jump directly from index 0 to index 7 which is last index of the array.
 
 Constraints:
 ------------
1 <= arr.length <= 5 * 10^4
-10^8 <= arr[i] <= 10^8
 */
import java.util.*;

public class MinJumps {
    private static int solve(int idx, int jumps, int[] arr, int n, boolean[] visited) {
        if (idx == n - 1) {
            return jumps;
        }

        visited[idx] = true;
        int mini = Integer.MAX_VALUE;

        // either go from i + 1 till last
        int nextIdx = idx + 1;
        if (nextIdx < n && !visited[nextIdx]) {
            mini = Math.min(mini, solve(nextIdx, jumps + 1, arr, n, visited));
        }

        // either go back
        int prevIdx = idx - 1;
        if (prevIdx >= 0 && !visited[prevIdx]) {
            mini = Math.min(mini, solve(prevIdx, jumps + 1, arr, n, visited));
        }

        for (int i = 0; i < n; i++) {
            if (i != idx && arr[idx] == arr[i] && !visited[i]) {
                mini = Math.min(mini, solve(i, jumps + 1, arr, n, visited));
            }
        }
        // while backtracking make this as false
        visited[idx] = false;
        return mini;
    }

    private static int solveMemo(int idx, int jumps, int[] arr, int n, boolean[] visited, int[][] dp) {
        if (idx == n - 1) {
            return jumps;
        }

        if (dp[idx][jumps] != -1)
            return dp[idx][jumps];
        visited[idx] = true;

        int mini = Integer.MAX_VALUE;

        // either go from i + 1 till last
        int nextIdx = idx + 1;
        if (nextIdx < n && !visited[nextIdx]) {
            mini = Math.min(mini, solveMemo(nextIdx, jumps + 1, arr, n, visited, dp));
        }

        // either go back
        int prevIdx = idx - 1;
        if (prevIdx >= 0 && !visited[prevIdx]) {
            mini = Math.min(mini, solveMemo(prevIdx, jumps + 1, arr, n, visited, dp));
        }

        for (int i = 0; i < n; i++) {
            if (i != idx && arr[idx] == arr[i] && !visited[i]) {
                mini = Math.min(mini, solveMemo(i, jumps + 1, arr, n, visited, dp));
            }
        }
        // while backtracking make this as false
        visited[idx] = false;
        return dp[idx][jumps] = mini;
    }

    private static int solveTabulation(int[] arr, int n) {
        int[][] dp = new int[n][n]; // dp[i][j] -> min jumps to reach index i with j jumps
        for (int[] row : dp)
            Arrays.fill(row, Integer.MAX_VALUE); // Fill with large values

        dp[0][0] = 0; // Starting point

        for (int jumps = 0; jumps < n - 1; jumps++) { // Iterate over jumps
            for (int idx = 0; idx < n; idx++) { // Iterate over indices
                if (dp[idx][jumps] == Integer.MAX_VALUE)
                    continue; // Skip uninitialized values

                int nextIdx = idx + 1;
                if (nextIdx < n) {
                    dp[nextIdx][jumps + 1] = Math.min(dp[nextIdx][jumps + 1], dp[idx][jumps] + 1);
                }

                int prevIdx = idx - 1;
                if (prevIdx >= 0) {
                    dp[prevIdx][jumps + 1] = Math.min(dp[prevIdx][jumps + 1], dp[idx][jumps] + 1);
                }

                for (int i = 0; i < n; i++) {
                    if (arr[idx] == arr[i] && i != idx) {
                        dp[i][jumps + 1] = Math.min(dp[i][jumps + 1], dp[idx][jumps] + 1);
                    }
                }
            }
        }

        // Find the minimum jumps needed to reach the last index
        int minJumps = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            minJumps = Math.min(minJumps, dp[n - 1][j]);
        }
        return minJumps;
    }

    // private int solveBFS(int[] arr,int n){
    // boolean[] visited = new visited[n];

    // Queue<Integer> qu = new LinkedList<>();
    // qu.offer(0);
    // visited[0] = true;

    // while(!qu.isEmpty()){
    // int top = qu.poll();

    // if
    // }
    // }
    private static int minJumps(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        // for(int[] row : dp){
        // Arrays.fill(row,-1);
        // }

        // return solveMemo(0,0,arr,arr.length,new boolean[n],dp);
        return solveTabulation(arr, n);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        System.out.print(minJumps(arr));
        sc.close();
    }
}