import java.util.*;
//Time complexity: O(N) -> N is the total number of characters int the search string
//Space complexity: O(N*K) n= number of characters in the sentence and K is the total number of sentences
class AutoCompleteSystem {
    Map<String, Integer> map = new HashMap<>();
    StringBuilder str;

    class Trie{
        Map<Character, Trie> children;
        List<String> mostFrequent;

        Trie(){
            children = new HashMap<>();
            mostFrequent = new ArrayList<>();
        }
    }
    Trie root;

    public void addTrie(String str){
        Trie curr = root;

        for(int i =0; i< str.length(); i++){
            char ch = str.charAt(i);
            if(!curr.children.containsKey(ch)) curr.children.put(ch, new Trie());
            curr = curr.children.get(ch);
            List<String> currList = curr.mostFrequent;
            if(!currList.contains(str)) currList.add(str);
            Collections.sort(currList, (a, b) ->{
                if(map.get(b) == map.get(a)) return a.compareTo(b);
                return map.get(b) - map.get(a);
            });
            if(currList.size() > 3) currList.remove(currList.size()-1);

        }

    }

    public List<String> searchTrie(String str){
        Trie curr = root;

        for(int i=0; i< str.length(); i++){
            char ch = str.charAt(i);
            if(!curr.children.containsKey(ch)) return new ArrayList<>();
            curr = curr.children.get(ch);
        }

        return curr.mostFrequent;

    }

    public AutoCompleteSystem(String[] sentences, int[] times) {
        root = new Trie();
        str = new StringBuilder();

        for(int i =0; i< sentences.length; i++){
            map.put(sentences[i], map.getOrDefault(sentences[i], 0)+times[i]);
            addTrie(sentences[i]);
        }
    }

    public List<String> input(char c) {
        if(c == '#'){
            map.put(str.toString(), map.getOrDefault(str.toString(), 0) + 1);
            addTrie(str.toString());
            str = new StringBuilder();
            return new ArrayList<>();
        }
        str.append(c);

        List<String> list = searchTrie(str.toString());
        return list;

    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */