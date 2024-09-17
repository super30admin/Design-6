import java.util.*;
public class designAutoComplete {

	class TrieNode{  
		// Create a TrieNode having Map for each character and its TrieNode as children so that we can search the trienode for each character entered
		// counts Map to store the sentence and its corresponding count searched
		Map<Character, TrieNode> children;
		Map<String, Integer> counts;
		
		public TrieNode() {
			children = new HashMap<>();
			counts = new HashMap<>();
		}
	}
	
	TrieNode root;
	String prefix;
	public designAutoComplete(String[] sentences, int[] times) {
		
		root = new TrieNode();
		prefix = "";
		// add all the sentenced and its times onto trieNode
		for(int i=0;i<sentences.length;i++) {
			add(sentences[i], times[i]);
		}
	}
	
	public void add(String sentence, int count) {
		
		TrieNode curr = root;
		for(char c: sentence.toCharArray()) {
			
			// retrieve the next TrieNode and check if its value is null, if null, create new trieNode and 
			// add the character and its TrieNode
			TrieNode next = curr.children.get(c);
			if(next == null) {
				 next = new TrieNode();
				curr.children.put(c, next);
			}
			// move ahead in TrieNode so that we can fetch the next trieNode
			curr = next;
			// once we have added the character and TrieNode, we need update our counts map with sentence provided and its corresponding count
			curr.counts.put(sentence, curr.counts.getOrDefault(sentence, 0)+count);
		}
	}
	
	public List<String> input(char c){
		
		// if the input is #, it means we have nothing to search, we will return empty list and update our prefix since it might have
		// previously searched keywords
		if(c == '#') {
			add(prefix, 1);
			prefix = "";
			return new ArrayList<String>();
		}
		// keep adding the character to prefix so that we can search with keywords entered until # is entered.
		// if the # is not entered, we keep updating the prefix String with our already given previous input and checking for 
		// the prefix String in our counts Map and TrieNode.
		
		prefix = prefix + c;
		TrieNode curr = root;
		
		// Iterate over the Trienode and check if the characters given in the input is already present in trieNode.
		for(char ch: prefix.toCharArray()) {
			TrieNode next = curr.children.get(ch);
			if(next == null) {
				return new ArrayList<String>();
			}
			curr = next;
		}
		// once we have the values stores in Map, we use Heap to retrieve the sentences depending on the count of sentences, So
		// pQ will store all sentences in descending order of the counts of each String sentence.
		PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a,b) -> 
		(a.getValue() == b.getValue() ? a.getKey().compareTo(b.getKey()): b.getValue() - a.getValue()));
		
		//Add all the values from the counts Map so that we can have the values stored in PQ in descnding order of counts so that 
		// we can retrieve the sentences which have highest repeated counts
		pq.addAll(curr.counts.entrySet());
		// create result list
		List<String> res = new ArrayList<>();
		// since the question asks us to return only top 3, we iterate over the pq only 3 times
		int k = 3;
		// iterate over the pQ.
		while(!pq.isEmpty() && k > 0) {
			// add only the sentences from the pq into result list
			res.add((String) pq.poll().getKey());
			k--;
		}
		// return the result list.
		return res;
	}
}
