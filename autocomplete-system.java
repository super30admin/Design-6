// TC: O(n * log(n)) for input method
// SC: O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class AutocompleteSystem {

    HashMap<String, Integer> map;
    String input = "";

    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        // Insert each sentence with its corresponding frequency
        for (int i = 0; i < sentences.length; i++) {
            map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
        }
    }

    public List<String> input(char c) {
        if (c == '#') {
            // When the input ends, update the frequency count
            map.put(input, map.getOrDefault(input, 0) + 1);
            input = "";
            return new ArrayList<>();
        }
        
        input += c;
        
        // Use a min-heap to keep track of the top 3 sentences matching the prefix
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
            if (a.count == b.count) {
                return b.sentence.compareTo(a.sentence);
            } else {
                return a.count - b.count;
            }
        });
        
        // Iterate through each sentence in the map to check if it matches the current prefix
        for (String s : map.keySet()) {
            if (s.startsWith(input)) {
                pq.add(new Pair(s, map.get(s)));
                if (pq.size() > 3) {
                    pq.poll();
                }
            }
        }
        
        // Build the result list by polling the priority queue (the order is reversed)
        List<String> result = new ArrayList<>();
        while (!pq.isEmpty()) {
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
