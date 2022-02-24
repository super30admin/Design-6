package design6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class DesignSearchAutocompleteSystem {
	//Time Complexity : O(m), where m is the length of search keyword
	//Space Complexity : O(n), for HashMap
	//Did this code successfully run on Leetcode : Yes
	//Any problem you faced while coding this : No
	class Trie {
        Trie[] children;
        List<String> list;
        
        public Trie() {
            this.children = new Trie[256];
            list = new ArrayList<>();
        }
    }
    
    Trie root;
    public void insert(String sentence) {
        Trie curr = root;
        for(char c: sentence.toCharArray()) {
            if(curr.children[c - ' '] == null)
                curr.children[c - ' '] = new Trie();
            curr = curr.children[c - ' '];
            if(!curr.list.contains(sentence))
                curr.list.add(sentence);
            curr.list.sort((a, b) -> {
                return map.get(a) == map.get(b) ? a.compareTo(b) : map.get(b) - map.get(a);
            });
            if(curr.list.size() > 3)
                curr.list.remove(curr.list.size() - 1);
        }
    }
    
    public List<String> searchWithPrefix(String word) {
        Trie curr = root;
        for(char c: word.toCharArray()) {
            if(curr.children[c - ' '] == null)
                return new ArrayList<>();
            curr = curr.children[c - ' '];
        }
        return curr.list;
    }
    
    Map<String, Integer> map;
    public DesignSearchAutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        root = new Trie();
        
        for(int i=0; i<sentences.length; i++) {
            map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
            insert(sentences[i]);
        }
    }
    
    StringBuilder sb = new StringBuilder();
    public List<String> input(char c) {
        if(c == '#') {
            String search = sb.toString();
            map.put(search, map.getOrDefault(search, 0) + 1);
            insert(search);
            sb = new StringBuilder();
            return new ArrayList<>();
        }
        sb.append(c);
        return searchWithPrefix(sb.toString());
    }
    
    
	//Time Complexity : O(n + m), where n is length of sentences array and m is the length of search keyword
	//Space Complexity : O(n), for HashMap and constant i.e 3, for MinHeap
	//Did this code successfully run on Leetcode : Yes
	//Any problem you faced while coding this : No
	Map<String, Integer> map;
    PriorityQueue<String> minHeap;
    public DesignSearchAutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        minHeap = new PriorityQueue<>((a, b) -> {
            return map.get(a) == map.get(b) ? b.compareTo(a) : map.get(a) - map.get(b);
        });
        for(int i=0; i<sentences.length; i++)
            map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
    }
    
    StringBuilder sb = new StringBuilder();
    public List<String> input(char c) {
        if(c == '#') {
            map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
            sb = new StringBuilder();
            return new ArrayList<>();
        }
        sb.append(c);
        for(String s: map.keySet()) {
            if(s.startsWith(sb.toString()))
                minHeap.offer(s);
            if(minHeap.size() > 3)
                minHeap.poll();
        }
        
        List<String> res = new ArrayList<>();
        while(!minHeap.isEmpty())
            res.add(0, minHeap.poll());
        return res;
    }
}
