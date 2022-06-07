/*
Problem: https://leetcode.com/problems/design-search-autocomplete-system/
TC: O(n * m) where n is the number of strings and m is the average length of strings
SC: O(n * m)
*/
class TrieNode {
    HashMap<Character, TrieNode> children;
    HashMap<String, Integer> stringFreqMap;
    
    public TrieNode() {
        children = new HashMap<>();
        stringFreqMap = new HashMap<>();
    }
}

class Trie {
    TrieNode root;
    
    public Trie() {
        root = new TrieNode();
    }
    
    public void buildTrie(String sentences[], int times[]) {
        for (int i = 0; i < sentences.length; ++i) {
            insert(sentences[i], times[i]);
        }
    }
    
    public void insert(String sentence, int times) {
        TrieNode cur = root;
        String contStr = "";
        
        for (int i = 0; i < sentence.length(); ++i) {
            char c = sentence.charAt(i);
            
            if (!cur.children.containsKey(c)) {
                cur.children.put(c, new TrieNode());
            }
            
            cur = cur.children.get(c);
            cur.stringFreqMap.put(sentence, cur.stringFreqMap.getOrDefault(sentence, 0) + times);
        }
    }
    
    public List<String> getSuggestions(String word) {
        HashMap<String, Integer> wordFreqMap = getAllSuggestionsForWord(word);
        
        // Sort in descending order of frequency and then lexicographically
        PriorityQueue<String> pq = new PriorityQueue<>((s1, s2) -> {
           if (wordFreqMap.get(s1) == wordFreqMap.get(s2)) {
               return s2.compareTo(s1);
           } else {
               return wordFreqMap.get(s1) - wordFreqMap.get(s2);
           }
        });
        
        for (String s : wordFreqMap.keySet()) {
            // if (s.startsWith(word)) {
                pq.offer(s);
                if (pq.size() > 3) {
                    pq.poll();
                }
            // }
            
        }
        
        List<String> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(0, pq.poll());
        }
        return result;
    }
    
    private HashMap<String, Integer> getAllSuggestionsForWord(String word) {
        TrieNode cur = root;
        
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            
            if (!cur.children.containsKey(c)) {
                return new HashMap<>();
            }
            cur = cur.children.get(c);
        }
        
        return cur.stringFreqMap;
    }
}
class AutocompleteSystem {

    Trie trie;
    StringBuilder currentString;
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        trie = new Trie();
        trie.buildTrie(sentences, times);
        currentString = new StringBuilder();
    }
    
    public List<String> input(char c) {
        if (c == '#') {
            trie.insert(currentString.toString(), 1);
            currentString.setLength(0);
            return new ArrayList<>();
        }
        
        currentString.append(c);
        return trie.getSuggestions(currentString.toString());
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */