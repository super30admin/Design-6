//Time complexity is O(n)
//Space complexity is O(N)
//This solution is submitted on leetode

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class BigN125AutoCompleteSolution {
	class AutocompleteSystem {
		HashMap<String, Integer> map;
		String search;
		
		public AutocompleteSystem(String[] sentences, int[] times) {
			this.map = new HashMap<>();
			this.search = "";
			for(int i =0;i<sentences.length;i++) {
				map.put(sentences[i],map.getOrDefault(sentences[i],0)+ times[i]);
			}
		}

		public List<String> input(char c) {
			if(c== '#') {
				map.put(search, map.getOrDefault(search,0)+1);
				search = "";
				return new ArrayList<>();
			}
			search +=c;
			PriorityQueue<Pairs> pq = new PriorityQueue<>((a,b)-> {
				if(a.count == b.count) {
					return a.sentence.compareTo(b.sentence);
				}
				return b.count -a.count;
			});
			List<String> result = new ArrayList<>();
			for(String key : map.keySet()) {
				if(key.startsWith(search)) {
					pq.add(new Pairs(map.get(key),key));
				}
			}
			while(!pq.isEmpty() && result.size() <3) {
				Pairs temp = pq.poll();
				result.add(temp.sentence);
			}
			return result;
		}
	}
	
	class Pairs{
		int count;
		String sentence;
		public Pairs(int count, String sentence) {
			this.count = count;
			this.sentence = sentence;
		}
	}
	/**
	 * Your AutocompleteSystem object will be instantiated and called as such:
	 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
	 * List<String> param_1 = obj.input(c);
	 */
}