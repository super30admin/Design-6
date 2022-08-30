//Time Complexity: O(n) + O(m); where n is the total no. of sentences & m is the no. of sentences starting with input.
//Space Complexity: O(n)
//Code run successfully on LeetCode.

public class Problem2 {

	class AutocompleteSystem {

	    HashMap<String, Integer> map;

	    String input = "";

	    public AutocompleteSystem(String[] sentences, int[] times) {

	        map = new HashMap<>();

	        // put every thing in database

	        for(int i = 0; i < sentences.length; i++){

	            map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);

	        }

	    }

	    public List<String> input(char c) {

	        if(c =='#'){

	            map.put(input, map.getOrDefault(input, 0) + 1);

	            input = "";

	            return new ArrayList<>();

	        }

	        input += c;

	        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {

	            if(a.count == b.count){

	                return b.sentence.compareTo(a.sentence);

	            } else {

	                return a.count - b.count;

	            }

	        });

	        for (String s : map.keySet()){

	            if (s.startsWith(input)){

	                pq.add(new Pair(s, map.get(s)));

	                if(pq.size() > 3){

	                    pq.poll();

	                }

	            }

	        }

	        List<String> result = new ArrayList<>();

	        while(!pq.isEmpty()) {

	            Pair p = pq.poll();

	            result.add(0, p.sentence);

	        }

	        return result;

	    }

	     private class Pair {

	        String sentence;

	        int count;

	        public Pair(String s, int c) {

	            sentence = s;

	            count = c;

	        }

	    }

	}
}
