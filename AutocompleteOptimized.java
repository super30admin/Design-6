class AutocompleteSystem {
    //tc-o(1),sc-o(1)
    class TrieNode{
        TrieNode[] children;
        List<String> li;
        public TrieNode(){
            this.children = new TrieNode[256];//as space need to be considered
            this.li = new ArrayList<>();
        }
    }
    
    private void insert(String sentence)
    {
      TrieNode curr = root;
      for(int i=0;i<sentence.length();i++)
      {
          char c = sentence.charAt(i);
          if(curr.children[c-' '] == null)
          {
              curr.children[c-' '] = new TrieNode();
          }
          curr = curr.children[c-' '];
          List<String> lis = curr.li;
          if(!lis.contains(sentence))
          {
              lis.add(sentence);
          }
          Collections.sort(lis, (a,b) ->{
              if(map.get(a) == map.get(b))
              {
                  return a.compareTo(b);
              }
              
                  return map.get(b) - map.get(a);
              
          }

          );
          if(lis.size() > 3)
          {
              lis.remove(lis.size()-1);
          }
      }
    }
    
    private List<String> search(String prefix)
    {
        TrieNode curr = root;
       for(int i=0;i<prefix.length();i++)
       {
           char c = prefix.charAt(i);
           if(curr.children[c-' '] == null)
           {
               return new ArrayList<>();
           }
           curr = curr.children[c-' '];
       }
       return curr.li;
    }

    HashMap<String, Integer> map;
    StringBuilder inputs;
    TrieNode root;
    public AutocompleteSystem(String[] sentences, int[] times) {
        this.map = new HashMap<>();
        this.inputs = new StringBuilder();
        this.root = new TrieNode();

        for(int i=0;i<sentences.length;i++)
        {
            String sentence = sentences[i];
    
            map.put(sentence, map.getOrDefault(sentence,0)+times[i]);
            insert(sentence);
        }

    }
    
    public List<String> input(char c) {
        if(c == '#')
        {
            String search = inputs.toString();
           
            map.put(search, map.getOrDefault(search,0)+1);
            insert(search);
            inputs = new StringBuilder();
            return new ArrayList<>();

        }
        inputs.append(c);   
         String prefix = inputs.toString();
  
        return search(prefix);
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */