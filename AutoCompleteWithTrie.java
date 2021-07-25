package Design6;
/*-------------------------------------------------------------------------------------------------------
Time complexity :O(N) where N is the number os sentences within a trie
space complexity:O(N) 
Approach:
Did this code run successfully in leetcode : yes
problems faces : no*/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class AutoCompleteWithTrie {

        class TrieNode{
            
            HashMap<String,Integer> map ; //containing all the sentences starting with input whose last character is this TrieNode
            HashMap<Character,TrieNode> children ;
            //constructor
            public TrieNode()
            {
                map = new HashMap<>();
                children = new HashMap<>();
            }
        }
        
        private void insert(String sentence,int times) {
            
            TrieNode curr = root;
            for(int i=0;i<sentence.length();i++)
            {
                char c = sentence.charAt(i);
                if(!curr.children.containsKey(c))
                {
                    curr.children.put(c, new TrieNode());
                }
                curr = curr.children.get(c);
                curr.map.put(sentence,curr.map.getOrDefault(sentence, 0)+times);
            }
            
        }
        
        private HashMap<String,Integer> search(String inputSentence)
        {
            TrieNode curr = root;
            for(int i=0;i<inputSentence.length();i++)
            {
                char c = inputSentence.charAt(i);
                if(!curr.children.containsKey(c))
                {
                    return new HashMap<>();
                }
                curr = curr.children.get(c);
            }
            return curr.map;
        }
    
    
        TrieNode root ;
        StringBuilder sb;
    
    public AutoCompleteWithTrie(String[] sentences, int[] times) 
    {
        root = new TrieNode();
        sb = new StringBuilder();
        for(int i=0;i<sentences.length;i++)
        {
            String sentence = sentences[i];
            insert(sentence, times[i]);
        }
        
    }
    
    public List<String> input(char c) {
        
        if(c=='#')
        {
            String input = sb.toString();
           insert(input, 1);
            sb = new StringBuilder();
            return new ArrayList<>();
        }
        
        sb.append(c);
        String input = sb.toString();
        HashMap<String, Integer> map = search(input); //O(n) it returns smaller map, containing the sentence for the given input
        PriorityQueue<String> pq = new PriorityQueue<>((a,b) -> {
           if(map.get(a) == map.get(b))
           {
               return b.compareTo(a);
           }
            return map.get(a)-map.get(b);
        });
        
        for(String s : map.keySet())
        {
            if(s.startsWith(input))
            {
                pq.add(s);
                if(pq.size()>3)
                {
                    pq.poll();
                }
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
