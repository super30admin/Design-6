class AutocompleteSystem {
    class TrieNode{
        TrieNode[] children;
        HashSet<String> matches;
        public TrieNode()
        {
            children = new TrieNode[256];
            matches = new HashSet<>();
        }
    }
    
    HashMap<String,Integer> frequencies;
    TrieNode root;
    TrieNode curr;
    StringBuilder sb;
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        curr =root;
        sb = new StringBuilder();
        frequencies = new HashMap<>();
        for (int i =0;i<sentences.length;i++)
        {
            String sentence =sentences[i];
            frequencies.put(sentence, times[i]);
            insertIntoTrie(sentence);
        }
    }
    private void insertIntoTrie(String sentence)
    {
        TrieNode currNode= root;
        for(char c : sentence.toCharArray())
        {
            if(currNode.children[c-' ']==null)
                currNode.children[c-' ']= new TrieNode();
            currNode.matches.add(sentence);
           // System.out.println("Added "+sentence+" to the list of "+c);
            currNode = currNode.children[c-' '];
        }
        currNode.matches.add(sentence);
        
        
    }
    
    public List<String> input(char c) {
        if(c=='#')
        {   String sentence =sb.toString();
            insertIntoTrie(sentence);
            frequencies.put(sentence, frequencies.getOrDefault(sentence,0)+1);
            sb = new StringBuilder();
            curr= root;
         return new ArrayList<String>();
            
        }
        sb.append(c);
        List<String>result = new ArrayList<>();
        if(curr!=null && curr.children[c-' ']!=null)
        {   HashSet<String> matches = curr.children[c-' '].matches;
            PriorityQueue<String>pq = new PriorityQueue<>((a,b)->{
               int ca = frequencies.get(a);
               int cb = frequencies.get(b);
               return ca==cb?b.compareTo(a):ca-cb ;
            });
           for(String s :matches)
           {
               pq.add(s);
               if(pq.size()>3)
                   pq.poll();
           }
           while(!pq.isEmpty())
           {
               result.add(0,pq.poll());
           }
            curr =curr.children[c-' '];
            
        }
        
        else
        {curr=null;  }
        return result;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */