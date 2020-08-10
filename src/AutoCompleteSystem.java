
// Time Complexity : n = number of sentences, l = avg length of the sentence , O(n*l)
// Space Complexity :O(n)
// Did this code successfully run on Leetcode : Yes

//https://leetcode.com/explore/interview/card/amazon/81/design/3000/
/**
 * Not so efficient solution using HashMap. Better solution is to use Trie instead of map
 * will submit solution using Trie as well
 * 
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;


public class AutoCompleteSystem {
	
	class Pair{
		String sentence;
		Integer count;
		
		public Pair(String s, int cnt) {
			this.sentence = s;
			this.count = cnt;
		}
	}
	Map<String, Integer> sentCountMap;
	String input;
	
	public AutoCompleteSystem(String[] sentences, int[] times) {
		this.sentCountMap = new HashMap<String, Integer>();
		input = "";
		
		for(int i=0; i<sentences.length; i++) {
			sentCountMap.put(sentences[i], sentCountMap.getOrDefault(sentences[i], 0) + times[i]);
		}
	}
	
	public List<String> input(char c) {
		if(c == '#') { // if input #, string seen till now is new sentence
			sentCountMap.put(input, sentCountMap.getOrDefault(input, 0) + 1);
			input = "";
			return new ArrayList<String>();
		}else {
			Queue<Pair> pq = new PriorityQueue<>((a,b) -> {
				if(a.count == b.count) { //if count same, we need lexicographically bad string removed
					return b.sentence.compareTo(a.sentence);
				}else {
					return a.count - b.count; // we have to form min heap based on count so at end we get elements with max count
				}
			});
			
			input +=c;
			
			//O(n*l)
			for(String key: sentCountMap.keySet()) {
				if(key.startsWith(input)) {
					Pair p = new Pair(key, sentCountMap.get(key));
					pq.add(p);
					
					if(pq.size() > 3) {
						pq.poll();
					}
				}
			}
			
			//get final result from min heap in reverse order - max count to min count
			String[] res = new String[pq.size()];
			
			//O(3)
			for(int i= pq.size() - 1; i >=0; i--) {
				res[i] = pq.poll().sentence;
			}
			
			return Arrays.asList(res);
		}
	}
}
