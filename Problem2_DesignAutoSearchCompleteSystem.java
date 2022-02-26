// Time Complexity :o(N)
// Space Complexity : o(N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

//BruthForce using PriorityQueue
class AutocompleteSystem {

    Map<String,Integer> countMap;
    StringBuilder input;
    public AutocompleteSystem(String[] sentences, int[] times) {
        
        countMap=new HashMap<>();
        input=new StringBuilder();
        for(int i=0;i<sentences.length;i++)
        {
            countMap.put(sentences[i],countMap.getOrDefault(sentences[i],0)+times[i]);
        }
    }
    
    public List<String> input(char c) {
        
        if(c=='#')
        {
            String inputStr=input.toString();
            
           countMap.put(inputStr,countMap.getOrDefault(inputStr,0)+1);
            
            input=new StringBuilder();
          
            return new ArrayList<>();
        }
        
        input.append(c);
        
        PriorityQueue<String> pq=new PriorityQueue<>((a,b)->{
           
            if(countMap.get(a)==countMap.get(b))
            {
                return b.compareTo(a);
            }
            else
            {
              return  countMap.get(a)-countMap.get(b);
            }    
        });
        
        for(String key: countMap.keySet())
        {
            if(key.startsWith(input.toString()))
            {
                pq.add(key);
                if(pq.size()>3)
                    pq.poll();
            }
        }
        
        List<String> result=new ArrayList<>();
        
        while(!pq.isEmpty())
        {
            result.add(0,pq.poll());
        }
        
        return result;
    }
}

//Trie Using PriorityQueue

//Time Complexity :o(1)
//Space Complexity : o(N)
//Did this code successfully run on Leetcode : Yes
//Any problem you faced while coding this : No

class AutocompleteSystem {

    Map<String,Integer> countMap;
    
    class TrieNode {
        List<String> pqueue;
        HashMap<Character,TrieNode> children;
        
        public TrieNode()
        {
            pqueue=new ArrayList<>();
            children=new HashMap<>();
        }
    }
    
    TrieNode root;
    
     public void insert(String sentence, int times) 
     {
         TrieNode current=root;
         
         for(int i=0;i<sentence.length();i++)
         {
             char c=sentence.charAt(i);
             
             if(!current.children.containsKey(c))
             {
                 current.children.put(c,new TrieNode());
             }
            current= current.children.get(c);
             
             if(!current.pqueue.contains(sentence))
                current.pqueue.add(sentence);
             
             Collections.sort(current.pqueue,(a,b)-> countMap.get(a)==countMap.get(b) ? a.compareTo(b): countMap.get(b)- countMap.get(a));
             
             if(current.pqueue.size() >3)
             {
                 current.pqueue.remove(current.pqueue.size()-1);
             }
         }
     }
    
    List<String> search(String prefix)
    {
         TrieNode current = root;
        
        for(int i=0;i<prefix.length();i++)
        {
            char c=prefix.charAt(i);
            
            if(!current.children.containsKey(c))
            {
                return new ArrayList<>();
            }
            
            current=current.children.get(c);
        }
        return current.pqueue;
    }
    
     StringBuilder input;
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        
        root = new TrieNode();
        countMap = new HashMap<>();
        input = new StringBuilder();
        
        
        for(int i=0;i<sentences.length;i++)
        {
            countMap.put(sentences[i],countMap.getOrDefault(sentences[i],0) + times[i]);
            
            insert(sentences[i],times[i]);
        }
    }
    
    public List<String> input(char c) {
        
        if(c=='#')
        {
            String inputStr=input.toString();
            
           countMap.put(inputStr,countMap.getOrDefault(inputStr,0)+1);
            
            insert(inputStr,1);
            
            input=new StringBuilder();
          
            return new ArrayList<>();
        }
        
        input.append(c);
        
        return search(input.toString());
    }
}


/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */