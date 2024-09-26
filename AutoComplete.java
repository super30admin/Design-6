//Time Complexity: o(n log k)
//Space Complexity: o(n)
//Maintain a trie  of sentences and at every node maintain a list of active sentences accordingly with hashmap. 
//Add to the list and sort it based on the hotness by getting from hashmap and remove if > 3. Also traverse based on the input.
// traverse through the list and return the list as its based maintained to 3 based on activeness return that directly.
// At the end char # add the string to the list and hashmap by incrementing the hotness by maintaining it in string builder.
//In this way we keep returning hot lists at every input and sorting the lists.
class AutocompleteSystem {
    TrieNode root;
    StringBuilder sb;
    TrieNode cur;
    Map<String, Integer> map = new HashMap<>();
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        cur = root;
        sb = new StringBuilder();
        for(int i = 0; i < sentences.length; i++)
        {
            map.put(sentences[i], times[i]);
            insert(sentences[i]);
        }
    }
    
    class TrieNode{
    TrieNode[] child;
    List<String> list;
        public TrieNode()
        {
            child = new TrieNode[128];
            list = new ArrayList();
        }
    }
    
    public void insert(String word)
    {
            TrieNode curr = root;
            for(int j = 0 ; j < word.length(); j++)
            {
                char c = word.charAt(j);
                if(curr.child[c - ' '] == null)
                {
                    curr.child[c - ' '] =  new TrieNode();
                }
                curr = curr.child[c - ' '];
                if(!curr.list.contains(word)){
                    curr.list.add(word);
                }
                Collections.sort(curr.list, (a, b)->map.get(b) != map.get(a)? map.get(b) - map.get(a):a.compareTo(b));
                if(curr.list.size() > 3) curr.list.remove(3);
            }
    }
    
    public List<String> input(char c) {
        if(c == '#')
        {
            map.put(sb.toString(), map.getOrDefault(sb.toString(), 0)+1);
            insert(sb.toString());
            sb.setLength(0);
            cur = root;
            return new ArrayList<>();
        }
        else
        {
            if(cur.child[c - ' '] == null)
            {
                cur.child[c- ' '] = new TrieNode();
            }
            sb.append(c);
            cur = cur.child[c - ' '];
            List val = cur.list;
            return cur.list;    
        }
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */

  