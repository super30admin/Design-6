// Time Complexity : n*klog(n*k) where is n is length of sentencesce array and k is avaerage length of each sentence.
// Space Complexity : l where l is number of unique characters
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach : add elemts into trie and search the character and retuern all string from that character
class AutocompleteSystem {

    TrieNode root;

    StringBuilder sb;
    TrieNode curr;

    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        sb = new StringBuilder();
        curr = root;
        for(int i=0; i<sentences.length; i++){
            insert(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if(c == '#'){
            insert(sb.toString(), 1);
            sb = new StringBuilder();
            curr = root;
        }
        else{
            sb.append(c);
            if(curr != null){
                TrieNode temp = curr.map.get(c);
                if(temp != null){
                    res = getSentences(temp);
                }
                curr = temp;
            }
        }

        return res;
    }

    Queue<Pair<String, Integer>> q;

    public List<String> getSentences(TrieNode temp){
        q = new PriorityQueue<>((a,b) ->  {
                int x = Integer.compare(a.getValue(), b.getValue());
                if( x == 0){
                    return b.getKey().compareTo(a.getKey());
                }
                return x;
        });

        dfs(temp, new StringBuilder());

        List<String> res = new ArrayList<>();
        while(!q.isEmpty()){
            res.add(0, q.poll().getKey());
        }
        return res;
    }


    public void dfs(TrieNode root, StringBuilder path){

        if(root.end != 0){
            q.add(new Pair<>(sb.toString()+path.toString(), root.end));
            if(q.size() > 3){
                q.poll();
            }
        }

        for(Map.Entry<Character, TrieNode> e : root.map.entrySet()){
            char ch = e.getKey();
            TrieNode child = e.getValue();
            path.append(ch);
            dfs(child, path);
            path.deleteCharAt(path.length() - 1);
        }
    }


    public void insert(String sentence, int times){

        TrieNode curr = root;

        for(char ch : sentence.toCharArray()){

            TrieNode temp = curr.map.get(ch);
            if( temp == null){
                temp = new TrieNode();
                curr.map.put(ch, temp);
            }
            curr = temp;
        }
        curr.end += times;
    }


    class TrieNode{

        Map<Character, TrieNode> map;
        int end;

        public TrieNode(){
            map = new HashMap<>();
            end = 0;
        }
    }

}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
