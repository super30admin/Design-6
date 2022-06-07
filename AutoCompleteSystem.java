//Using hashmap and and priority queue and iterating over hashmap to get the sentence

//Time Complexity : 0(n) where n is the no. of input strings
//Space Complexity: 0(n)
//Did it successfully run on leetcode: No, I don't have leetcode premium
//Did you face any problem while coding: No

//In brief explain your code


import java.awt.image.TileObserver;
import java.util.*;

public class AutoCompleteSystem {
    HashMap<String, Integer> map;
    StringBuilder currString;

    public AutoCompleteSystem(String [] sentences, int [] times){
        map = new HashMap<>();
        currString = new StringBuilder();
        for(int i = 0; i < sentences.length; i++){
            String sentence = sentences[i];
            map.put(sentence, map.getOrDefault(sentence, 0)+ times[i]);
        }
    }

    public List<String> input(char c){
        if(c == '#'){
            String inputString = currString.toString();
            map.put(inputString, map.getOrDefault(inputString, 0) + 1);
            currString = new StringBuilder();
            return new ArrayList<>();
        }
        currString.append(c);
        PriorityQueue<String> pq = new PriorityQueue<>((a,b) -> {
            if(map.get(a) == map.get(b)){
                return b.compareTo(a);
            }
            return map.get(a) - map.get(b);
        });
        for(String key : map.keySet()){
            String s = currString().toString();
            if(map.containsKey(s)){
                pq.add(key);
                if(pq.size() > 3){
                    pq.poll();
                }
            }
        }
        List<String> result = new ArrayList<>();
        while (!pq.isEmpty()){
            result.add(0, pq.poll());
        }
        return result;
    }
}

//Using TrieNode

//Time Complexity : 0(n) where n is the no. of input strings
//Space Complexity: 0(n)
//Did it successfully run on leetcode: No, I don't have leetcode premium
//Did you face any problem while coding: No

//In brief explain your code
public class AutoCompleteSystem {
    class TrieNode{
        HashMap<Character, TrieNode> children;
        HashMap<String, Integer> map;
        public TrieNode(){
            children = new HashMap<>();
            map = new HashMap<>();
        }
    }

    TrieNode root;
    public void insert(String sentence, int times){
        TrieNode curr = root;
        for (int i = 0; i < sentence.length(); i++){
            char c = sentence.charAt(i);
            if (!curr.children.containsKey(c)){
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
            curr.map.put(sentence, curr.map.getOrDefault(sentence, 0) + times);
        }
    }

    private HashMap<String, Integer> search(String sentence){
        TrieNode curr = root;
        for (int i = 0; i < sentence.length(); i++){
            char c = sentence.charAt(i);
            if(!curr.children.containsKey(c)){
                return new HashMap<>();
            }
            curr = curr.children.get(c);
        }
        return curr.map;
    }


    HashMap<String, Integer> map;
    StringBuilder currString;

    public AutoCompleteSystem(String [] sentences, int [] times){
        root = new TrieNode();
        map = new HashMap<>();
        currString = new StringBuilder();
        for(int i = 0; i < sentences.length; i++){
            String sentence = sentences[i];
            insert(sentence, times[i]);
        }
    }

    public List<String> input(char c){
        if(c == '#'){
            String inputString = currString.toString();
            insert(inputString, 1);
            currString = new StringBuilder();
            return new ArrayList<>();
        }
        currString.append(c);
        HashMap<String,Integer> mymap = search(currString.toString());
        PriorityQueue<String> pq = new PriorityQueue<>((a,b) -> {
            if(mymap.get(a) == mymap.get(b)){
                return b.compareTo(a);
            }
            return mymap.get(a) - map.get(b);
        });
        for(String key : mymap.keySet()){
            String s = currString().toString();
            if(map.containsKey(s)){
                pq.add(key);
                if(pq.size() > 3){
                    pq.poll();
                }
            }
        }
        List<String> result = new ArrayList<>();
        while (!pq.isEmpty()){
            result.add(0, pq.poll());
        }
        return result;
    }
}

//Using arraylist

public class AutoCompleteSystem {
    class TrieNode{
        HashMap<Character, TrieNode> children;
        List<String> words;
        public TrieNode(){
            children = new HashMap<>();
            words = new ArrayList<>();
        }
    }

    TrieNode root;
    public void insert(String sentence, int times){
        TrieNode curr = root;
        for (int i = 0; i < sentence.length(); i++){
            char c = sentence.charAt(i);
            if(!curr.children.containsKey(c)){
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
            List<String> temp = curr.words;
            if(!temp.contains(sentence)){
                temp.add(sentence);
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
            curr.words = temp;
        }
    }

    private List<String> search(String sentence){
        TrieNode curr = root;
        for (int i = 0; i< sentence.length(); i++){
            char c = sentence.charAt(i);
            if(!curr.children.containsKey((c))){
                return new ArrayList<>();
            }
            curr = curr.children.get(c);
        }
        return curr.words;
    }


    HashMap<String, Integer> map;
    StringBuilder currString;

    public AutoCompleteSystem(String [] sentences, int [] times){
        root = new TrieNode();
        map = new HashMap<>();
        currString = new StringBuilder();
        for(int i = 0; i < sentences.length; i++){
            String sentence = sentences[i];
            map.put(sentence, map.getOrDefault(sentence, 0)+ times[i]);
            insert(sentence, times[i]);
        }
    }

    public List<String> input(char c){
        if(c == '#'){
            String inputString = currString.toString();
            map.put(inputString, map.getOrDefault(inputString, 0) + 1);
            insert(inputString, 1);
            currString = new StringBuilder();
            return new ArrayList<>();
        }
        currString.append(c);

        return search(currString.toString());
    }
}