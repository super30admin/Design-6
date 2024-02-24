import java.util.*;

public class AutocompleteSystem {
    Map<String, Integer> freq = new HashMap<>();
    StringBuilder input = new StringBuilder();
    Trie trie = new Trie();
    // TC: O(N) where N is number of sentences
    // SC: O(N) where N is number of sentences
    public AutocompleteSystem(String[] sentences, int[] times) {
        int n = sentences.length;
        for (int i = 0; i < n; i++) {
            freq.put(sentences[i], times[i]);
            trie.insert(sentences[i], freq);
        }
    }

    // TC: O(N) where N is number of sentences
    // SC: O(N) where N is number of sentences
    public List<String> input(char c) {
        if (c == '#') {
            String s = input.toString();
            freq.put(s, freq.getOrDefault(s, 0) + 1);
            trie.insert(s, freq);
            input = new StringBuilder();
            return new ArrayList<>();
        }
        input.append(c);
        String prefix = input.toString();
        return trie.getPrefixWords(prefix);
    }
}

class TrieNode {
    TrieNode[] children;
    List<String> prefixWords;
    public TrieNode() {
        children = new TrieNode[256];
        prefixWords = new ArrayList<>();
    }
}

class Trie {
    TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    // TC: O(N) where N is length of word
    public void insert(String word, Map<String, Integer> freq) {
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            if (curr.children[ch - ' '] == null) {
                curr.children[ch - ' '] = new TrieNode();
            }
            List<String> prefixWords = curr.children[ch - ' '].prefixWords;
            if (!prefixWords.contains(word)) {
                prefixWords.add(word);
            }
            Collections.sort(prefixWords, (a, b) -> {
                if (freq.get(a) == freq.get(b)) {
                    return a.compareTo(b);
                }
                return freq.get(b) - freq.get(a);
            });
            if (prefixWords.size() > 3) {
                prefixWords.remove(prefixWords.size() - 1);
            }
            curr = curr.children[ch - ' '];
        }
    }

    // TC: O(N) where N is length of prefix
    public List<String> getPrefixWords(String prefix) {
        TrieNode curr = root;
        for (char ch : prefix.toCharArray()) {
            if (curr.children[ch - ' '] == null) {
                return new ArrayList<>();
            }
            curr = curr.children[ch - ' '];
        }
        return curr.prefixWords;
    }
}

class AutocompleteSystemUsingMap {
    Map<String, Integer> freq = new HashMap<>();
    StringBuilder input = new StringBuilder();
    public AutocompleteSystemUsingMap(String[] sentences, int[] times) {
        int n = sentences.length;
        for (int i = 0; i < n; i++) {
            freq.put(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        if (c == '#') {
            String s = input.toString();
            freq.put(s, freq.getOrDefault(s, 0) + 1);
            input = new StringBuilder();
            return new ArrayList<>();
        }
        input.append(c);
        String prefix = input.toString();
        PriorityQueue<String> minHeap = new PriorityQueue<>((a, b) -> {
            if (freq.get(a) == freq.get(b)) {
                return b.compareTo(a);
            }
            return freq.get(a) - freq.get(b);
        });
        for (String s : freq.keySet()) {
            if (s.startsWith(prefix)) {
                minHeap.add(s);
                if (minHeap.size() > 3) {
                    minHeap.poll();
                }
            }
        }
        List<String> res = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            res.add(0, minHeap.poll());
        }
        return res;
    }
}
