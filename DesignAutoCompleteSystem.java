// Time Complexity : O(n log 3) - O(n)- words start with search word
// Space Complexity : O()
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


class AutocompleteSystem {

    HashMap<String, Integer> map;
    String search;
    TrieNode root;

    class TrieNode {
        TrieNode[] children;
        List<String> startsWith;

        public TrieNode() {
            this.children = new TrieNode[256];
            this.startsWith = new ArrayList<>();
        }
    }

    private void insert(String word){
        TrieNode curr = root;

        for(char c : word.toCharArray()) {
            if(curr.children[c-' '] == null){
                curr.children[c- ' '] = new TrieNode();
            }

            curr = curr.children[c - ' '];
            curr.startsWith.add(word);
        }
    }

    private List<String> prefixSearch(String prefix){
        TrieNode curr = root;

        for(char c : prefix.toCharArray()) {
            if(curr.children[c-' '] == null){
                return new ArrayList<>();
            }

            curr = curr.children[c - ' '];

        }
        return curr.startsWith;
    }

    public AutocompleteSystem(String[] sentences, int[] times) {
        this.map = new HashMap<>();
        this.search = "";
        this.root = new TrieNode();
        for(int i=0;i< sentences.length;i++) {
            String sen = sentences[i];
            int time = times[i];
            if(!map.containsKey(sen)) {
                insert(sen);
            }
            map.put(sen, map.getOrDefault(sen, 0) + time);

        }
    }

    public List<String> input(char c) {
        List<String> result = new ArrayList<>();
        if(c == '#'){
            if(!map.containsKey(search)) {
                insert(search);
            }
            map.put(search, map.getOrDefault(search, 0) + 1);
            search="";
            return result;
        }

        search += c;
        PriorityQueue<String> pq = new PriorityQueue<>((String a, String b) -> {
            if(map.get(a) == map.get(b)){
                return b.compareTo(a);
            }

            return map.get(a) - map.get(b);
        });

        for(String sentence : prefixSearch(search)){
            if(sentence.startsWith(search)) {
                pq.add(sentence);

                if(pq.size() > 3){
                    pq.poll();
                }
            }
        }

        while(!pq.isEmpty()){
            result.add(0,pq.poll());
        }

        return result;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
// Using Heap and HashMAp - TC : O(N) + O(N log 3) = O(N)
class AutocompleteSystem {

    HashMap<String, Integer> map;
    String search;

    public AutocompleteSystem(String[] sentences, int[] times) {
        this.map = new HashMap<>();
        this.search = "";
        for(int i=0;i< sentences.length;i++) {
            String sen = sentences[i];
            int time = times[i];

            map.put(sen, map.getOrDefault(sen, 0) + time);
        }
    }

    public List<String> input(char c) {
        List<String> result = new ArrayList<>();
        if(c == '#'){
            map.put(search, map.getOrDefault(search, 0) + 1);
            search="";
            return result;
        }

        search += c;
        PriorityQueue<String> pq = new PriorityQueue<>((String a, String b) -> {
            if(map.get(a) == map.get(b)){
                return b.compareTo(a);
            }

            return map.get(a) - map.get(b);
        });

        for(String sentence : map.keySet()){
            if(sentence.startsWith(search)) {
                pq.add(sentence);

                if(pq.size() > 3){
                    pq.poll();
                }
            }
        }

        while(!pq.isEmpty()){
            result.add(0,pq.poll());
        }

        return result;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */