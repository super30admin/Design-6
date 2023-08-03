import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class AutoComplete {
    class TrieNode {
        TrieNode[] children;
        List<String> startsWith;

        public TrieNode() {
            this.children = new TrieNode[256];
            this.startsWith = new ArrayList<>();
        }
    }

    private void insert(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (curr.children[c - ' '] == null) {
                curr.children[c - ' '] = new TrieNode();
            }
            curr = curr.children[c - ' '];
            List<String> top3 = curr.startsWith;
            if (!top3.contains(word)) {
                top3.add(word);
            }
            Collections.sort(top3, (a, b) -> {
                if (map.get(a) == map.get(b)) {
                    return a.compareTo(b);
                }
                return map.get(b) - map.get(a);
            });
            if (top3.size() > 3) {
                top3.remove(top3.size() - 1);
            }
        }
    }

    private List<String> searchTrie(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (curr.children[c - ' '] == null)
                return new ArrayList<>();
            curr = curr.children[c - ' '];
        }
        return curr.startsWith;
    }

    HashMap<String, Integer> map;
    StringBuilder search;
    TrieNode root;

    public AutoComplete(String[] sentences, int[] times) {
        this.map = new HashMap<>();
        this.search = new StringBuilder();
        this.root = new TrieNode();
        for (int i = 0; i < sentences.length; i++) {
            String sentence = sentences[i];
            int time = times[i];
            map.put(sentence, map.getOrDefault(sentence, 0) + time);
            insert(sentence);
        }
        // System.out.println(map);
    }

    public List<String> input(char c) {
        if (c == '#') {
            String searchTerm = search.toString();
            this.search = new StringBuilder();
            map.put(searchTerm, map.getOrDefault(searchTerm, 0) + 1);
            insert(searchTerm);
            return new ArrayList<>();
        }

        search.append(c);
        String searchTerm = search.toString();
        return searchTrie(searchTerm);
    }

    public static void main(String[] args) {
        String[] sentences = { "i love you", "island", "ironman", "i love leetcode" };
        int[] times = { 5, 3, 2, 2 };

        AutoComplete autoComplete = new AutoComplete(sentences, times);

        char[] input = { 'i', ' ', 'a', ' ' };
        for (char c : input) {
            List<String> suggestions = autoComplete.input(c);
            System.out.println("Input: " + c);
            System.out.println("Suggestions: " + suggestions);
        }

        // Adding a new sentence and then searching again
        autoComplete.input('#');
        char[] newInput = { 'i', ' ' };
        for (char c : newInput) {
            List<String> suggestions = autoComplete.input(c);
            System.out.println("Input: " + c);
            System.out.println("Suggestions: " + suggestions);
        }
    }
}
