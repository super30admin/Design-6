import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

import javax.xml.transform.Result;

//idea is to use hashmap as database to store setences as key and no of occurances as values
//priority queue will store all sentences  which start with inputString .
//priority queue is max heap here which store sentences in pair form sentences and count.
//return top 3 sentences from priority queue.

//time complexity o(n)
//sc o(n)

public class AutoCompleteSystem {
	static HashMap<String,Integer> database = new HashMap<>();
    static String searchString = "";
    public  static void AutocompleteSystem (String[] sentences, int[] times) {
    	for(int i=0; i<sentences.length;i++) {
    		String curr = sentences[i];
    		if(database.containsKey(curr)) {
    			database.put(curr, database.get(curr)+times[i]);
    		}else {
    			database.put(curr, times[i]);
    		}
    	}
    }
    
    public static List<String> input(char c){
    	if(c=='#') {
    		database.put(searchString, database.getOrDefault(searchString, 0)+1);
    		searchString ="";
    		return new ArrayList<>();
    	}
    	searchString+=c;
		PriorityQueue<Pair> q = new PriorityQueue<>((a,b)->(a.count!=b.count ? b.count- a.count: a.sentence.compareTo(b.sentence)));
    	ArrayList<String> result = new ArrayList<>();
    	for(String s :database.keySet()) {
    		if(s.startsWith(searchString)) {
    			q.offer(new Pair(s,database.get(s)));
    		}
    	}
    	
    	while(!q.isEmpty() && result.size()<3) {
    		result.add(q.poll().sentence);
    	}
    	
    	return result;
    }
    
    static class Pair{
    	String sentence;
    	int count;
    	Pair(String sentence, int count){
    		this.sentence = sentence;
    		this.count = count;
    	}
    }
    
    public static void main(String[] args) {
    	String[] sentences = {"i love you", "island","ironman", "i love leetcode"};
    	int[] times = {5,3,2,2};
    	AutocompleteSystem(sentences,times);
    	List<String> result = input('i');
    	System.out.println("autocomplete senteces are "+result);
    	
    }
}
