class AutocompleteSystem {
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
          curr.li.add(sentence);
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
            if(!map.containsKey(sentence)) insert(sentence);
            map.put(sentence, map.getOrDefault(sentence,0)+times[i]);
        }

    }
    
    public List<String> input(char c) {
        if(c == '#')
        {
            String search = inputs.toString();
            if(!map.containsKey(search)) insert(search);
            map.put(search, map.getOrDefault(search,0)+1);
            inputs = new StringBuilder();
            return new ArrayList<>();

        }
        inputs.append(c);
        PriorityQueue<String> pq = new PriorityQueue<>((a,b)->{
            if(map.get(a) == map.get(b))//when freq are equal smaller letter will have precendence
            {
                return b.compareTo(a);// 
                /*String myStr1 = "ir";
String myStr2 = "is";
System.out.println(myStr2.compareTo(myStr1)); s2.comapres1 retruns 1 , s1comapres2 retruns -1
  String myStr1 = "ii";//1st
String myStr2 = "im";//4nd
System.out.println(myStr1.compareTo(myStr2));//i-m = -4*/
            }
            else
            {
                return map.get(a) - map.get(b);//string with lesser freq should be on the top
            }
         });
         String prefix = inputs.toString();
         List<String> startsWith = search(prefix);
         
         for(String str : startsWith)
         {
             if(str.startsWith(prefix))
             {
                 pq.add(str);
             }
             if(pq.size() > 3)
             {
                 pq.poll();
             }
         }
         

        List<String> result = new ArrayList<>();
        while(!pq.isEmpty())
        {
            result.add(0,pq.poll());
        }
        return result;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */