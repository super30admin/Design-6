// Time Complexity : 
// Space Complexity :
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach

class AutoCompleteSystem{
    HashMap<String, Integer> hash;
    TrieNode root;
    class TrieNode{
        List<String> pq;
        HashMap<Character, TrieNode> children;
        public TrieNode(){
            pq = new ArrayList<>();
            children = new HashMap<>();
        }
    }
    
    private void insert(String sentence, int time){
        TrieNode cur = root;
        for(int i = 0; i < sentence.length(); i++){
            char c = sentence.charAt(i);
            if(!cur.children.containsKey(c)){
                cur.childern.put(c, new TrieNode());
            }
            cur = cur.children.get(c);
            List<String> curLi = cur.pq;
            if(!curLi.contains(sentence)){
                curLi.add(sentence);
            }
            Collections.sort(curLi, (a,b) ->{
                if(hash.get(a) == hash.get(b)){
                    return a.compareTo(b);
                }
                return hash.get(b) - hash.get(a);
            });
            if(curLi.size() > 3){
                curLi.remove(curLi.size() - 1);     
            }
            cur.pq = curLi;
        }
    }
    private List<String> search(String input){
        TrieNode cur = root;
        for(int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            if(!cur.children.containsKey(c)){
                return new ArrayList<>();
            }
            cur = cur.children.get(c);
        }
        return cur.pq;
    }
    StringBuilder inpit;
    public AutoCompleteSystem (String [] sentences, int [] times){
        input = new StringBuilder();
        map = new HashMap<>();
        root = new TrieNode();
        for(int i = 0; i < sentences.length; i++){
            map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
            insert(sentences[i], times[i]);
        }
    }
    public List<String> input(char c){
        if(c == '#'){
            String in = input.toString();
            map.put(in, map.getOrDefault(in, 0) + 1);
            insert(in,1);
            input = new StringBuilder();
            return new ArrayList<>();
        }
        input.append(c);
        List<String> result = search(input.toString());
        return result;
    }
}
