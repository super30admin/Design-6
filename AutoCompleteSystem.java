class AutocompleteSystem {
    TrieNode root;
    HashMap<String, Integer> map;
    StringBuilder sb;

    class TrieNode {
        HashMap<Character, TrieNode> children;
        List<String> list;
 
        TrieNode()
        {
            children = new HashMap();
            list = new ArrayList();
        }
      }
      // TC: O(M) -> M: length of the sentence
        private void insertWordIntoTrie(String sentence, int time)
        {
            TrieNode curr = root;
            for ( int i = 0; i < sentence.length(); i++)
            {
                char c = sentence.charAt(i);
                if (!curr.children.containsKey(c))
                {
                    curr.children.put(c, new TrieNode());   
                }
                curr = curr.children.get(c);
                List<String> currList = curr.list;
                System.out.println("list = "+ currList );
                if (!currList.contains(sentence))
                {
                     currList.add(sentence);
                }
                Collections.sort(currList, (a,b) -> {
                    if (map.get(a) == map.get(b))
                    {
                        return a.compareTo(b);
                    }
                 return map.get(b) - map.get(a);   
              });
                
              if (currList.size() > 3)
              {
                  currList.remove(3);
              }
              curr.list = currList;
            }     
        }
      // TC: O(M) -> M: length of the sentence
        private List<String> searchInputInTrie(String input)
        {
            TrieNode curr = root;
            for ( int i = 0; i < input.length(); i++)
            {
                char c = input.charAt(i);
                if (!curr.children.containsKey(c))
                {        
                   return new ArrayList();
                }
                curr = curr.children.get(c);
            }
            return curr.list;
        }
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        map = new HashMap();
        sb = new StringBuilder();
        for (int i = 0; i < sentences.length; i++)
        {
            map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
            insertWordIntoTrie(sentences[i], times[i]);
        }
    }
   
    public List<String> input (char c) {
        if (c == '#')
        {
            String in = sb.toString();
            insertWordIntoTrie(in, 1);
            map.put(in, map.getOrDefault(in, 0) + 1);
            sb = new StringBuilder();
            return new ArrayList();
        }
        sb.append(c);
       
       // String in = sb.toString();
        List<String> result = searchInputInTrie(sb.toString());
        return result;  
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */



// class AutocompleteSystem {
//     TrieNode root;
//     StringBuilder sb;
//     HashMap<String, Integer> prefixMap;
//     class TrieNode {
//         HashMap<Character, TrieNode> children;
//         HashMap<String, Integer> count;
        
//         TrieNode()
//         {
//             children = new HashMap();
//             count = new HashMap();
//         }
//       }
//       // TC: O(M) -> M: length of the sentence
//         private void insertWordIntoTrie(String sentence, int time)
//         {
//             TrieNode curr = root;
//             for ( int i = 0; i < sentence.length(); i++)
//             {
//                 char c = sentence.charAt(i);
//                 if (!curr.children.containsKey(c))
//                 {
//                     curr.children.put(c, new TrieNode());   
//                 }
//                 curr = curr.children.get(c);
//                 curr.count.put(sentence, curr.count.getOrDefault(sentence, 0) + time);
//             }
//         }
//       // TC: O(M) -> M: length of the sentence
//         private HashMap<String, Integer> searchInputInTrie(String input)
//         {
//             TrieNode curr = root;
//             for ( int i = 0; i < input.length(); i++)
//             {
//                 char c = input.charAt(i);
//                 if (!curr.children.containsKey(c))
//                 {        
//                    return new HashMap();
//                 }
//                 curr = curr.children.get(c);
//             }
//             return curr.count;
//         }
    
//     public AutocompleteSystem(String[] sentences, int[] times) {
//         root = new TrieNode();
//         sb = new StringBuilder();
//         for (int i = 0; i < sentences.length; i++)
//         {
//             insertWordIntoTrie(sentences[i], times[i]);
//         }
//     }
   
//     public List<String> input(char c) {
//         if (c == '#')
//         {
//             String in = sb.toString();
//             insertWordIntoTrie(in, 1);
//             sb = new StringBuilder();
//             return new ArrayList();
//         }
//         sb.append(c);
//         PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
//             if (prefixMap.get(a) == prefixMap.get(b))
//             {
//                 return b.compareTo(a);
//             }
//             return prefixMap.get(a) - prefixMap.get(b);
//         }); 
        
//        // String in = sb.toString();
//         prefixMap = searchInputInTrie(sb.toString());
//         for (String s : prefixMap.keySet())
//         {
//               String in = sb.toString();
//               if (s.startsWith(in))
//               {
//                 pq.add(s);
//                 if (pq.size() > 3)
//                 {
//                     pq.poll();
//                 }
//               }
//         } 
//         List<String> result = new ArrayList();
//         while (!pq.isEmpty())
//             result.add(0, pq.poll());
//         return result;  
//     }
// }

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
