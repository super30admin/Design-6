/*Approach: We need to consider each character of the every word given in sentences array,
and add an entry corresponding to each such character at one level of the trie. At the leaf node of every word,
we can update the times section of the node with the corresponding number of times this word has been typed.
to implement the input(c) function, for every input character cc, we need to add this character to the word
being formed currently, i.e. to \text{cur\_sent}cur_sent. Then, we need to traverse in the current trie
till all the characters in the current word, \text{cur\_sent}cur_sent, have been exhausted.

From this point onwards, we traverse all the branches possible in the Trie,
put the sentences/words formed by these branches to a list along with their corresponding number of occurrences,
and find the best 3 out of them similar to the last approach.*/

import java.util.*;

class DesignSearchAutocompleteSystem {
    class TrieNode {
        private final TrieNode[] children;
        private final ArrayList<String> hotlist;

        public TrieNode() {
            children = new TrieNode[27]; //26 + space
            hotlist = new ArrayList<>();
        }

        // Insert this string s into the Trie tree
        private void update(String s) {
            TrieNode curr = root;
            for(int i=0; i<s.length(); i++) {
                int idx = (s.charAt(i) == ' ') ? 26 : (s.charAt(i) - 'a');
                if(curr.children[idx] == null) {
                    curr.children[idx] = new TrieNode();
                }
                curr = curr.children[idx];
                insert(curr.hotlist, s);
            }
        }

        // Insert the string s into the sorted list and keep the size of list as 3
        private void insert(ArrayList<String> hotlist, String s) {
            int i;
            for(i=0; i<hotlist.size(); i++) {
                if(hotlist.get(i).equals(s)) {
                    hotlist.remove(i);
                    break;
                }
            }
            for(i=0; i<hotlist.size(); i++) {
                if(greater(s, hotlist.get(i))) {
                    hotlist.add(i, s);
                    break;
                }
            }
            if(i == hotlist.size()) {
                hotlist.add(s);
            }
            if(hotlist.size() > 3) {
                hotlist.remove(hotlist.size() - 1);
            }
        }

        //
        private boolean greater(String a, String b) {
            int cntA = count.get(a);
            int cntB = count.get(b);
            if(cntA != cntB) {
                return cntA > cntB;
            }
            return a.compareTo(b) < 1;
        }
    }

    private final TrieNode root;
    private TrieNode curr;  // curr is keep track current node search until '#' reset it
    private StringBuilder sb;
    private final HashMap<String, Integer> count;

    public DesignSearchAutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        curr = root;
        sb = new StringBuilder();
        count = new HashMap<String, Integer>();

        for(int i=0; i<sentences.length; i++) {
            count.put(sentences[i], times[i]);
            root.update(sentences[i]);
        }
    }

    public List<String> input(char c) {
        if(c == '#') {
            curr = root;
            String s = sb.toString();
            sb = new StringBuilder();
            count.put(s, count.getOrDefault(s, 0) + 1);
            curr.update(s);
            return new ArrayList<String>();
        } else {
            sb.append(c);
            if(curr == null) {
                return new ArrayList<String>();
            }
            int idx = (c == ' ') ? 26 : (c - 'a');
            curr = curr.children[idx];
            if(curr == null) {
                return new ArrayList<String>();
            }
            return curr.hotlist;
        }
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */