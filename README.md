# Design-6

## Problem1 Design Phone Directory (https://leetcode.com/problems/design-phone-directory/)

class PhoneDirectory {
    //Time Complexity for all three functions is O(1) - get, check, release
    //Space Complexity : O(N) because the set and the queue can store atmost n elements. 
    Queue<Integer> queue; 
    Set<Integer> set;
    public PhoneDirectory(int maxNumbers) {
        queue = new LinkedList<>();
        set = new HashSet<>();
        for(int i=0; i<maxNumbers; i++)
        {
            set.add(i);
            queue.add(i);
        }
    }
    
    public int get() {
        if(queue.isEmpty()) return -1;
        int re = queue.poll();
        set.remove(re);
        return re;
    }
    
    public boolean check(int number) {
        return set.contains(number);
    }
    
    public void release(int number) {
        if(set.contains(number)) return;
        queue.add(number);
        set.add(number);
    }
}

## Problem2 Design Autocomplete System (https://leetcode.com/problems/design-search-autocomplete-system/)
class AutocompleteSystem {
    //Time Complexity: O(m) 
    input() function- O(m) where m= no of string for each search word result list
    //Space Complexity: O(n) for hashmap -> n is the number of sentences 
    // space complexity of the trie is constant because at most 256 levels - not dependent on input 
    HashMap<String, Integer> map;
    class TrieNode
    {
        boolean isEnd;
        List<String> li;
        TrieNode children[];
        public TrieNode()
        {
            children = new TrieNode[256];
            li = new ArrayList<>();
        }
    }
    private void insert(String sentence)
    {
        TrieNode curr = root;
        for(int i=0; i<sentence.length(); i++)
        {
            char c = sentence.charAt(i);
            if(curr.children[c-' '] ==  null)
            {
                curr.children[c-' '] = new TrieNode();
            }
            curr = curr.children[c - ' '];
            curr.li.add(sentence);
            
        }
        curr.isEnd = true;
    }
    private List<String> searchPrefix(String searchKey)
    {
        TrieNode curr = root;
        for(int i=0; i< searchKey.length() ; i++)
        {
            char c = searchKey.charAt(i);
            if(curr.children[c-' '] == null)
            {
                return new ArrayList<>();
            }
            curr = curr.children[c-' '];
        }
        return curr.li;
        
    }
    TrieNode root; 
    StringBuilder sb;
    PriorityQueue<String> pq;
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        map = new HashMap<>();
        sb = new StringBuilder();
        pq = new PriorityQueue<>((a,b) -> {
            if(map.get(a) == map.get(b))
            {
                return b.compareTo(a);
            }
            
            return map.get(a) - map.get(b);
        });
        for(int i=0; i< sentences.length; i++)
        {
            //fill the hashmap with sentences and the corresponding frequencies 
            map.put(sentences[i], map.getOrDefault(sentences[i], 0)+ times[i]);
            //insert each sentence into trie
            insert(sentences[i]);
        }  
    }
    
    public List<String> input(char c) {
        if(c == '#')
        {
            String search = sb.toString();
            if(!map.containsKey(search))
            {
                map.put(search, 1);
                insert(search);
            }
            else
            {
                map.put(search, map.get(search) + 1);
            }
            sb = new StringBuilder();
            return new ArrayList<>();
        }
       
           sb.append(c);
           //search term 
            String searchKey = sb.toString();
           // Collect all the strings that start with the search term
            List<String> listOfStrings = searchPrefix(searchKey);
            for(String sentence : listOfStrings)
            {
                    pq.add(sentence);
                    if( pq.size() > 3)
                    {
                        pq.poll();
                    }
                
            }
            
            List<String> result = new ArrayList<>();
            while(!pq.isEmpty())
            {
                result.add(0, pq.poll());
            }
            
            return result;
    }
}
--- optimize ->

class AutocompleteSystem {
    //Time Complexity: input() function- O(l) where l= length of search key 
    //Space Complexity: O(n) for hashmap -> n is the number of sentences 
    // space complexity of the trie is constant because at most 256 levels - not dependent on input 
    HashMap<String, Integer> map;
    class TrieNode
    {
        boolean isEnd;
        List<String> li;
        TrieNode children[];
        public TrieNode()
        {
            children = new TrieNode[256];
            li = new ArrayList<>();
        }
    }
    private void insert(String sentence)
    {
        TrieNode curr = root;
        for(int i=0; i<sentence.length(); i++)
        {
            char c = sentence.charAt(i);
            if(curr.children[c-' '] ==  null)
            {
                curr.children[c-' '] = new TrieNode();
            }
            curr = curr.children[c - ' '];
            if(!curr.li.contains(sentence)) 
                curr.li.add(sentence);
            curr.li.sort((a,b) -> {
            if(map.get(a) == map.get(b))
            {
                return a.compareTo(b);
            }
            
            return map.get(b) - map.get(a);
            });
            
            
            if(curr.li.size() >3)             
            {
                  curr.li.remove(curr.li.size()-1);
            }
              
            
        }
        curr.isEnd = true;
    }
    private List<String> searchPrefix(String searchKey)
    {
        TrieNode curr = root;
        for(int i=0; i< searchKey.length() ; i++)
        {
            char c = searchKey.charAt(i);
            if(curr.children[c-' '] == null)
            {
                return new ArrayList<>();
            }
            curr = curr.children[c-' '];
        }
        return curr.li;
        
    }
    TrieNode root; 
    StringBuilder sb;
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        map = new HashMap<>();
        sb = new StringBuilder();
        for(int i=0; i< sentences.length; i++)
        {
            //fill the hashmap with sentences and the corresponding frequencies 
            map.put(sentences[i], map.getOrDefault(sentences[i], 0)+ times[i]);
            //insert each sentence into trie
            insert(sentences[i]);
        }  
    }
    
    public List<String> input(char c) {
        if(c == '#')
        {
             String search = sb.toString();
             map.put(search, map.getOrDefault(search, 0) + 1);
             insert(search);
             sb = new StringBuilder();
             return new ArrayList<>();
        }
       
           sb.append(c);
           //search term 
            String searchKey = sb.toString();
           // Collect all the strings that start with the search term
            List<String> listOfStrings = searchPrefix(searchKey);
            return listOfStrings;
    }
}

---Brute force without trie-----
//O(m+n) - time, O(n) space for hashmap
class AutocompleteSystem {
    HashMap<String, Integer> map;
    StringBuilder sb;
    PriorityQueue<String> pq;
    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        this.sb = new StringBuilder();
        pq = new PriorityQueue<>((a,b) -> {
            if(map.get(a) == map.get(b))
            {
                return b.compareTo(a);
            } 
            return map.get(a) - map.get(b);
        });
        int i=0;
        for(String sentence: sentences)
        {
            map.put(sentence, map.getOrDefault(sentence, 0)+times[i]);
            //System.out.println(sentence + " " + times[i]);
            i++;
        }
    }
    
    public List<String> input(char c) {
        if(c == '#')
        {
            String search = sb.toString();
            map.put(search, map.getOrDefault(search, 0) + 1);
            sb = new StringBuilder();
            return new ArrayList<>();
        }
       
           sb.append(c);
           //search term 
            String searchKey = sb.toString();
           // Collect all the strings that start with the search term
            for(String sentence : map.keySet())
            {
                if(sentence.startsWith(searchKey))
                {
                    pq.add(sentence);
                    if( pq.size() > 3)
                    {
                        String s = pq.poll();
                        System.out.println(s);
                    }
                }
            }
            
            List<String> result = new ArrayList<>();
            while(!pq.isEmpty())
            {
                result.add(0, pq.poll());
            }
            
            return result;
        
    }
}

