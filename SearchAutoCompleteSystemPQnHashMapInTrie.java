import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class SearchAutoCompleteSystemPQnHashMapInTrie {

    //  Priority Queue and Hash Map in Trie -  Optimised - Time O(l) and Space O(n)

    // Trie Node Constructor
    public static class TrieNode {

        HashMap<Character, TrieNode> children;
        List<String> topLi;

        public TrieNode() {

            this.children = new HashMap<>();
            this.topLi = new ArrayList<>();
        }
    }

    // building Trie
    public void insertInTrie(String s) {        // O(l)

        // start from root
        TrieNode curr = this.root;

        // iterate over characters in string to be inserted
        for(int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            // if character is not found in children of trie node, make a new trie node
            if(!curr.children.containsKey(c)) {

                curr.children.put(c, new TrieNode());
            }

            // move to child trie node
            curr = curr.children.get(c);

            // get top list at current trie node
            List<String> topLi = curr.topLi;

            // add sentence to be inserted to the end of top list , only if it is not there already
            if(!topLi.contains(s)) {

                topLi.add(s);
            }

            // this sort replaces the task of priority queue
            //  sort top list according to custom comparator
            Collections.sort(topLi, (a, b) -> {    // O(1) = O(3)

                if(map.get(a) == map.get(b)) {

                    // sentence with lower lexicographical character remains in top list i.e., ascending order
                    return a.compareTo(b);
                }

                // top list with top frequency sentence at the front i.e., descending order
                return map.get(b) - map.get(a);
            });

            // limit top list size at 3
            if(topLi.size() > 3) {

                topLi.remove(topLi.size() - 1);
            }
        }

    }

    // searching for top sentences with prefix in trie
    public List<String> searchInTrie(String prefix) {

        TrieNode curr = root;

        for(int i = 0; i < prefix.length(); i++) {

            char c = prefix.charAt(i);

            if(!curr.children.containsKey(c)) {

                // output empty list of strings as child trie node matching with prefix character is not found
                return new ArrayList<>();
            }

            // update current trie node, if matching child is found
            curr = curr.children.get(c);
        }
        // after iterating over prefix, current trie node's top list gives list of strings with right prefix and high frequency
        return curr.topLi;
    }

    HashMap<String, Integer> map;

    TrieNode root;

    public SearchAutoCompleteSystemPQnHashMapInTrie(String[] sentences, int[] times) {

        map = new HashMap<>();

        root = new TrieNode();

        int n = sentences.length;

        // build sentence frequency map
        for(int i = 0; i < n; i++) {     // O(n) space

            String sentence = sentences[i];
            int frequency = times[i];

            // frequency map dictates order of top list in trie nodes
            map.put(sentence, map.getOrDefault(sentence, 0) + frequency);

            // each sentence is inserted in trie after hash map
            insertInTrie(sentence);
        }

    }

    StringBuilder sb = new StringBuilder();

    public List<String> input(char c) {              // O(N)

        // current search sentence is over and be added to frequency map for future inputs
        if(c == '#') {

            String newSentence = sb.toString();

            // insert new sentence in hash map first and then in trie
            map.put(newSentence, map.getOrDefault(newSentence, 0) + 1);

            insertInTrie(newSentence);      // O(l)

            // make search string builder empty again as current search sentence has just ended
            sb = new StringBuilder();

            // output an empty list when sentence finishes
            return new ArrayList<>();
        }


        // add input character to search string builder
        sb.append(c);

        String searchStr = sb.toString();

        // search for top three search results with search string as their prefix in trie
        return searchInTrie(searchStr);

    }

    public static void main(String[] args) {

        String[] sentences = {"i love you", "island", "iroman", "i love leetcode"};
        int[] times = {5, 3, 2, 2};

        SearchAutoCompleteSystemPQnHashMapInTrie obj =
                new SearchAutoCompleteSystemPQnHashMapInTrie(sentences, times);

        char[] inChar = {'i', ' ', 'a', '#'};

        for(int i = 0; i < 4; i++) {

            List<String> ls = obj.input(inChar[i]);

            System.out.println("");
            System.out.println("Top three searches now: ");

            for (String str : ls) {

                System.out.println(str);
            }

            if (inChar[i] == '#') System.out.println("Search sentence completely entered now");

        }
    }
}

/*
TIME COMPLEXITY = O(l)

l - average length of sentence to be inserted

SPACE COMPLEXITY = O(n)

n - length of sentence array
Hash Map space = O(n)
*/

/*
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */