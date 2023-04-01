import java.util.Map;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
/*
Design Auto-Complete System
approach:
1. use a map for tracking strings and freq, use priority queue for getting top 3 hot strings
2. use a map + trie + priority queue
 */

public class AutoCompleteSystem {
    Map<String, Integer> map;
    StringBuilder input;
    Trie root;
    PriorityQueue<String> pq;
    class Trie {
        Trie[] children;
        List<String> words;

        public Trie() {
            this.children = new Trie[256];
            this.words = new ArrayList<>();
        }
    }

    public AutoCompleteSystem(String[] sentences, int[] times) {
        input = new StringBuilder();
        map = new HashMap<>();
        for (int i=0;i<sentences.length;i++) {
            map.put(sentences[i], map.getOrDefault(sentences[i], 0)+times[i]);
        }
        this.root = new Trie();

        for (int i=0;i<sentences.length;i++) {
            String curr = sentences[i];
            Trie temp = this.root;
            for (int j=0; j<curr.length();j++) {
                char currChar = curr.charAt(j);
                Trie[] neighbors = temp.children;
                if (neighbors[currChar-' ']==null)
                {
                    neighbors[currChar-' '] = new Trie();
                }
                neighbors[currChar-' '].words.add(curr);
                temp = neighbors[currChar-' '];
            }
        }
        pq = new PriorityQueue<>((a,b) -> {
            if (map.get(a)==map.get(b)) {
                return b.compareTo(a);
            }
            return map.get(a)-map.get(b);
        });
    }

    private List<String> getWordsList(String prefix) {
        Trie curr = this.root;

        for (int i=0;i<prefix.length();i++) {
            char c = prefix.charAt(i);
            curr = curr.children[c-' '];
        }
        if (curr.words!=null && curr.words.size()!=0)
        return curr.words;

        return Collections.emptyList();
    }

    private void insertIntoTrie(String word) {
        Trie curr = root;
        for (int i=0;i<word.length();i++) {
            char c = word.charAt(i);
            Trie []kids = curr.children;
            if (kids[c-' ']==null) kids[c-' '] = new Trie();
            kids[c-' '].words.add(word);
            curr = kids[c-' '];
        }
    }

    //O(number of strings that starts with given character) <<< n
    List<String> input(char c) {
        if (c=='#') {
            String inputWord = input.toString();
            map.put(inputWord, map.getOrDefault(inputWord, 0)+1);
            input = new StringBuilder();
            insertIntoTrie(inputWord);
            return Collections.emptyList();
        }
        input.append(c);
        String prefix = input.toString();
        List<String> wordsWithPrefix = getWordsList(prefix);

        for (String s: wordsWithPrefix) {
            pq.add(s);
            if (pq.size()>3) {
                pq.poll();
            }
        }
        List<String> result = new ArrayList<>();

        while (!pq.isEmpty()) {
            result.add(0, pq.poll());
        }
        return result;
    }

    public static void main(String []args) {
        String[] strings = new String[]{"i love you", "island","ironman", "i love leetcode"};
        int[] times = new int[]{5,3,2,2};
        AutoCompleteSystem autoCompleteSystem = new AutoCompleteSystem(strings, times);
        autoCompleteSystem.input('i');
        autoCompleteSystem.input(' ');
        autoCompleteSystem.input('#');
    }
}
