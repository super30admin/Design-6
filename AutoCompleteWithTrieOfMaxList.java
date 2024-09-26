package Design6;
/*-------------------------------------------------------------------------------------------------------
Time complexity :O(1) 
space complexity:O(1) 
Approach:
Did this code run successfully in leetcode : yes
problems faces : no*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class AutoCompleteWithTrieOfMaxList {

        HashMap<String,Integer> map = new HashMap<>(); //Global map to maintain inventory
        class TrieNode{
            
            List<String> listOfTopDegrees;
            HashMap<Character,TrieNode> children ;
            //constructor
            public TrieNode()
            {
                listOfTopDegrees = new ArrayList<String>();
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
               List<String> temp = curr.listOfTopDegrees;
               if(!temp.contains(sentence))
               {
                temp.add(sentence);
                }
                Collections.sort(temp,(a,b)->{
                    if(map.get(a) == map.get(b)) 
                    {
                        return a.compareTo(b);
                    }
                    return map.get(b)-map.get(a);
                });
                if(temp.size()>3)
                {
                    temp.remove(3);
                }
                curr.listOfTopDegrees = temp;
            }
            
        }
        
        private List<String> search(String inputSentence)
        {
            TrieNode curr = root;
            for(int i=0;i<inputSentence.length();i++)
            {
                char c = inputSentence.charAt(i);
                if(!curr.children.containsKey(c))
                {
                    return new ArrayList<>();
                }
                curr = curr.children.get(c);
            }
            return curr.listOfTopDegrees;
        }
    
    
        TrieNode root ;
        StringBuilder sb;
    
    public AutoCompleteWithTrieOfMaxList(String[] sentences, int[] times) 
    {
        map = new HashMap<>();
        root = new TrieNode();
        sb = new StringBuilder();
        for(int i=0;i<sentences.length;i++)
        {
            String sentence = sentences[i];
            map.put(sentence, map.getOrDefault(sentence, 0)+times[i]);
            insert(sentence, times[i]);
           
        }
        
    }
    
    public List<String> input(char c) {
        
        if(c=='#')
        {
            String input = sb.toString();
            map.put(input, map.getOrDefault(input, 0)+1);
           insert(input, 1);
            sb = new StringBuilder();
            return new ArrayList<>();
        }
        
        sb.append(c);
        String input = sb.toString();
        
        return search(input);
        
    }


}
