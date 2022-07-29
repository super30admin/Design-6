// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

import java.util.*;

class AutocompleteSystem {

    // maintain hashmap for storing sentences and it's occurences
    HashMap<String, Integer> map;
    // stringBuilder for maintaing search string
    StringBuilder sb;

    public AutocompleteSystem(String[] sentences, int[] times) {
        // intialize map and stringBuilder
        map = new HashMap<>();
        sb = new StringBuilder();
        // add all the sentences in map
        for (int i = 0; i < sentences.length; i++) {
            String sentence = sentences[i];
            int time = times[i];
            map.put(sentence, time);
        }
    }

    public List<String> input(char c) {
        // first check if char is # then reset stringbuilder and add this into the map
        // and return
        // empty arrayList
        if (c == '#') {
            String search = sb.toString();
            map.put(search, map.getOrDefault(search, 0) + 1);
            // reset sb
            sb = new StringBuilder();
            // return empty list
            return new ArrayList<>();
        }
        // append char c in stringBuilder
        sb.append(c);
        // custom priorityQueue
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            int ca = map.get(a);
            int cb = map.get(b);
            // check if frequency is same we sort this by lexiographically
            if (ca == cb) {
                return b.compareTo(a);
            }
            return ca - cb;
        });
        String search = sb.toString();
        for (String key : map.keySet()) {
            // go over all the sentences and get top 3 sentences that is starts with search
            // string
            if (key.startsWith(search)) {
                pq.add(key);
                // if pq size is greater than 3 poll element from the queue
                if (pq.size() > 3) {
                    pq.poll();
                }
            }

        }
        // add all remaining element in the result and return the result
        List<String> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(0, pq.poll());
        }
        return result;
    }

    public static void main(String[] args) {
        String[] sentences = new String[] { "i love you", "island", "iroman", "i love leetcode" };
        int[] times = new int[] { 5, 3, 2, 2 };
        AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
        System.out.println(obj.input('i'));
        // return ["i love you", "island", "i love leetcode"]. There are four sentences
        // that have prefix "i". Among them, "ironman" and "i love leetcode" have same
        // hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love
        // leetcode" should be in front of "ironman". Also we only need to output top 3
        // hot sentences, so "ironman" will be ignored.
        System.out.println(obj.input(' '));
        // return ["i love you", "i love leetcode"]. There are only two sentences that
        // have prefix "i ".
        System.out.println(obj.input('a'));
        // return []. There are no sentences that have prefix "i a".
        System.out.println(obj.input('#'));
        // return []. The user finished the input, the sentence "i a" should be saved as
        // a historical sentence in system. And the following input will be counted as a
        // new search.
    }
}