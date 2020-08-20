// Time Complexity : O(n + mlog(m)) --> where n is number of sentences in the input array and m is all the sentences which starts with given input
// Space Complexity : O(n)
// Did this code successfully run on Leetcode (642): Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

class AutocompleteSystem {
    HashMap<String, Integer> map;
    String input;
    public AutocompleteSystem(String[] sentences, int[] times) {
        this.map = new HashMap<>();
        this.input = "";
        for (int i = 0; i < sentences.length; i++) {
            map.put(sentences[i], times[i]);
        }
    }
    
    public List<String> input(char c) {
        if (c == '#') {
            map.put(this.input, map.getOrDefault(this.input, 0) + 1);
            this.input = "";
            return new ArrayList<>(); 
        }
        
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
            if (a.count == b.count) return b.sentence.compareTo(a.sentence);
            else return a.count - b.count;
        });
        
        input += c;
        for (String key : map.keySet()) {
            if (key.startsWith(input)) {
                pq.add(new Pair(key, map.get(key)));
                if (pq.size() > 3) pq.poll();
            }
        }
        
        List<String> result = new ArrayList<>();
        while (!pq.isEmpty()) result.add(0, pq.poll().sentence);
        
        return result; 
    }
    
    class Pair {
        String sentence;
        int count;
        
        public Pair(String sentence, int count) {
            this.sentence = sentence;
            this.count = count;
        }
    }
}