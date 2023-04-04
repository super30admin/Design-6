//time complexity: O(m*n*k) m=length of sentence, n=no.of sentences, k=no.of suggestions required
//space complexity: O(m*n)
//ran on leetcode: yes
//Trie data structure stores the sentences and their frequency. For each input character, the corresponding node in the Trie is traversed, 
//and a priority queue is maintained to store the top 3 most frequently occurring sentences in the subtree rooted at that node. 
//When a new sentence is added to the Trie, its frequency is updated, and the priority queue for all nodes in the path from the root to the leaf is updated to contain only the top 3 most frequently occurring sentences. 
//When a "#" character is encountered, the current sentence is added to the Trie with a frequency of 1 and the Trie is reset to the root node.
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
