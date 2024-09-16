//Time complexity - O(nlogk)
//Space complexity - O(n) for hashmap

class AutocompleteSystem {
    HashMap<String, Integer> hm = new HashMap<>();
    TrieNode root;
    TrieNode curr;
    StringBuilder sb;
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        curr = root;
        sb = new StringBuilder();
        for(int i=0;i<sentences.length;i++){
            addToTrie(root, sentences[i], times[i]);
        }
    }
    public void addToTrie(TrieNode root, String s, int t){
        int i = 0;
        hm.put(s, hm.getOrDefault(s, 0)+t);
        while(i<s.length()){
            if(root.children[s.charAt(i)] == null){
                root.children[s.charAt(i)] = new TrieNode();
            }
            root = root.children[s.charAt(i)];
            if(!root.pq.contains(s)){
                root.pq.add(s);
            }
            Collections.sort(root.pq, (p,q)->hm.get(p)!=hm.get(q) ? hm.get(q)-hm.get(p) : p.compareTo(q) );
            if(root.pq.size()>3){
                root.pq.remove(3);
            }
            i++;
        }
        root.isEnd = true;
    }
    public List<String> input(char c) {
        if(c=='#'){
            curr = root;
            addToTrie(curr, sb.toString(), 1);
            sb.setLength(0);
            return new ArrayList<>();
        }
        else{
            if(curr.children[c] == null){
                curr.children[c] = new TrieNode();
            }
            sb.append(c);
            curr = curr.children[c];
            return curr.pq;
        }
    }

    class TrieNode{
        TrieNode[] children;
        boolean isEnd;
        List<String> pq;

        TrieNode(){
            children = new TrieNode[128];
            pq = new ArrayList<>();
        }

    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */


