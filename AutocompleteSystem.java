// Space Complexity: Trie + Map O(nk) where n is number of sentence of average length k
// Trie with HashMap
public class AutocompleteSystem {

    class TrieNode {
        List<String> result;
        Map<Character, TrieNode> children;
        public TrieNode()
        {
            result = new ArrayList<>();
            children = new HashMap<>();
        }
    }

    // O(n)
    public void insert(String s, int times)
    {
        TrieNode curr = root;
        for(int i = 0 ; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if(!curr.children.containsKey(c))
            {
                curr.children.put(c, new TrieNode());
            }
            
            curr = curr.children.get(c);

            List<String> newResult = curr.result;
            if(!newResult.contains(s))
            {
                newResult.add(s);
            }
            
            // sort desc
            Collections.sort(newResult, (a, b) -> {
                if(map.get(a) == map.get(b))
                    return a.compareTo(b);
                else
                    return map.get(b) - map.get(a);
            });

            if(newResult.size() > 3)
                newResult.remove(newResult.size()-1);

            curr.result = newResult;
        }
    }

    // O(n)
    public List<String> search(String prefix)
    {
        TrieNode curr = root;
        for(int i = 0 ; i < prefix.length(); i++)
        {
            char c = prefix.charAt(i);
            if(!curr.children.containsKey(c))
                return new ArrayList<>();
            curr = curr.children.get(c);
        }

        return curr.result;
    }

    TrieNode root;
    StringBuilder curr;
    Map<String, Integer> map;

    public AutocompleteSystem(String sentences[], String times[])
    {   
        map = new HashMap<>();
        root = new TrieNode();
        curr = new StringBuilder();
        
        // populate trie
        for(int i = 0 ; i < sentences.length; i ++)
        {
            // for easy look up of starts with
            insert(sentences[i],times[i]);
            // to add new strings and get count
            map.put(sentences[i], map.getOrDefault(sentences[i],0)+ times[i]);
        }
    }

    // O(n)
    public List<String> input(char c)
    {
        // end of sentence add it to map
        if(c == "#")
        {
            String input = curr.toString();
            // add new string to trie
            insert(input, 1); // O(n)
            // add to map
            map.put(input, map.getOrDefault(input, 0) + 1);
            // reset String buffer
            curr = new StringBuilder();
            return new ArrayList<>();
        }
        else
        {
            curr.append(c);
        }

        // get all strings starting with curr from Trie 
        List<String> result = search(curr.toString()); // O(n)
        return result;
    }
}

// Trie for easy lookup
public class AutocompleteSystem {

    class TrieNode {
        Map<String, Integer> map;
        Map<Character, TrieNode> children;
        public TrieNode()
        {
            map = new HashMap<>();
            children = new HashMap<>();
        }
    }

    public void insert(String s, int times)
    {
        TrieNode curr = root;
        for(int i = 0 ; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if(!curr.children.containsKey(c))
            {
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
            // get all string and their count that start s
            curr.map.put(s, curr.map.getOrDefault(s, 0) + times);
        }
    }

    public Map<String, Integer> search(String prefix)
    {
        TrieNode curr = root;
        for(int i = 0 ; i < prefix.length(); i++)
        {
            char c = prefix.charAt(i);
            if(!curr.children.containsKey(c))
                return new HashMap<>();
            curr = curr.children.get(c);
        }

        // found a string with prefix, returns all strings that classify
        return curr.map;
    }

    TrieNode root;
    StringBuilder curr;

    public AutocompleteSystem(String sentences[], String times[])
    {   
        curr = new StringBuilder();
        root = new TrieNode();

        // populate trie
        for(int i = 0 ; i < sentences.length; i ++)
        {
            insert(sentences[i],times[i]);
        }
    }

    public List<String> input(char c)
    {
        // end of sentence add it to map
        if(c == "#")
        {
            String input = curr.toString();
            // add new string to trie
            insert(input, 1);
            // reset String buffer
            curr = new StringBuilder();
            return new ArrayList<>();
        }
        else
        {
            curr.append(c);
        }

        // get all strings starting with curr from Trie 
        Map<String, Integer> map = search(curr.toString());
        PriorityQueue pq<String> pq = new PriorityQueue<String>((a,b) -> { 
                if(map.get(a) == map.get(b))
                    return b.compareTo(a);
                else 
                    return map.get(a) - map.get(b);
            });
        
        // search map for chars starting with c
        // O(n)
        for(String s : map.keySet())
        {
            if(s.startsWith(curr.toString()))
            {
                pq.offer(s);
                // since we want top three 
                if(pq.size() > 3)
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

// Brute Force - Search in hashmap
public class AutocompleteSystem {
    Map<String, Integer> map;
    StringBuilder curr;

    public AutocompleteSystem(String sentences[], String times[])
    {
        map = new HashMap<>();
        curr = new StringBuilder();
        for(int i = 0 ; i < sentences.length; i ++)
        {
            map.put(sentences[i], map.getOrDefault(sentences[i],0)+ times[i]);
        }
    }

    public List<String> input(char c)
    {
        // end of sentence add it to map
        if(c == "#")
        {
            String input = curr.toString();
            // add new string to map
            map.put(input, map.getOrDefault(input,0)+ times[i]);
            // reset String buffer
            curr = new StringBuilder();
            return new ArrayList<>();
        }
        else
        {
            curr.append(c);
        }

        PriorityQueue pq<String> pq = new PriorityQueue<String>((a,b) ->{ 
                if(map.get(a) == map.get(b))
                    return a.compareTo(b);
                else
                    return map.get(b) - map.get(a);
            });
        
        // search map for chars starting with c
        // O(n)
        for(String s : map.keySet())
        {
            if(s.startsWith(curr.toString()))
            {
                pq.offer(s);
                // since we want top three 
                if(pq.size() > 3)
                    pq.poll();
            }
        }

        // create PQ in desc order
        List<String> result = new ArrayList<>();
        while(!pq.isEmpty())
        {
            result.add(0, pq.poll());
        }
        return result;
    }
}
