package Design6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class AutocompleteSystem {

    // main storage for all the sentences will be the hashmap.
    HashMap<String, Integer> map = new HashMap<>();
    StringBuilder input;
    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        input = new StringBuilder();
        for(int i = 0 ; i < sentences.length ; i++) {
            map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
        }
        //PQ will come into picture whenever I need to search for a sentence.
    }

    public List<String> input(char c) {
        if(c == '#') {
            String in = input.toString();
            map.put(in, map.getOrDefault(in, 0) + 1);
            input = new StringBuilder();
            return new ArrayList<>();
        }
        input.append(c);
        // PriorityQueue<String> pq = new PriorityQueue<>((a, b)-> map.get(a) - map.get(b));

        PriorityQueue<String> pq = new PriorityQueue<>((a, b)-> {
            if(map.get(a) == map.get(b)) {
                return b.compareTo(a);
            }
            return map.get(a) - map.get(b);
        });

        for(String key: map.keySet()) {
            String in = input.toString();
            if(key.startsWith(in)) {
                pq.add(key);
                if(pq.size() > 3) {
                    pq.remove();
                }
            }
        }
        List<String> result = new ArrayList<>();
        while(!pq.isEmpty()) {
            result.add(0, pq.poll());
        }
        return result;
    }

    /**
     * Your AutocompleteSystem object will be instantiated and called as such:
     * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
     * List<String> param_1 = obj.input(c);
     */

    class AutocompleteSystem1 {
        class TrieNode {
            HashMap<String, Integer> map;
            HashMap<Character, TrieNode> children;

            private TrieNode() {
                map = new HashMap<>();
                children = new HashMap<>();
            }
        }
        TrieNode root;
        private void insert(String word, int times) {
            TrieNode curr = root;
            for(int i = 0 ; i < word.length() ; i++) {
                char c = word.charAt(i);
                if(!curr.children.containsKey(c)) {
                    curr.children.put(c, new TrieNode());
                }

                curr = curr.children.get(c);
                curr.map.put(word, curr.map.getOrDefault(word, 0) + times);
            }
        }

        //Search string prefix in the trie.
        private HashMap<String, Integer> search(String prefix) {
            TrieNode curr = root;
            for(int i = 0 ; i < prefix.length() ; i++) {
                char c = prefix.charAt(i);
                if(!curr.children.containsKey(c)) {
                    // prefix is not there in the hashmap
                    return new HashMap<>();
                }

                curr = curr.children.get(c);
            }
            return curr.map;
        }
        StringBuilder input;
        public AutocompleteSystem(String[] sentences, int[] times) {
            root = new TrieNode();
            input = new StringBuilder();
            for(int i = 0 ; i < sentences.length ; i++) {
                insert(sentences[i], times[i]);
            }
            //PQ will come into picture whenever I need to search for a sentence.
        }

        public List<String> input(char c) {
            if(c == '#') {
                String in = input.toString();
                insert(in, 1);
                input = new StringBuilder();
                return new ArrayList<>();
            }
            input.append(c);
            // PriorityQueue<String> pq = new PriorityQueue<>((a, b)-> map.get(a) - map.get(b));
            HashMap<String, Integer> countMap = search(input.toString());
            PriorityQueue<String> pq = new PriorityQueue<>((a, b)-> {
                if(countMap.get(a) == countMap.get(b)) {
                    return b.compareTo(a);
                }
                return countMap.get(a) - countMap.get(b);
            });


            for(String key: countMap.keySet()) {
                String in = input.toString();
                if(key.startsWith(in)) {
                    pq.add(key);
                    if(pq.size() > 3) {
                        pq.remove();
                    }
                }
            }
            List<String> result = new ArrayList<>();
            while(!pq.isEmpty()) {
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
    public static void main(String[] args) {

    }

}
