// Time Complexity - O(nlogk) n = no of sentences , k = heap size which is 3
// Space Complexity -O(n) n = no of sentences in hashmap

// Approach
// Add sentences and their freq to hashmap. Whenever a char is typed, append it into search string
// since we are going to search the complete string one by one, the priority queue will store the pairs of
// sentences with their counts. If count of occurrences are equal, compare the lexicographic ordering of string.
// If count is greater, put that into min head and poll the top of queue. If char is # it means end of input and return the 
// result until now after adding it to the hashmap.

import java.util.HashMap;
import java.util.PriorityQueue;

class AutocompleteSystem {
    private class Pair {
        String sentence;
        int count;
        public Pair(String sentence, int count) {
            this.sentence = sentence;
            this.count = count;
        }
    }

    HashMap<String,Integer> map;
    String input;

    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        input = "";
        for(int i=0;i<sentences.length;i++) {
            map.put(sentences[i],map.getOrDefault(sentences[i], 0)+times[i]);
        }
    }

    public List<String> input(char c) {
        if(c == '#') {
            map.put(input, map.getOrDefault(input, 0)+1);
            input = "";
            return new ArrayList<>();
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> {
            if(a.count == b.count) {
                return b.sentence.compareTo(a.sentence);
            } else {
                return a.count - b.count;
            }
        });
        input += c;
        for(String key:map.keySet()) {
            if(key.startsWith(input)) {
                pq.add(new Pair(key,map.get(key)));
                if(pq.size() > 3) pq.poll();
            }
        }
        List<String> res = new ArrayList<>();
        while(!pq.isEmpty()) {
            res.add(0,pq.poll().sentence);
        }
        return res;
    }
}

