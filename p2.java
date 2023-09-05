// Time Complexity :O(n) for input where n is the length of the prefix
// Space Complexity :O(1) for input
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :no


// Your code here along with comments explaining your approach


class AutocompleteSystem {
    HashMap<String, Integer> map;
    Trie head;
    class Trie{
        Trie[] children;
        List<String> pq;
        public Trie(){
            this.children = new Trie[256];
            this.pq = new ArrayList<>();
        }
    }

//During the insert function, we are also updating the list of words. The max length of the list is 3 so after sorting we romove the 4th word
    public void insert(String sentence){
        Trie node = head;
        for(int i=0; i<sentence.length();i++){
            char c = sentence.charAt(i);
            if(node.children[c - ' '] == null){
                node.children[c - ' '] = new Trie();
            }
            node = node.children[c - ' '];
            if(!node.pq.contains(sentence)) node.pq.add(sentence);
            Collections.sort(node.pq, ((a,b) -> {
                if(map.get(a) == map.get(b)){
                    return a.compareTo(b);
                }
                else return map.get(b)-map.get(a);
            }));
            if(node.pq.size()>3) node.pq.remove(3);
            
        }
    }
    public AutocompleteSystem(String[] sentences, int[] times) {
        this.head = new Trie();
        this.map = new HashMap();
        //put the sentence and it's frequency in hashmap and trie
        for(int i=0; i<sentences.length; i++){
            map.put(sentences[i], map.getOrDefault(sentences[i],0)+times[i]);
            insert(sentences[i]);
            
        }

        
    }

    String s = "";
    
    public List<String> input(char c) {
        List<String> result = new ArrayList<>();
        //If we encounter #, we update the frequency of the word before # and update the list in trie too
        if(c == '#'){
            map.put(s, map.getOrDefault(s,0)+1);
            insert(s);
            s = "";
            
            return result;
        }

        s = s + c;
        Trie node = head;
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(node.children[ch - ' '] == null){
                return result;
            }
            node = node.children[ch - ' '];
        }
        

        return node.pq;

    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */