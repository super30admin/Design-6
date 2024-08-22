/*
Tc --> O(n)
SC --> O(n)
*/
class Trie{
    HashMap<Character, Trie> children;
    List<String> ls;

    public Trie(){
        children = new HashMap<>();
        ls = new ArrayList<>();
    }
}
class AutocompleteSystem {
    HashMap<String, Integer> freq;
    StringBuilder st;
    Trie root;
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new Trie();
        freq = new HashMap<>();
        st = new StringBuilder();
        for(int i=0;i<sentences.length;i++){
            createDict(root, sentences[i]);
            freq.put(sentences[i],freq.getOrDefault(sentences[i],0)+times[i]);
            
        }




    }
    
    public List<String> input(char c) {
        if(c=='#'){
            String w = st.toString();
            if(!freq.containsKey(w)){
                createDict(root, w);
            }
            freq.put(w, freq.getOrDefault(w,0)+1);
            st = new StringBuilder();
            return new ArrayList<>();
            
        }
        st.append(c);
        List<String> words = search(root, st.toString());
        PriorityQueue<String> pq = new PriorityQueue<>(
            (a,b)->{
                int c1 = freq.get(a);
                int c2 = freq.get(b);

                if(c1==c2){
                    return b.compareTo(a);
                }
                return c1-c2;

            }
        );

        for(String word: words){
            pq.add(word);
            if(pq.size()>3){
                pq.poll();
            }
        }//for

        List<String> res = new ArrayList<>();

        while(!pq.isEmpty()){
            res.add(0,pq.poll());
        }

        return res;

        


    }

    public void createDict(Trie root, String word){
        Trie curr = root;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if(!curr.children.containsKey(c)){
                curr.children.put(c, new Trie());
            }
            curr = curr.children.get(c);
            curr.ls.add(word);
        }//for

        

    }

    public List<String> search(Trie root, String word){
        Trie curr = root;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if(curr.children.get(c)==null){
                return new ArrayList<>();
            }
            curr = curr.children.get(c);

        }//for

        return curr.ls;
    }//method
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */