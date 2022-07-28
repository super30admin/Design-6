//****125.379. DESIGN A PHONE DIRECTORY****
//Time complexity:o(n);
//Space complexity:o(n);
//Leetcode runnable: Y;
//Any doubt:N;

class PhoneDirectory {
    HashSet<Integer> set;
    Queue<Integer> q;

    public PhoneDirectory(int maxNumbers) {
        this.set=new HashSet<>();
        this.q=new LinkedList<>();
        
        for(int i=0;i<maxNumbers;i++)
        {
            q.add(i);
            set.add(i);
        }
        
    }
    
    public int get() {
        if(!q.isEmpty())
        {
            int re=q.poll();
            set.remove(re);
            return re;
        }
        return -1;
        
    }
    
    public boolean check(int number) {
        return set.contains(number);
        
    }
    
    public void release(int number) {
        if(set.contains(number))
        {
           set.add(number); 
        }
        else
        {
            q.add(number);
            set.add(number);
        }
        
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */



//126. 642. Design Search Autocomplete System- HASHMAP SOLUTION*****
//Time complexity:o(n);
//Space complexity:O()
//Leetcode runnable:Y;
//Any doubts:N;
class AutocompleteSystem {
    //Map for storing all the words given in the sentence array
    HashMap<String, Integer> map;
    StringBuilder sb;

    public AutocompleteSystem(String[] sentences, int[] times) {
        this.map=new HashMap<>();
        this.sb=new StringBuilder();
        //Adding all the sentences in the map so that whenever a prefix is searched, we can retrive it from map
        for(int i=0;i<sentences.length;i++)
        {
            String sen=sentences[i];
            map.put(sen, map.getOrDefault(sen, 0)+times[i]);
        }
        
    }
    
    public List<String> input(char c) {
        //If it is the hash then basically add the old string to the map as well reset the sb to new stringBuilder
        if(c=='#')
        {
            //Get the sb into string
            String re=sb.toString();
            //Add it to the map
            map.put(re, map.getOrDefault(re, 0)+1);
            //Reset the sb
            sb=new StringBuilder();
            return new ArrayList<>();
        }
        //If c is not equal to hash then
        
        //Get the given c into sb
        sb.append(c);
        
        //Declaring minheap to get the first 3 results
        PriorityQueue<String> pq=new PriorityQueue<>((a,b)->{
            int ca=map.get(a);
            int cb=map.get(b);
            
            if(ca==cb)
            {
                return b.compareTo(a);
            }
            return ca-cb;
        });
            
            //Searching in the map whatever starts with given char and maintaining top 3 result using minheap
            String re=sb.toString();
            for(String key: map.keySet())
            {
                if(key.startsWith(re))
                {
                    pq.add(key);
                    if(pq.size()>3)
                    {
                        pq.poll();
                    }
                }
            }
        
        
        //Making arraylist for returning the result
        ArrayList<String> result=new ArrayList<>();
        while(!pq.isEmpty())
        {
            
        result.add(0, pq.poll());
        }
            
        
        
        return result;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */



//126. 642. Design Search Autocomplete System- TRIENODE SOLUTION*****
//Time complexity:o(k);
//Space complexity:O(1)
//Leetcode runnable:Y;
//Any doubts:N;

class AutocompleteSystem {
    //TrieNode declaration
    class TrieNode{
        TrieNode[] children;
        List<String> li;
        
        public TrieNode(){
            this.children=new TrieNode[256];
            this.li=new ArrayList<>();
        }
    }
    
    public void insert(String word)
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
            curr.li.add(word);
        }
    }
    
    public List<String> search(String word)
    {
        TrieNode curr=root;
        for(int i=0;i<word.length();i++)
        {
            char c=word.charAt(i);
            
            if(curr.children[c-' ']==null)
            {
                return new ArrayList<>(); 
            }
            curr=curr.children[c-' '];
        }
        return curr.li;
    }
    
    
    
    
    //Map for storing all the words given in the sentence array
    HashMap<String, Integer> map;
    StringBuilder sb;
    TrieNode root;

    public AutocompleteSystem(String[] sentences, int[] times) {
        this.map=new HashMap<>();
        this.sb=new StringBuilder();
        this.root=new TrieNode();
        //Adding all the sentences in the map so that whenever a prefix is searched, we can retrive it from map
        for(int i=0;i<sentences.length;i++)
        {
            String sen=sentences[i];
            if(!map.containsKey(sen)) insert(sen);
            map.put(sen, map.getOrDefault(sen, 0)+times[i]);
            //Adding inside the trie
            
        }
        
    }
    
    public List<String> input(char c) {
        //If it is the hash then basically add the old string to the map as well reset the sb to new stringBuilder
        if(c=='#')
        {
            //Get the sb into string
            String re=sb.toString();
            if(!map.containsKey(re)) insert(re);
            //Add it to the map
            map.put(re, map.getOrDefault(re, 0)+1);
            //Insert to trie
            
            //Reset the sb
            sb=new StringBuilder();
            return new ArrayList<>();
        }
        //If c is not equal to hash then
        
        //Get the given c into sb
        sb.append(c);
        
        //Declaring minheap to get the first 3 results
        PriorityQueue<String> pq=new PriorityQueue<>((a,b)->{
            int ca=map.get(a);
            int cb=map.get(b);
            
            if(ca==cb)
            {
                return b.compareTo(a);
            }
            return ca-cb;
        });
            
            //Searching in the map whatever starts with given char and maintaining top 3 result using minheap
            String re=sb.toString();
            List<String> li=search(re);
            for(String key: li)
            {
                
                
                    pq.add(key);
                    if(pq.size()>3)
                    {
                        pq.poll();
                    }
                
            }
        
        
        //Making arraylist for returning the result
        ArrayList<String> result=new ArrayList<>();
        while(!pq.isEmpty())
        {  
            result.add(0, pq.poll());
        }
            
        
        
        return result;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
