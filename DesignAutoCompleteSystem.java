//Time Complexity :  O(n)
//Space Complexity : O(n)

import java.util.*;

class AutocompleteSystem {

    HashMap<String, Integer> map;
    StringBuilder sb;

    public AutocompleteSystem(String[] sentences, int[] times) {

        this.map = new HashMap<>();
        this.sb = new StringBuilder();
        for (int i = 0; i < sentences.length; i++) {
            map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
        }
    }

    public List<String> input(char c) {

        if (c == '#') {

            String search = sb.toString();

            map.put(search, map.getOrDefault(search, 0) + 1);
            sb = new StringBuilder();
            return new ArrayList<>();
        }
        sb.append(c);

        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {

            if (map.get(a) == map.get(b)) {
                return b.compareTo(a);
            } else {
                return map.get(a) - map.get(b);
            }
        });

        String prefix = sb.toString();

        for (String key : map.keySet()) {
            if (key.startsWith(prefix)) {
                pq.add(key);
            }
            if (pq.size() > 3) {
                pq.poll();
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