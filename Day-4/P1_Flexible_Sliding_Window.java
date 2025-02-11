import java.util.Scanner;
public class P1_Flexible_Sliding_Window {

    private static int maxBooksNaive(int[] books,int k,int n){

        int maxBooksRead = 0;

        
        for(int i = 0 ; i < n ; i++){   
            int timeTaken = 0;

            for(int j = i ; j < n ; j++){
                timeTaken += books[j];
                if(timeTaken > k) break;
                maxBooksRead = Math.max(maxBooksRead,j - i + 1);
             }   
        }

        return maxBooksRead;
        
    }
    // Approach-2 Two pointer
    private static int maxBooksTwoPointer(int[] books,int k,int n){
        // Flexible size sliding window is as same as two pointer approach
        int left = 0 , right = 0;
        
        int time = 0;
        int maxBooksRead = 0;
        while(right < n){
            if(time > k){
                time -= books[left];
                left++;
            }
            
            time += books[right];
            maxBooksRead = Math.max(maxBooksRead,right - left);

            
            right++;
        }

        return maxBooksRead;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();

        int[] arr = new int[n];
        for(int i = 0 ; i < n ; i++){
            arr[i] = sc.nextInt();   
        }

        int k = sc.nextInt();
        
        System.out.println(maxBooksTwoPointer(arr, k, n));

        
        sc.close();
    }
}
