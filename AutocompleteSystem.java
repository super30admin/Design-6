// Time Complexity : 0(n log k)
// Space Complexity : 0(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

class AutocompleteSystem {
    StringBuilder builder;
    TrieNode curr;
    TrieNode root;
    //measuring number of times a sentence is occuring
    Map<String, Integer> hotness;
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        curr = root;
        hotness = new HashMap<>();
        builder = new StringBuilder();
        //iterate through sentences array
        for(int i = 0; i< sentences.length;i++){
            addToTrie(root,sentences[i],times[i]);
        }
    }
    public List<String> input(char c) {
        //when end of search is reached
        if(c == '#'){
            //make trie for sentence
            addToTrie(root,builder.toString(),1);
            builder.setLength(0);
            curr = root;
            return new ArrayList<>();
        } else {
            //if TrieNode not present then initialize new TrieNode
            if(curr.children[c] == null) {
                curr.children[c] = new TrieNode();
            }
            //add to string builder
            builder.append(c);
            curr = curr.children[c];
            return curr.pq;
        }
    }
    //add sentence to Trie
    private void addToTrie(TrieNode node, String s, int t){
        int i = 0;
        hotness.put(s,hotness.getOrDefault(s,0)+t);
        while(i<s.length()){
            //if sentence not present then add new TrieNode
            if(node.children[s.charAt(i)] == null) {
                node.children[s.charAt(i)] = new TrieNode();
            }
            node = node.children[s.charAt(i)];
            //node's list doesn't contain sentence then add sentence
            if(!node.pq.contains(s))
                node.pq.add(s);
            //sort the list
            Collections.sort(node.pq,(a,b)-> hotness.get(b)!=hotness.get(a)?hotness.get(b)-hotness.get(a):a.compareTo(b));
            //if more than 3 sentence in the list than remove last sentence
            if(node.pq.size()>3)
                node.pq.remove(3);
            i++;
        }
        node.isEnd = true;
    }
    //TrieNode class
    class TrieNode{
        TrieNode[] children;
        boolean isEnd;
        List<String> pq;
        public TrieNode(){
            children = new TrieNode[128];
            pq = new ArrayList();
        }
    }
}
