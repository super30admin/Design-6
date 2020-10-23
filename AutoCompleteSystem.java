/*
 * #642. Design Search Autocomplete System
 * 
 * Design a search autocomplete system for a search engine. 
 * Users may input a sentence (at least one word and end with a special character '#'). 
 * For each character they type except '#', you need to return the top 3 historical hot sentences that have prefix the same
 *  as the part of sentence already typed. Here are the specific rules:

1. The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
2. The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
3. If less than 3 hot sentences exist, then just return as many as you can.
4. When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.

Your job is to implement the following functions:

The constructor function:

AutocompleteSystem(String[] sentences, int[] times): This is the constructor. 
The input is historical data. Sentences is a string array consists of previously typed sentences. 
Times is the corresponding times a sentence has been typed. Your system should record these historical data.

Now, the user wants to input a new sentence. The following function will provide the next character the user types:

List<String> input(char c): The input c is the next character typed by the user. 
The character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#').
 Also, the previously typed sentence should be recorded in your system. 
 The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.

 
Example:
Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
The system have already tracked down the following sentences and their corresponding times:
"i love you" : 5 times
"island" : 3 times
"ironman" : 2 times
"i love leetcode" : 2 times
Now, the user begins another search:

Operation: input('i')
Output: ["i love you", "island","i love leetcode"]
Explanation:
There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored.

Operation: input(' ')
Output: ["i love you","i love leetcode"]
Explanation:
There are only two sentences that have prefix "i ".

Operation: input('a')
Output: []
Explanation:
There are no sentences that have prefix "i a".

Operation: input('#')
Output: []
Explanation:
The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.

 
Note:

1. The input sentence will always start with a letter and end with '#', and only one blank space will exist between two words.
2. The number of complete sentences that to be searched won't exceed 100. The length of each sentence including those in the historical data won't exceed 100.
3. Please use double-quote instead of single-quote when you write test cases even for a character input.
4. Please remember to RESET your class variables declared in class AutocompleteSystem, as static/class variables are persisted across multiple test cases. Please see here for more details.
 
 
 */


/*
 * Time Complexity: O (N) -> for loops
 * 
 * Space Complexity: O (N) > HashMap to store 'N' historical sentences and Max Heap to store 'N' nodes for sentences that "starts with" sentence typed by user as input
 * 
 * Did this code successfully run on leetcode: Yes
* 
*  Any problem you faced while coding this: No
*  
 */

package com.s30.edu.design6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

//We are storing sentence and times as a Node in a Heap
class Node{
 
 String sentence;
 int count;
 
 public Node(String sentence, int count){
     this.sentence = sentence;
     this.count = count;
 }
 
}

class AutocompleteSystem {

 HashMap<String, Integer> map;
 String inputStr;
 
 public AutocompleteSystem(String[] sentences, int[] times) {
     // Initialize the map and input string
     map = new HashMap<>();
     inputStr = "";
     
     // store all the given sentences and corresponding times in a HashMap
     for(int i = 0; i < sentences.length; i++){
         map.put(sentences[i], times[i]);
     }
     
 }
 
 public List<String> input(char c) {
     
     if(c == '#'){
         /* If char entered is "#", check two things:
         *   1. If any inputStr entered before '#' character
         *   2. If '#' is the first char entered
         */
         
         // 1. 
         if(inputStr != ""){
             // inputStr should be stored as a historical sentence in system -> HashMap
             // If already present, increment the 'times' of a sentence
             map.put(inputStr, map.getOrDefault(inputStr, 0) + 1);
             inputStr = ""; // reset input string
         }
         
         // If char entered is directly "#", return empty arraylist
         return new ArrayList<>();

     }
     
     // else, append the char entered by user to input string
     inputStr += c;
     
     // For every input string, create a new Priority Queue
     /*
     *   In JAVA, Priority Queue is a Min Heap, but we need max heap as we need to return 3 hot sentences
     *   We are converting this min Heap to Max Heap by using a custom comparator -> using Lambda expression
     *   Also, We are storing the sentence and its corresponding times as a Node in a Max Heap, so need custom comparator for that as well
     *   (a,b) are two nodes
     *   We check if node a's count != node b's count, then custom comparator returns a node with higher count
     *       Since this is a max heap and we want 3 sentences with higher count, "b.count - a.count" will give
     *       positive ans, means b.count > a.count, node b will be returned first (will be on left side for understanding)
     *   If "a.count == b.count", then custom comparator will compare the strings and return smaller one first
     *   	compare smaller sentence 'a.sentence' with larger one 'b.sentence' -> smaller sentence a.sentence (on the left side)
     *   
     */
     PriorityQueue<Node> queue = new PriorityQueue<Node>((a,b) -> (a.count != b.count) ? b.count - a.count : a.sentence.compareTo(b.sentence));
     
     // Whenever user enters the input, traverse the HashMap keyset, check which key "starts with" prefix same as input entered by user "inputStr", add that key and its times as a Node in a Max Heap
     for(String key : map.keySet()){
         if(key.startsWith(inputStr)){
            queue.add(new Node(key, map.get(key)));
         }
     }
                       
     // Create a result list
     List<String> output = new ArrayList<>();
                       
     // Get the top 3 historical hot sentences that have the same prefix as the part of sentence already typed from Max Heap
     while(!queue.isEmpty() && output.size() < 3){
         output.add(queue.remove().sentence);
     }
       
     return output;
                       
 }
                       
}

/**
* Your AutocompleteSystem object will be instantiated and called as such:
* AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
* List<String> param_1 = obj.input(c);
*/
