/*
In a school, the students are given an array of strings words[]. 
Students have to find the longest string in words[] 
such that every prefix of it is also in words.

For example, let words = ["a", "app", "ap","appl", "apply"]. 
The string "apply" has prefixes "ap","app","appl" and "a", all of which are in words.

Your task is the find and return the longest string in words as described above.

If there is more than one string with the same length, return the lexicographically
smallest one, and if no string exists, return "".

Input Format
-------------
Line1: Space spearated strings

Output Format
--------------
Line-1: A string 

Sample Input-1:
---------------
k kmi km kmit

Sample Output-1:
----------------
kmit

Explanation:
------------
"kmit" has prefixes "kmi", "km", "k" and all of them appear in words.


Sample Input-2:
---------------
t tup tupl tu tuple tupla

Sample Output-2:
----------------
tupla

Explanation:
------------
Both "tuple" and "tupla" have all their prefixes in words.
However, "tupla" is lexicographically smaller, so we return that.


Sample Input-3:
---------------
abc bc ab abcd

Sample Output-3:
----------------
""
 */
import java.util.*;
class TrieNode{
    TrieNode[] children;
    boolean eow;

    public TrieNode(){
        this.children = new TrieNode[26];
        this.eow = false;
    }
}


class Trie{
    private TrieNode root;

    public Trie(){
        this.root = new TrieNode();
    }

    public TrieNode getRoot(){
        return root;
    }
    public void insert(String word){
        TrieNode crawl = root;
        for(char ch : word.toCharArray()){
            int idx = ch - 'a';

            if(crawl.children[idx] == null){
                crawl.children[idx] = new TrieNode();
            }

            crawl = crawl.children[idx];
        }

        // mark end of word
        crawl.eow = true;
    }


}

public class LongestWord {

    private static String getLongestWord(String[] words){
        // create a trie and insert all the words into it 
        Trie trie = new Trie();
        for(String word : words){
            trie.insert(word);
        }


        // traverse through the trie

        for(int i = 0 ; i < 26 ; i++){
            TrieNode crawl = trie.getRoot();

            // start crawling into the depth of the trie 
            while(crawl.children[i])
        }
    }
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){

            String[] words = sc.nextLine().split(" ");

            System.out.println(getLongestWord(words));

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
