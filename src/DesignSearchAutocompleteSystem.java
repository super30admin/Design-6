// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : No
// Any problem you faced while coding this : No

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class DesignSearchAutocompleteSystem {
	HashMap<String, Integer> map;
	StringBuilder input;
	
	public DesignSearchAutocompleteSystem(String[] sentences, int[] times){
		input = new StringBuilder();
		map = new HashMap<>();
		for(int i = 0; i < sentences.length; i++){
			map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
		}
	}
	
	public List<String> input(char c){
		if(c == '#'){
			String in = input.toString();
			map.put(in, map.getOrDefault(in, 0) + 1);
			input = new StringBuilder();
			return new ArrayList<>();
		}
		input.append(c);
		PriorityQueue<String> pq = new PriorityQueue<>((a, b) ->{
			if(map.get(a) == map.get(b)){
				return b.compareTo(a);
			}
			return map.get(a) - map.get(b);
		});
		//add all string starting with prefix in pq
		for(String s : map.keySet()){
			String inp = input.toString();
			if(s.startsWith(inp)){
				pq.add(s);
				if(pq.size() > 3){
					pq.poll();
				}
			}
		}
		List<String> result = new ArrayList<>();
		while(!pq.isEmpty()){
			result.add(pq.poll());
		}
		return result;
	}
	
}
