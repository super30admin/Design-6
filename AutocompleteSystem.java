// Time Complexity : The time complexity is O(n + m) where n is the length of the input and m is the numer of words with input as prefix
// Space Complexity : The space complexity if O(m*n) where m is the number of letters and n is the number of words
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach

class AutocompleteSystem {

    Trie root;
    String input;

    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new Trie();
        input = "";

        // store the sentences in a trie
        for(int i=0;i<sentences.length;i++){
            insert(sentences[i],times[i]);
        }
    }

    public List<String> input(char c) {

        // Add to the trie
        if(c == '#'){
            insert(input,1);
            input = "";
            return new ArrayList<>();
        }

        input += c;

        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> {
            if(a.freq == b.freq){
                return b.s.compareTo(a.s);
            }
            return a.freq-b.freq;
        });

        HashMap<String,Integer> map = check(input);

        //search by prefix
        for(String str:map.keySet()){
            pq.offer(new Pair(str,map.get(str)));

            if(pq.size() > 3){
                pq.poll();
            }
        }

        List<String> res = new ArrayList<>();

        while(!pq.isEmpty()){
            res.add(0,pq.poll().s);
        }

        return res;

    }

    //add to the trie
    public void insert(String word,int time){

        Trie cur = root;

        for(int i=0;i<word.length();i++){
            char ch = word.charAt(i);

            if(!cur.children.containsKey(ch)){
                cur.children.put(ch,new Trie());
            }

            cur = cur.children.get(ch);
            cur.count.put(word,cur.count.getOrDefault(word,0)+time);
        }
    }

    public HashMap<String,Integer> check(String word){

        Trie cur = root;

        for(int i=0;i<word.length();i++){

            char ch = word.charAt(i);

            if(!cur.children.containsKey(ch)){
                return new HashMap<>();
            }

            cur = cur.children.get(ch);
        }

        return cur.count;
    }
}

class Trie{

    HashMap<String,Integer> count;
    HashMap<Character,Trie> children;

    public Trie(){
        count = new HashMap<>();
        children = new HashMap<>();
    }
}

class Pair{

    String s;
    int freq;

    public Pair(String s,int freq){
        this.s = s;
        this.freq = freq;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */