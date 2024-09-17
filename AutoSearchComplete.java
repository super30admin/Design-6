/*
method 1- brute force 
for every input we traverse the hashmap and get all the strings which start with given input
add all strings to priority and give the top 3 strings as output
time complexity: O(n)
space complexity: O(n) + o(k)
this is very ineeficeint if they are large number of strings given.
time complexity; O(N) + O(m log k)
space complexity: O(N)+O(3)


method2 - inorder to avoid searching entire hm 
we will build a trie data structure and at each node maintain a hashmap of string startting with that prefix and count.

method 3- 
maintain a list of strings at every node which are top 3 
time complexity; O(n) + O(n) + O(3) -> O(n)
space complexity: O(n)

*/
class TrieNode{
    //HashMap<String,Integer> countMap;
    TrieNode[]children;
    List<String> pq;
    TrieNode(){
        this.children = new TrieNode[128];
        this.pq = new ArrayList<>();
    }
    
    
}

class AutocompleteSystem {

    
    private void insertTrie(String s){
        TrieNode curr = root;
        for(int i = 0; i < s.length();i++){
            if(curr.children[s.charAt(i) - ' '] == null){
                curr.children[s.charAt(i)- ' '] = new TrieNode();
            }
            curr = curr.children[s.charAt(i) - ' '];
            //get the currlist
            List<String> currlist = curr.pq;
            //sort the list to keep top 3 by taking the word freq from hm
            if(!currlist.contains(s))currlist.add(s);
            Collections.sort(currlist,(a,b) -> {
                if(freqMap.get(a) == freqMap.get(b)){
                    return a.compareTo(b); //increasing
                }
                return freqMap.get(b) - freqMap.get(a); //decreasing
            });
            if(currlist != null && currlist.size() > 3)currlist.remove(3);
            curr.pq = currlist;
        }
    }    
    
    private List<String> searchTrie(String s){
        TrieNode curr = root;
        for(int i = 0; i < s.length();i++){
            char ch = s.charAt(i);
            if(curr.children[ch - ' '] == null){
                return new ArrayList<>();
            }
            curr = curr.children[ch - ' '];
        }
        return curr.pq;
    }
    
    
    
    HashMap<String,Integer> freqMap;
    TrieNode root;
    StringBuilder input;
    
    public AutocompleteSystem(String[] s, int[] times) {
       this.freqMap = new HashMap<>();
        this.root = new TrieNode();
        this.input = new StringBuilder();
        
        for(int i = 0;i < s.length;i++){
            freqMap.put(s[i], freqMap.getOrDefault(s[i] , 0) + times[i]);
            insertTrie(s[i]);
        }
    }
    
    
    public List<String> input(char c) {
        if(c == '#'){
            String str = input.toString();
            freqMap.put(str, freqMap.getOrDefault(str , 0) + 1);
            insertTrie(str);
            input = new StringBuilder();
            return new ArrayList<>();
        }
        input.append(c);
        List<String> result = searchTrie(input.toString());
       return result;
    }
}



/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */