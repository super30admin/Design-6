//Problem 1 Design Phone Directory
// Time Complexity :O(1)
// Space Complexity :O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach
//keep used slots in hashset and use a queue to get next available slot.
class PhoneDirectory {
    HashSet<Integer> uset;
    Queue<Integer> q;
    public PhoneDirectory(int maxNumbers) {
        this.uset=new HashSet<>();
        this.q=new LinkedList<>();
        for(int i=0;i<maxNumbers;i++){
            q.add(i);
        }
    }
    
    public int get() { //O(1)
        if(q.isEmpty())
            return -1;
        
        int el=q.poll();
        uset.add(el);
        return el;
    }
    
    public boolean check(int number) { //O(1)
        return !uset.contains(number);
    }
    
    public void release(int number) { //O(1)
        if(uset.contains(number)){
            uset.remove(number);
            q.add(number);
        }
    }
}


//Problem 2 Design Search Autocomplete System
// Time Complexity :O(NL)
// Space Complexity :O(NL)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach
// Use a Prefix tree for better search prefix and keep a list with custom comparator at each TrieNode. When u enter a node in the trie, change freq and store it in the list each time.
// at the end. just return list at the prefix node.
class AutocompleteSystem {
    private TrieNode root;
    class TrieNode{
        TrieNode[] children;
        List<String> startsWith;

        public TrieNode(){
            this.children=new TrieNode[256];
            this.startsWith=new ArrayList<>();
        }
    }

    private void insert(String word){
        TrieNode cur=root;
        for(int i=0;i<word.length();i++){
            char c=word.charAt(i);
            if(cur.children[c-' ']==null){
                cur.children[c-' ']=new TrieNode();
            }
            cur=cur.children[c-' '];
            // cur.startsWith.add(word);
            List<String> li=cur.startsWith;
            if(!li.contains(word))
                li.add(word);
            
            Collections.sort(li, (a,b)->{ //do custom sort
                if(map.get(a)==map.get(b))
                    return a.compareTo(b);

                return map.get(b)-map.get(a); 
            });
            if(li.size()>3){
                li.remove(li.size()-1);
            }
        }
    }

    private List<String> search(String prefix){
        TrieNode cur=root;
        for(int i=0;i<prefix.length();i++){
            char c=prefix.charAt(i);
            if(cur.children[c-' ']==null){
                return new ArrayList<>();
            }
            cur=cur.children[c-' '];
        }
        return cur.startsWith;
    }

    private HashMap<String, Integer>map;
    private String sb;
    public AutocompleteSystem(String[] sentences, int[] times) {
        this.map= new HashMap<>();
        this.sb="";
        this.root=new TrieNode();
        for(int i=0;i<sentences.length;i++){
            // if(!map.containsKey(sentences[i]))
            //     insert(sentences[i]);

            map.put(sentences[i], map.getOrDefault(sentences[i],0)+times[i]);
            insert(sentences[i]);
        }
    }
    public List<String> input(char c) {
        List<String> res=new ArrayList<>();
        if(c=='#'){
            // if(!map.containsKey(sb))
            //     insert(sb);
            map.put(sb, map.getOrDefault(sb,0)+1);
            insert(sb);
            this.sb="";
            return new ArrayList<>();
        }
        this.sb+=c;
        return search(sb);
        // PriorityQueue<String> q=new PriorityQueue<>((String a, String b)->{
        //     if(map.get(a)==map.get(b)){
        //         return b.compareTo(a);
        //     }
        //     return map.get(a)-map.get(b);
        // });

        // List<String> stwith=search(sb);
        // for(String str: stwith){
        //     q.add(str);
        //     if(q.size()>3)
        //         q.poll();
        // }

        // for(String key: map.keySet()){
        //     if(key.startsWith(sb)){
        //         q.add(key);
        //         if(q.size()>3)
        //             q.poll();
        //     }
        // }

        // while(!q.isEmpty()){
        //     res.add(0,q.poll());
        // }
        // return res;
    }
}