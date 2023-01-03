package com.madhurima;

import java.util.*;

public class AutoCompleteSystem {
}

//TC = O(nLogk) i.e O(nLog3) = O(n) for each call to input method. K is size of min heap
//SC = O(n), where n is unique sentences
class AutocompleteSystemBruteForce {
    //brute force

    HashMap<String, Integer> map;
    StringBuilder sb;

    public AutocompleteSystemBruteForce(String[] sentences, int[] times) {
        map = new HashMap<>();
        sb = new StringBuilder();
        for(int i = 0; i < sentences.length; i++){
            map.put(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        if(c == '#'){
            String sentence = sb.toString();
            map.put(sentence, map.getOrDefault(sentence, 0) + 1);
            sb = new StringBuilder();
            return new ArrayList<>();
        }

        sb.append(c);
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            if(map.get(a) == map.get(b)){
                return b.compareTo(a);
            }
            return map.get(a) - map.get(b);
        });

        for(String key: map.keySet()){
            if(key.startsWith(sb.toString())){
                pq.add(key);
                if(pq.size() > 3){
                    pq.poll();
                }
            }
        }

        List<String> result = new ArrayList<>();
        while(!pq.isEmpty()){
            result.add(0, pq.poll());
        }

        return result;

    }
}

//TC = O(l + n), l is avg length of word, n is keys present in sub - hashmap,
// better than brute force as we are not going over the entire hashmap keySet() for every call to input
//SC = O(n), where n is unique sentences
class AutocompleteSystemUsingTrie {
    //Using Trie

    class TrieNode{
        HashMap<Character, TrieNode> children;
        HashMap<String, Integer> map;
        public TrieNode(){
            children = new HashMap<>();
            map = new HashMap<>();
        }
    }

    TrieNode root;

    private void insert(String word, int times){
        TrieNode curr = root;

        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(!curr.children.containsKey(c)){
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
            curr.map.put(word, curr.map.getOrDefault(word, 0) + times);
        }
    }

    private HashMap<String, Integer> search(String word){
        TrieNode curr = root;

        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(!curr.children.containsKey(c)){
                return new HashMap<>();
            }
            curr = curr.children.get(c);
        }

        return curr.map;
    }


    StringBuilder sb;

    public AutocompleteSystemUsingTrie(String[] sentences, int[] times) {
        // map = new HashMap<>();
        root = new TrieNode();
        sb = new StringBuilder();

        for(int i = 0; i < sentences.length; i++){
            String sentence = sentences[i];
            insert(sentence, times[i]);
        }
    }

    public List<String> input(char c) {
        if(c == '#'){
            String sentence = sb.toString();
            // map.put(sentence, map.getOrDefault(sentence, 0) + 1);
            insert(sentence, 1);
            sb = new StringBuilder();
            return new ArrayList<>();
        }

        sb.append(c);

        HashMap<String, Integer> map = search(sb.toString());

        PriorityQueue<String> pq = new PriorityQueue<>((a,b) -> {
            if(map.get(a) == map.get(b)){
                return b.compareTo(a);
            }
            return map.get(a) - map.get(b);
        });

        for(String key: map.keySet()){
            // if(key.startsWith(sb.toString())){
            pq.add(key);
            if(pq.size() > 3){
                pq.poll();
            }
            // }
        }

        List<String> result = new ArrayList<>();
        while(!pq.isEmpty()){
            result.add(0, pq.poll());
        }

        return result;

    }
}


//TC = O(nl) n is number of sentences, l is avg length of word
//SC = O(n)
class AutocompleteSystemOptimizedTrie {
    //Optimizing Trie solution by keeping list of top 3 phrases instead of hashmap of all such phrases

    class TrieNode{
        HashMap<Character, TrieNode> children;
        List<String> pq;
        public TrieNode(){
            children = new HashMap<>();
            pq = new ArrayList<>();
        }
    }

    TrieNode root;

    private void insert(String word, int times){
        TrieNode curr = root;

        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(!curr.children.containsKey(c)){
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
            List<String> temp = curr.pq;
            if(!temp.contains(word)){
                temp.add(word);
            }
            Collections.sort(temp, (a, b) -> {
                if(map.get(a) == map.get(b)){
                    return a.compareTo(b);
                }
                return map.get(b) - map.get(a);
            });

            if(temp.size() > 3){
                temp.remove(temp.size() - 1);
            }

            curr.pq = temp;

        }
    }

    private List<String> search(String word){
        TrieNode curr = root;

        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(!curr.children.containsKey(c)){
                return new ArrayList<>();
            }
            curr = curr.children.get(c);
        }

        return curr.pq;
    }


    HashMap<String, Integer> map;
    StringBuilder sb;

    public AutocompleteSystemOptimizedTrie(String[] sentences, int[] times) {
        map = new HashMap<>();
        root = new TrieNode();
        sb = new StringBuilder();

        for(int i = 0; i < sentences.length; i++){
            String sentence = sentences[i];
            map.put(sentence, times[i]);
            insert(sentence, times[i]);
        }
    }

    public List<String> input(char c) {
        if(c == '#'){
            String sentence = sb.toString();
            map.put(sentence, map.getOrDefault(sentence, 0) + 1);
            insert(sentence, 1);
            sb = new StringBuilder();
            return new ArrayList<>();
        }

        sb.append(c);

        return search(sb.toString());

    }
}
