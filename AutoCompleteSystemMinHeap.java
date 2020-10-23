package com.s30.edu.design6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

//We are storing sentence and times as a Node in a Heap
class Node1{

String sentence;
int count;

public Node1(String sentence, int count){
   this.sentence = sentence;
   this.count = count;
}

}


/******************* Optimization from O (N logN) time to add sentences to Max heap to O (N log3) in Min Heap = O (N) **********************/
// Instead of storing all sentences in a heap, use min heap and store just 3 sentences that "starts with" input entered to reduce time complexity

/*
 * Time Complexity: O (n * min(inputStr, key) * log3)
 * 
 * Rest is same as code using  Max Heap
 * 
 */

class AutoCompleteSystemMinHeap {
	 HashMap<String, Integer> map;
	 String inputStr;
	 
	 public AutoCompleteSystemMinHeap(String[] sentences, int[] times) {
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
	             inputStr = "";
	         }
	         
	         // If char entered is directly "#", return empty arraylist
	         return new ArrayList<>();

	     }
	     
	     // else, append the char entered by user to input string
	     inputStr += c;
	     
	     // For every input string, create a new Priority Queue
	    
	     
	     /*
	     *   In JAVA, Priority Queue is a Min Heap
	     *   We are using a custom comparator -> using Lambda expression, because we are storing the sentence and its corresponding times as a Node in a Min Heap
	     *   (a,b) are two nodes
	     *   We check if node a's count != node b's count, then custom comparator returns a node with lower count
	     *       Since this is a min heap and we want 3 sentences with lower count, "a.count - b.count" will give
	     *       negative ans, means a.count < b.count, node a will be returned first (will be on left side for understanding)
	     *   If "a.count == b.count", then custom comparator will compare the strings and return larger one first
	     *   	compare larger sentence 'b.sentence' with smaller one 'a.sentence' -> larger sentence b.sentence (on the left side)
	     *   
	     */
	     PriorityQueue<Node> queue = new PriorityQueue<Node>((a,b) -> (a.count != b.count) ? a.count - b.count : b.sentence.compareTo(a.sentence));
	     
	     // Whenever user enters the input, traverse the HashMap keyset, check which key "starts with" prefix same as input entered by user "inputStr", add that key and its times as a Node in a Min Heap
	     for(String key : map.keySet()){   // O (n * min(inputStr, key) * log3)
	         if(key.startsWith(inputStr)){
	            queue.add(new Node(key, map.get(key)));
	             
	            // When size of heap beaomes greater than 3, remove from min heap
	            if(queue.size() > 3){
	                queue.remove();
	            }
	         }
	     }
	                       
	     // Create a result list
	     List<String> output = new ArrayList<>();
	                       
	     // Get the top 3 historical hot sentences that have the same prefix as the part of sentence already typed from Max Heap
	     while(!queue.isEmpty() && output.size() < 3){
	         output.add(queue.remove().sentence);
	     }
	     
	     // Above step will give the output in ascending order, we want it to be reversed
	     Collections.reverse(output);
	       
	     return output;
	                       
	 }
	 
}
