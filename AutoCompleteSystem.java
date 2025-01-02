// Approach : Brute Force - 
// 1. By storing sentences along with their frequency in a HashMap
// 2. for searching and showing results only top 3 we need to create a min Heap 
// Time : nlogk ~ O(n) if k is minimal
// space : O(k) // Heap Size + O(n) // size of sentences ~ O(n)
class AutocompleteSystem {
    HashMap<String, Integer> map;
    String search;

    public AutocompleteSystem(String[] sentences, int[] times) {
        this.search = "";
        this.map = new HashMap<>();
        for (int i = 0; i < sentences.length; i++) {
            String sentence = sentences[i];
            int time = times[i];
            map.put(sentence, map.getOrDefault(sentence, 0) + time);
        }
    }

    public List<String> input(char c) {
        // if user entered initial character itself as # then don't update search and also if entered # as last char dont update the  search
        if (c == '#') {
            map.put(search, map.getOrDefault(search, 0) + 1);
            search = "";
            return new ArrayList<>();
        }
 
        search += c; // User is typing "i love .... "
        // min heap - Top 3 sentences need to be returned
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            if (map.get(a) == map.get(b)) {
                // if sentences added to the queue has same frequency then follow lexiographical
                // order
                return b.compareTo(a); // returns
            }
            return map.get(a) - map.get(b);
        });
        // now go over the HashMap and check for all the sentences(keys) that starts
        // with the i/p search term
        for (String sentence : map.keySet()) {
            // every sentence of the HashMap
            if (sentence.startsWith(search)) {
                pq.add(sentence);
                if(pq.size()>3) pq.poll();
            }
        }
        List<String> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(0, pq.poll());
        }
        return result;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */