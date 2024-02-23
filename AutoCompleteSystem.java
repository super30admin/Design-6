/*
* Did this code successfully run on Leetcode : YES
* 
* Any problem you faced while coding this : NO
* 
* Time Complexity: O(N)
    N = total sentences in dictionary
* 
* Space Complexity: O(1)
* 
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

class AutocompleteSystem {
    HashMap<String, Integer> hmap;

    StringBuilder input;

    public AutocompleteSystem(String[] sentences, int[] times) {
        int n = sentences.length;

        this.hmap = new HashMap<>();

        for (int index = 0; index < n; index++) {
            hmap.put(sentences[index], 
                hmap.getOrDefault(sentences[index], 0) + times[index]);
        }

        this.input = new StringBuilder();
    }

    private List<String> getHotSentences() {
        // min heap
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            int countA = hmap.get(a);
            int countB = hmap.get(b);

            if (countA == countB) {
                return b.compareTo(a);
            }

            return countA - countB;
        });

        String inputStr = input.toString();

        for (String sentence : hmap.keySet()) {
            if (sentence.startsWith(inputStr)) {
                pq.add(sentence);
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

    private void clearInput() {
        String inputStr = input.toString();

        hmap.put(inputStr, hmap.getOrDefault(inputStr, 0) + 1);

        input = new StringBuilder();
    }

    public List<String> input(char c) {
        if (c == '#') {
            clearInput();

            return new ArrayList<>();
        } else {
            input.append(c);

            return getHotSentences();
        }
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */