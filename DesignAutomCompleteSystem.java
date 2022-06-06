//TC : O(N) where N is the total no of nodes in Trie
//SC : O(N)
class AutocompleteSystem {

    class TrieNode{
        HashMap <Character,TrieNode>children;
        List <String>sentenceList;

        public TrieNode(){
            children = new HashMap<>();
            sentenceList = new ArrayList();
        }
    }
    public void insertIntoTrie(String sentence,int times)
    {
        TrieNode curr = root;
        for(int i=0;i<sentence.length();i++)
        {
            char c = sentence.charAt(i);
            if(!curr.children.containsKey(c))
                curr.children.put(c,new TrieNode());

            curr = curr.children.get(c);
            List<String> temp = curr.sentenceList;
            if(!temp.contains(sentence)){
                temp.add(sentence);
            }
            Collections.sort(temp, (a, b) -> {
                if(map.get(a) ==  map.get(b)) {
                    return a.compareTo(b);
                }
                return map.get(b) - map.get(a);
            });

            if(temp.size() > 3){
                temp.remove(temp.size() - 1);
            }
            curr.sentenceList = temp;
        }
    }
    public List<String> searchTrie(String search)
    {
        TrieNode curr = root;
        int n = search.length();
        for(int i=0;i<n;i++)
        {
            char c = search.charAt(i);
            if(!curr.children.containsKey(c))
                return (new ArrayList());
            curr = curr.children.get(c);
        }
        return curr.sentenceList;
    }
    TrieNode root;
    HashMap<String,Integer> map;
    StringBuilder sb;
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        map = new HashMap();
        sb = new StringBuilder();
        int i=0;
        for(String sentence : sentences)
        {
            map.put(sentence,map.getOrDefault(sentence,0)+times[i]);
            insertIntoTrie(sentence,times[i]);
            i++;
        }
    }

    public List<String> input(char c) {
        if(c=='#')
        {
            String inputString = sb.toString();
            map.put(inputString, map.getOrDefault(inputString,0)+1);
            insertIntoTrie(inputString,1);
            sb = new StringBuilder();
            return new ArrayList<>();
        }
        sb.append(c);
        return searchTrie(sb.toString());
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */