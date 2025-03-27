/*
You are given an array of people, which are the attributes of some people in 
a queue (not necessarily in order). 
Each people[i] = [hi, ki] represents the ith person of height hi with exactly ki 
other people in front who have a height greater than or equal to hi.

Reconstruct and return the queue that is represented by the input array people. 
The returned queue should be formatted as an array queue, where queue[j] = [hj, kj]
is the attributes of the jth person in the queue (queue[0] is the person at the front of the queue).

Input Format : 
--------------
Line-1: An integer n
Line-2: n number of pairs

Output Format : 
--------------
list of n pairs

Sample Input-1:
---------------
6
7 0
4 4
7 1
5 0
6 1
5 2

Sample Output-1: 
---------------
[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]

Explanation:
Person 0 has height 5 with no other people taller or the same height in front.
Person 1 has height 7 with no other people taller or the same height in front.
Person 2 has height 5 with two persons taller or the same height in front, which is person 0 and 1.
Person 3 has height 6 with one person taller or the same height in front, which is person 1.
Person 4 has height 4 with four people taller or the same height in front, which are people 0, 1, 2, and 3.
Person 5 has height 7 with one person taller or the same height in front, which is person 1.
Hence [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] is the reconstructed queue.

Sample Input-2:
--------------
6
6 0 
5 0 
4 0
3 2
2 2
1 4

Sample Output-2: 
----------------
[[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]
 

Constraints:
-------------
1 <= people.length <= 2000
0 <= hi <= 106
0 <= ki < people.length
It is guaranteed that the queue can be reconstructed.
 */

import java.util.*;

public class RearrangeQueue{
    // T.C:- O(nlogn + n) , S.C:- O(n)
    private static int[][] reconstructQueue(int[][] people){
        // Initial apprpach is to sort according to the heights in decreasing order , if heights are same then 
        // choose the one with minimum k (so that it comes at the front)

        // First sort in descending order of heights and if heights are same sort according to the smaller value of k

        /* You can use this way also 
            Arrays.sort(people,new Comparator<int[]>(){
            @Override
            public int compare(int[] a,int[] b){

                // first check if heights are same
                if(a[0] == b[0]){
                    // now place the person who has lesser k at the front
                    return a[1] - b[1];
                }

                return b[0] - a[0]; // sort in descending order of heights otherwise
            }
        });
            Or simply use lambda function as below
         */
        Arrays.sort(people,(a,b)->{
           
           return (a[0] == b[0]) ? a[1] - b[1] : b[0] - a[0] ;
        });
        
        
        // now traverse through each element in the people array and place it at it's correct kth position
        List<int[]> temp = new ArrayList<>();
        
        for(int[] p : people){
            int idx = p[1];
            temp.add(idx,p);// just place this person at idx so that he have k ppl whose height is greater than or equal to his height and remaining elements in right will be shifted 1 index right 
        }
        
        return temp.stream().toArray(int[][] :: new); // convert to 2d array
    }
    public static void main(String[] args){
        try(Scanner sc = new Scanner(System.in)){

            int n = sc.nextInt();
            int[][] people = new int[n][2];
            
            for(int i = 0 ; i < n ; i++){
                people[i][0] = sc.nextInt(); // h
                people[i][1] = sc.nextInt(); // k
            } 
            
            System.out.println(Arrays.deepToString(reconstructQueue(people))); // also prints nested 2d arrays 
            
            sc.close();
        }
        
    }
}