
// Time Complexity : O(1)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
import java.util.*;

class AutocompleteSystem {
    class TrieNode {
        // children array
        TrieNode[] children;
        // to get a list of startwih
        List<String> li;

        public TrieNode() {
            this.children = new TrieNode[256];
            this.li = new ArrayList<>();
        }

    }

    // maintain root node of the trie
    TrieNode root;
    // map for maintain freq
    HashMap<String, Integer> map;
    // stringbuilder for seach string
    StringBuilder sb;

    private void insert(String sentence) {
        // insert sentence in the TrieNode
        TrieNode curr = root;
        // for loop for add all chars of sentence in the trie;
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            // if c is not already in the trie we will
            // make a new node
            if (curr.children[c - ' '] == null) {
                curr.children[c - ' '] = new TrieNode();
            }

            // make curr = curr.children
            curr = curr.children[c - ' '];
            // get list of strings at current Node
            List<String> temp = curr.li;
            // here first check list already contains sentence or not
            if (!temp.contains(sentence)) {
                // add sentence into current node's list
                temp.add(sentence);
            }
            // sort the list by freq
            Collections.sort(temp, (a, b) -> {
                int ca = map.get(a);
                int cb = map.get(b);
                // check if frequency is same we sort this by lexiographically
                if (ca == cb) {
                    return a.compareTo(b);
                }
                return cb - ca;
            });
            // if list size greater than 3 remove last element from the list
            // here we are maintaing at most 3 sentence at each node in sorted manner
            if (temp.size() > 3) {
                temp.remove(temp.size() - 1);
            }

        }
    }

    private List<String> searchInTrie(String search) {
        // insert search in the TrieNode
        TrieNode curr = root;
        // for loop for add all chars of search in the trie;
        for (int i = 0; i < search.length(); i++) {
            char c = search.charAt(i);
            // if c is not already in the trie we will
            // return new list
            if (curr.children[c - ' '] == null)
                return new ArrayList<>();
            // make curr = curr.children
            curr = curr.children[c - ' '];
        }
        // return currentnode's list
        return curr.li;
    }

    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        map = new HashMap<>();
        sb = new StringBuilder();
        // add all the sentences in the trie and map
        for (int i = 0; i < sentences.length; i++) {
            String sentence = sentences[i];
            int time = times[i];
            map.put(sentence, time);
            insert(sentence);
        }
    }

    public List<String> input(char c) {
        // first check if char is # then reset stringbuilder and add this into the map
        // and trie
        // and return
        // empty arrayList
        if (c == '#') {
            String search = sb.toString();
            // if map doesnot contains key that means we are adding this sentence for the
            // first
            // time so add into the map
            map.put(search, map.getOrDefault(search, 0) + 1);
            insert(search);
            // reset sb
            sb = new StringBuilder();
            // return empty list
            return new ArrayList<>();
        }
        // append char c in stringBuilder
        sb.append(c);
        // search string
        String search = sb.toString();
        return searchInTrie(search);

    }

    public static void main(String[] args) {
        String[] sentences = new String[] { "i love you", "island", "iroman", "i love leetcode" };
        int[] times = new int[] { 5, 3, 2, 2 };
        AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
        System.out.println(obj.input('i'));
        // return ["i love you", "island", "i love leetcode"]. There are four sentences
        // that have prefix "i". Among them, "ironman" and "i love leetcode" have same
        // hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love
        // leetcode" should be in front of "ironman". Also we only need to output top 3
        // hot sentences, so "ironman" will be ignored.
        System.out.println(obj.input(' '));
        // return ["i love you", "i love leetcode"]. There are only two sentences that
        // have prefix "i ".
        System.out.println(obj.input('a'));
        // return []. There are no sentences that have prefix "i a".
        System.out.println(obj.input('#'));
        // return []. The user finished the input, the sentence "i a" should be saved as
        // a historical sentence in system. And the following input will be counted as a
        // new search.
    }
}