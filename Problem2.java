package Design-6;
// TC: O(nlog3)
// SC: O(N)
class AutocompleteSystem {
    HashMap<String, Integer> map;
    String search;
    TrieNode root;
    class TrieNode{
        HashMap<Character, TrieNode> children;
        ArrayList<String> top3Result;

        public TrieNode(){
            this.children = new HashMap<>();
            this.top3Result = new ArrayList<>();
        } 
    }

    public void insert(String word){
        TrieNode curr = root;
        for(char c: word.toCharArray()){
            if(!curr.children.containsKey(c)){
                curr.children.put(c, new TrieNode());
            }
            curr=curr.children.get(c);
            
            List<String> li = curr.top3Result;
            if(!li.contains(word)){
                li.add(word);
            }

            Collections.sort(li, (a,b)->{
                if(map.get(a)==map.get(b)){
                    return a.compareTo(b);
                }
                return map.get(b)-map.get(a);
            });

            if(li.size()>3){
                li.remove(li.size()-1);
            }
        }
    }

    public List<String> searchPrefix(String prefix){
        TrieNode curr=root;
        for(char c: prefix.toCharArray()){
            if(!curr.children.containsKey(c)){
                return new ArrayList<>();
            }
            curr=curr.children.get(c);
        }
        return curr.top3Result;
    }

    public AutocompleteSystem(String[] sentences, int[] times) {
        this.map = new HashMap<>();
        this.search = "";
        this.root = new TrieNode();

        for(int i=0;i<sentences.length;i++){
            String sentence = sentences[i];
            int time = times[i];
            map.put(sentence, time);
            insert(sentence);
        }    
    }
    
    public List<String> input(char c) {
        List<String> result = new ArrayList<>();
        if(c=='#'){
            map.put(search, map.getOrDefault(search,0)+1);
            insert(search);
            search="";
            return result;
        }
        search+=c;
        return searchPrefix(search);
    }
}
