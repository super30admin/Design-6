//# Design-6
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

//## Problem1 Design Phone Directory (https://leetcode.com/problems/design-phone-directory/)
// time complexity: O(1) for all functions
// space complexity: O(n) -> depends on value of maxNumbers(n= maxNumbers)

class PhoneDirectory {
    Queue<Integer> q;
    Set<Integer> set;
    public PhoneDirectory(int maxNumbers) {
        q = new LinkedList<>();
        set = new HashSet<>();
        for(int i=0; i < maxNumbers; i++) {
            q.add(i);
            set.add(i);
        }
    }
    
    public int get() {
        if(q.isEmpty()) return -1;
        int result = q.poll();
        set.remove(result);
        return result;
    }
    
    public boolean check(int number) {
        return set.contains(number);
    }
    
    public void release(int number) {
        if(!set.contains(number)) {
            q.add(number);
            set.add(number);
        }
    }

/*    
public static void main(String[] args) {
        int maxNumbers = 10000;
        int number = 0;
        PhoneDirectory obj = new PhoneDirectory(maxNumbers);
        int param_1 = obj.get();
        boolean param_2 = obj.check(number);
        System.out.println(param_1);
        System.out.println(param_2);
        number = 500;
        param_1 = obj.get();
        param_2 = obj.check(number);
        obj.release(number);
        System.out.println(param_1);
        System.out.println(param_2);
    }
*/
}
    
//## Problem2 Design Autocomplete System (https://leetcode.com/problems/design-search-autocomplete-system/)
class AutocompleteSystem {
    class TrieNode{
        List<String> li;
        HashMap<Character, TrieNode> children;
        public TrieNode() {
            li = new ArrayList<>();
            children = new HashMap<>();
        }
    }
    private void insert(String sentence){
        TrieNode curr = root;
        for(int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if(!curr.children.containsKey(c))
                curr.children.put(c, new TrieNode());
            curr = curr.children.get(c);
            if(!curr.li.contains(sentence)) {
                curr.li.add(sentence);
            }
            List<String> currLi = curr.li;
            Collections.sort(currLi, (a, b) -> {
                if(map.get(a) == map.get(b))
                    return a.compareTo(b);
                return map.get(b) - map.get(a);
            });
            if(curr.li.size() > 3)
                currLi.remove(currLi.size()- 1);
        }
    }
    private List<String> search(String prefix){
        TrieNode curr = root;
        for(int i = 0; i< prefix.length(); i++) {
            char c = prefix.charAt(i);
            if(!curr.children.containsKey(c)) return new ArrayList<>();
            curr = curr.children.get(c);
        }
        return curr.li;
    }
    HashMap<String, Integer> map;
    StringBuilder input;
    TrieNode root;
    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        input = new StringBuilder();
        root  = new TrieNode();
        for(int i=0; i< sentences.length; i++) {
            String currSent = sentences[i];
            map.put(currSent, map.getOrDefault(currSent, 0) + times[i]);
            insert(currSent); 
        }
    }
    
    public List<String> input(char c) {
        if(c == '#') {
            String in = input.toString();
            map.put(in, map.getOrDefault(in, 0) + 1);
            insert(in);
            input = new StringBuilder();
            return new ArrayList<>();
        }
        input.append(c);
        String in = input.toString();
        return search(in);
    }
}