//Best Approach: Space optimized Trie
//TC:O(P+Q)
//SC:O(n)
class AutocompleteSystem {

    StringBuilder builder;

    TrieNode curr;

    TrieNode root;

    Map<String, Integer> hotness;

    public AutocompleteSystem(String[] sentences, int[] times) {

        root = new TrieNode();

        curr = root;

        hotness = new HashMap<>();

        builder = new StringBuilder();

        for(int i = 0; i< sentences.length;i++){

            addToTrie(root,sentences[i],times[i]);

        }

    }

    public List<String> input(char c) {

        if(c == '#'){

            addToTrie(root,builder.toString(),1);

            builder.setLength(0);

            curr = root;

            return new ArrayList<>();

        } else {

            if(curr.children[c] == null) {

                curr.children[c] = new TrieNode();

            }

            builder.append(c);

            curr = curr.children[c];

            return curr.pq;

        }

    }

    private void addToTrie(TrieNode node, String s, int t){

        int i = 0;

        hotness.put(s,hotness.getOrDefault(s,0)+t);

        while(i<s.length()){

            if(node.children[s.charAt(i)] == null) {

                node.children[s.charAt(i)] = new TrieNode();

            }

            node = node.children[s.charAt(i)];

            if(!node.pq.contains(s))node.pq.add(s);

            Collections.sort(node.pq,(a,b)-> hotness.get(b)!=hotness.get(a)?hotness.get(b)-hotness.get(a):a.compareTo(b));

            if(node.pq.size()>3)node.pq.remove(3);

            i++;

        }

        node.isEnd = true;

    }

    class TrieNode{

        TrieNode[] children;

        boolean isEnd;

        List<String> pq;

        public TrieNode(){

            children = new TrieNode[128];

            pq = new ArrayList();

        }

    }

}