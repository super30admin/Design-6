// Time Complexity : O(n)
// Space Complexity : O(n)

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
        if (c == '#') {
            String search = sb.toString();
            map.put(search, map.getOrDefault(search, 0) + 1);
            // reset sb
            sb = new StringBuilder();
            // return empty list
            return new ArrayList<>();
        }
        sb.append(c);
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            int ca = map.get(a);
            int cb = map.get(b);
            if (ca == cb) {
                return b.compareTo(a);
            }
            return ca - cb;
        });
        String search = sb.toString();
        for (String key : map.keySet()) {
   
            if (key.startsWith(search)) {
                pq.add(key);
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

        System.out.println(obj.input(' '));

        System.out.println(obj.input('a'));
     System.out.println(obj.input('#'));

    }
} 