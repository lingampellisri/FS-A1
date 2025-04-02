/*
Imagine you're the master chef in a renowned kitchen, tasked with preparing a 
spectacular dinner consisting of numDishes unique dishes, labeled from 
0 to numDishes - 1. However, the recipes have dependencies—certain dishes can 
only be prepared after completing others. You’re given a list called dependecies, 
where each element dependencies[i] = [Xi, Yi] means that you must finish 
preparing dish Yi before starting dish Xi.

For instance, the pair [2, 1] implies that to create dish 2, 
dish 1 must be prepared first.

Return the ordering of dishes that a chef should take to finish all dishes.
	- the result set should follow the given order of conditions.
	- If it is impossible to complete all dishes, return an empty set.


Input Format:
-------------
Line-1: An integer numDishes, number of Dishes.
Line-2: An integer m, number of dependencies.
Next m lines: Two space separated integers, Xi and Yi.

Output Format:
--------------
Return a list of integers, the ordering of dishes that a chef should finish.

Sample Input-1:
---------------
4
3
1 2
3 0
0 1

Sample Output-1:
----------------
[2, 1, 0, 3]

Explanation: There are 4 dishes. Since dish 1 requires dish 2, dish 3 requires 
dish 0 and dish 0 requires dish 1, you can prepare dishes in the order 2 1 0 3.


Sample Input-2:
---------------
2
2
1 0
0 1

Sample Output-1:
----------------
[]

Explanation: There are 2 dishes, but dish 1 depends on dish 0 and dish 0 depends 
on dish 1. This circular dependency makes it impossible to prepare all dishes.

Constraints:
------------
- 1 <= numDishes <= 2000  
- 0 <= m <= 5000  
- dependencies[i].length == 2  
- 0 <= Xi, Yi < numDishes  
- All the dependency pairs are unique.
 */

import java.util.*;
public class MasterChef {
    private static List<List<Integer>> adjLst;
    private static int[] indegree;
    private static void dfs(int node,boolean[] visited,Stack<Integer> st){
        visited[node] = true;

        // go to it's nbrs
        for(int nbr : adjLst.get(node)){
            if(!visited[nbr]){
                dfs(nbr,visited,st);
            }
        }

        // once dfs is completed add the node to the stack
        st.add(node);
    }
    private static List<Integer> getLinearOrderingUsingTopoSort(int n){
        // solve using indegree approach
        Queue<Integer> qu = new LinkedList<>();
        // since we calculate the indegree . check if there are any nodes with indegree 0

        for(int i = 0 ; i < n ; i++){
            if(indegree[i] == 0){
                qu.offer(i); // this could be possible starting vertex in the linear ordering , since there are no one who depends on this node
            }
        }

        List<Integer> ans = new ArrayList<>();
        while(!qu.isEmpty()){
            // pop the first element 
            int node = qu.poll();
            // add this to answer
            ans.add(node);

            // go to it's nbr's 
            for(int nbr : adjLst.get(node)){
                indegree[nbr]--;
                if(indegree[nbr] == 0 ){
                    qu.offer(nbr);
                }
            }
            
        }


        return ans.size() == n ? ans : new ArrayList<>(); // if few nodes are missing it means there is cycle and linear ordering is not possible

    }

    private static void init(int n){
        // build adjacency list while taking edges input
        adjLst = new ArrayList<>();

        // initialize the adjlst
        for(int i = 0 ; i < n ; i++){
            adjLst.add(new ArrayList<>());
        }

        indegree = new int[n];
    }
    // this is a standard question of topological sort in DAG
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        

        
        int n = sc.nextInt();
        int edges = sc.nextInt();
        
        init(n); 

        for(int i = 0 ; i < edges ; i++){
            int v = sc.nextInt();
            int u = sc.nextInt();


            // there should be directed edge from u to v (u -> v)
            adjLst.get(u).add(v);
            indegree[v]++;
           
        }

        System.out.println(getLinearOrderingUsingTopoSort(n));

        sc.close();
    }
}
