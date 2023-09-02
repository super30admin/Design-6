// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// Your code here along with comments explaining your approach

//We will use the priority queue in the trie itself. We will insert the words in the List for every node, 
// but we will maintain only the top 3 sentences matching the prefix in those lists. 

class AutocompleteSystem {
    private HashMap<String, Integer> map;
    private String search;
    private TrieNode root;

    class TrieNode
    {
        TrieNode[] children;
        List topResults;
        public TrieNode()
        {
            this.children=new TrieNode[256];
            this.topResults=new ArrayList<>();
        }
    }
    private void insert(String word)
    {
        TrieNode curr=root;
        for(int i=0;i<word.length();i++)
        {
            char c=word.charAt(i);
            if(curr.children[c-' ']==null)
            {
                curr.children[c-' ']=new TrieNode();
            }
            curr=curr.children[c-' '];
            List<String> li=curr.topResults;    //li will contain the top results for that node
            if(!li.contains(word))  //if the word is not present then we add it
            {
                li.add(word);
            }

            Collections.sort (li,(String a,String b)->
            {
                if(map.get(a)==map.get(b))
                {
                    return a.compareTo(b);
                }
                return map.get(b)-map.get(a);   //return in descending order
            });

            if(li.size()>3)
            {
                li.remove(li.size()-1); //if the size of the list becomes greater than 3 then we remove the element
            }
        }
    }

    private List <String> searchPrefix(String prefix)
    {
        TrieNode curr=root;
        for(int i=0;i<prefix.length();i++)
        {
            char c=prefix.charAt(i);
            if(curr.children[c-' ']==null)
            {
                return new ArrayList<>();
            }
            curr=curr.children[c-' '];
        }
        return curr.topResults;
    }
    public AutocompleteSystem(String[] sentences, int[] times) 
    {
        this.map=new HashMap<>();
        this.search="";
        this.root=new TrieNode();

        for(int i=0;i<sentences.length;i++)
        {
            String sentence=sentences[i];
            map.put(sentence,map.getOrDefault(search,0)+times[i]);
            insert(sentence);
        }

    }
    
    public List<String> input(char c) 
    {
        if(c=='#')
        {
            //
            map.put(search,map.getOrDefault(search,0)+1);
            insert(search);
            this.search="";
            return new ArrayList<>();
        }
        this.search+=c;
        return searchPrefix(search);
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */




//We will create a Hashmap containing all the sentences with their frequencies.
// Whenever we get an input, we will append it to the search variable. Look for all the sentences 
// in the Hashmap which start with that input. Put this in the priority queue, which is a min heap and 
// will contain top 3 sentences that match with the input. We will return that as output. Whenever # is given as input, 
// which means the input sentence has completed, then we check if the word is already present in the hashmap , 
// if not then we add it and increase its frequency. To optimize the search of each letter we will use Trie, whth children and list. 
// Each child node will contain a list which will have all the words starting with that child node. Then we would return that list in the priority queue and get the top 3 sentences from it.
// TC: O(n)


class AutocompleteSystem {
    private String search;
    private HashMap<String,Integer> map;
    private TrieNode root;

    class TrieNode
    {
        TrieNode[] children;
        List startsWith;    //this is the list which contains all the words that starts with the child node

        public TrieNode()
        {
            this.children=new TrieNode[256];
            this.startsWith=new ArrayList<>();
        }
    }

    private void insert(String word)
    {
        TrieNode curr=root;
        for(int i=0;i<word.length();i++)
        {
            char c=word.charAt(i);
            //if the character is not present in trie
            if(curr.children[c-' ']==null)  //if the character is not present in the trie
            {
                curr.children[c-' ']=new TrieNode();
            }
            curr=curr.children[c-' ']; //move the curr pointer to the next character
            curr.startsWith.add(word);
        }   
    }

    private List<String> searchPrefix(String prefix)
    {
        TrieNode curr=root;
        for(int i=0;i<prefix.length();i++)
        {
            char c=prefix.charAt(i);
            //check in the trie if the prefix is present in it.
            if(curr.children[c-' ']==null)
            {
                return new ArrayList<>(); //If not then return a new ArrayList
            }
            curr=curr.children[c-' '];
        }
        return curr.startsWith; //return the list which contains the words with the prefix
    }

    public AutocompleteSystem(String[] sentences, int[] times) 
    {
        this.search="";
        this.map=new HashMap<>();
        this.root=new TrieNode();

        for(int i=0;i<sentences.length;i++)
        {
            String sentence=sentences[i];
            //if the sentence is not present in the trie, then add interface
            if(!map.containsKey(sentence))
            {
                insert(sentence);
            }
            map.put(sentence,map.getOrDefault(sentence,0)+times[i]);
        }
    }
    public List<String> input(char c) 
    {
        List<String> result=new ArrayList<>();
        //if the sentence given as input by the client has completed then we add it to the hashmap, increase its frequence and reset search to empty
        if(c=='#')
        {
             //if the search string is not present in the trie, then add interface
            if(!map.containsKey(search))
            {
                insert(search);
            }
            //put the complete sentence in the hashmap
            map.put(search,map.getOrDefault(search,0)+1);
            //reset search to empty
            this.search="";
            return result;
        } 
        this.search+=c;

        PriorityQueue <String> pq=new PriorityQueue<>((String a,String b)->{
            if(map.get(a)==map.get(b))  //if the sentences have same frequency
            {
                return b.compareTo(a);  //return the one that come lexicographically first
            }
            return map.get(a)-map.get(b);   //Otherwise return the results in ascending order of their frequencies
        });

        for(String sentence:map.keySet())
        {
            if(sentence.startsWith(search)) //if the sentence starts with the input given by the user
            {
                pq.add(sentence);   //add the sentences to the min heap
                if(pq.size()>3)
                {
                    pq.poll();
                }
            }
        }

        while(!pq.isEmpty())
        {
            result.add(0,pq.poll());
        }
        return result;
    }
}



//We will create a Hashmap containing all the sentences with their frequencies. 
// Whenever we get an input, we will append it to the search variable. Look for all the sentences in the Hashmap which start with that input. Put this in the priority queue, 
// which is a min heap and will contain top 3 sentences that match with the input. We will return that as output. Whenever # is given as input, which means the input sentence has completed, 
// then we check if the word is already present in the hashmap , if not then we add it and increase its frequency
// TC: O(Nlogk) SC: O(N)


class AutocompleteSystem {
    String search;
    HashMap<String,Integer> map;
    public AutocompleteSystem(String[] sentences, int[] times) 
    {
        this.search="";
        this.map=new HashMap<>();
        // add the sentences to the hashmap
        for (int i=0;i<sentences.length;i++)
        {
            String sentence=sentences[i];
            map.put(sentence,map.getOrDefault(sentence,0)+times[i]);
        }
    }
    public List<String> input(char c) 
    {
        List<String> result=new ArrayList<>();
        //if the sentence given as input by the client has completed then we add it to the hashmap, increase its frequence and reset search to empty
        if(c=='#')
        {
            //put the complete sentence in the hashmap
            map.put(search,map.getOrDefault(search,0)+1);
            //reset search to empty
            this.search="";
            return result;
        } 
        this.search+=c;

        PriorityQueue <String> pq=new PriorityQueue<>((String a,String b)->{
            if(map.get(a)==map.get(b))  //if the sentences have same frequency
            {
                return b.compareTo(a);  //return the one that come lexicographically first
            }
            return map.get(a)-map.get(b);   //Otherwise return the results in ascending order of their frequencies
        });

        for(String sentence:map.keySet())
        {
            if(sentence.startsWith(search)) //if the sentence starts with the input given by the user
            {
                pq.add(sentence);   //add the sentences to the min heap
                if(pq.size()>3)
                {
                    pq.poll();
                }
            }
        }

        while(!pq.isEmpty())
        {
            result.add(0,pq.poll());
        }
        return result;
    }
}