// Time Complexity : O(nlogk)
// Space Complexity :O(1)
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :
class AutocompleteSystem {
    class Trie{
        Trie[] children;
        List<String> li;
        public Trie(){
            this.children = new Trie[256];
            this.li = new ArrayList<>();
        }
    }
    Trie root;
    HashMap<String,Integer> map;
    StringBuilder sb;
    private void insert(String sentence){
        Trie curr = root;
        for(int i = 0; i < sentence.length(); i++){
            char c = sentence.charAt(i);
            if(curr.children[c-' ']==null)
                curr.children[c-' '] = new Trie();
            curr = curr.children[c-' '];
            List<String> tmp = curr.li;
            if(!tmp.contains(sentence))
            tmp.add(sentence);
            Collections.sort(tmp,(a,b) -> {
                int cnta = map.get(a);
                int cntb = map.get(b);
                if(cnta == cntb)
                    return a.compareTo(b);
                return cntb - cnta;
            });
            if(tmp.size() > 3)
                tmp.remove(tmp.size()-1);
        }
    }

    public AutocompleteSystem(String[] sentences, int[] times) {
        this.sb = new StringBuilder();
        this.map = new HashMap<>();
        this.root = new Trie();
        for(int i = 0; i < sentences.length; i++){
            map.put(sentences[i],times[i]);
            insert(sentences[i]);
        }
    }
    private List<String> search(String sentence){
        Trie curr = root;
        for(int i = 0; i < sentence.length(); i++){
            char c = sentence.charAt(i);
            if(curr.children[c-' ']==null)
                return new ArrayList<>();
            curr = curr.children[c-' '];
    }
    return curr.li; }
    
    public List<String> input(char c) {
        if(c == '#'){
            String tmp = sb.toString();
            sb = new StringBuilder();
            map.put(tmp,map.getOrDefault(tmp,0)+1);
            insert(tmp);
            return new ArrayList<>();
        }
        sb.append(c);
        return search(sb.toString());

    }
}