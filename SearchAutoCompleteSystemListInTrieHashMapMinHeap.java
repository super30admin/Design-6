import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class SearchAutoCompleteSystemListInTrieHashMapMinHeap {

    //  Trie and Hash Map - Time O(n) for every input and Space O(N*M)

    // Trie Node Constructor
    // maintain a list of string sentences passing through a trie node along with children trie nodes for further characters in a sentence
    public static class TrieNode {

        TrieNode[] children;
        List<String> sentences;

        public TrieNode() {

            this.children = new TrieNode[256];
            this.sentences = new ArrayList<>();
        }
    }

    // building Trie
    public void insertInTrie(String s) {

        // start from root
        TrieNode curr = root;

        // iterate over characters of sentence to be inserted in trie
        for(int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            // if trie node child at that character is not existent, create one
            if(curr.children[c-' '] == null) {

                curr.children[c-' '] = new TrieNode();
            }

            // move current trie node to that child with matching character
            curr = curr.children[c - ' '];

            // add this sentence to be inserted at the matching trie node where current pointer is moved to
            curr.sentences.add(s);
        }

    }

    // searching for sentences with prefix in trie
    public List<String> searchInTrie(String prefix) {

        // start from root
        TrieNode curr = root;

        // iterate over characters of prefix
        for(int i = 0; i < prefix.length(); i++) {

            char c = prefix.charAt(i);

            if(curr.children[c - ' '] == null) {

                // give empty list if prefix is not existent
                return new ArrayList<>();
            }

            curr = curr.children[c - ' '];
        }
        // give list of string sentences with given prefix
        return curr.sentences;
    }

    HashMap<String, Integer> map;

    TrieNode root;

    public SearchAutoCompleteSystemListInTrieHashMapMinHeap(String[] sentences, int[] times) {

        map = new HashMap<>();

        root = new TrieNode();

        int n = sentences.length;

        // build sentence frequency map
        for(int i = 0; i < n; i++) {     // O(n) space

            String sentence = sentences[i];
            int frequency = times[i];

            // populate hash map and trie with sentences from string array
            // avoid duplicate sentence additions in trie which is just for checking sentences with prefixes
            if(!map.containsKey(sentence)) {

                insertInTrie(sentence);
            }

            // if sentence repeats, frequency gets incremented
            map.put(sentence, map.getOrDefault(sentence, 0) + frequency);
        }

    }

    StringBuilder sb = new StringBuilder();

    public List<String> input(char c) {              // O(N)

        // current search sentence is over and be added to frequency map for future inputs
        if(c == '#') {

            String newSentence = sb.toString();

            // avoid duplicate sentence additions in trie which is just for checking sentences with prefixes
            if(!map.containsKey(newSentence)) {

                insertInTrie(newSentence);
            }

            // if new sentence is already there in hash map, frequency gets incremented by 1
            map.put(newSentence, map.getOrDefault(newSentence, 0) + 1);

            // make search string builder empty again as current search sentence has just ended
            sb = new StringBuilder();

            // output an empty list when sentence finishes
            return new ArrayList<>();
        }

        // maintain a Min Heap of sentences frequency wise
        // i.e., sort priority queue by minimum frequency and lower lexicographical order
        PriorityQueue<String> topThree = new PriorityQueue<>((a, b) -> {  // O(1) space

            // if frequency matches, heapify by lexicographical order
            if(map.get(a) == map.get(b)) {

                return b.compareTo(a);
            }

            // min heap condition
            return map.get(a) - map.get(b);
        });

        // add input character to search string builder
        sb.append(c);

        // current search string
        String searchStr = sb.toString();

        // get list of string sentences with current search string as prefix from trie through search method
        List<String> prefixFoundIn = searchInTrie(searchStr);

        // iterate over these list of strings with relevant prefix and get top three frequency through min heap
        for(String sentence: prefixFoundIn) {         // O(n)

            // add to min heap priority queue
            topThree.add(sentence);

            // limit min heap size to 3 as only top 3 searches are asked for
            if(topThree.size() > 3) {

                // pop out anything above 3 size limit
                topThree.poll();
            }

        }

        List<String> result = new ArrayList<>();

        while(!topThree.isEmpty()) {

            // min heap root has minimum frequency and the highest frequency sentence will be at the last of min heap which has to be appended to the front of result list
            result.add(0, topThree.poll());
        }

        // output list for every input character
        return result;
    }

    public static void main(String[] args) {

        String[] sentences = {"i love you", "island", "iroman", "i love leetcode"};
        int[] times = {5, 3, 2, 2};

        SearchAutoCompleteSystemListInTrieHashMapMinHeap obj =
                new SearchAutoCompleteSystemListInTrieHashMapMinHeap(sentences, times);

        char[] inChar = {'i', ' ', 'a', '#'};

        for(int i = 0; i < 4; i++) {

            List<String> ls = obj.input(inChar[i]);

            System.out.println("");
            System.out.println("Top three searches now: ");

            for(String str: ls) {

                System.out.println(str);
            }

            if(inChar[i] == '#')      System.out.println("Search sentence completely entered now");
        }
    }
}

/*
TIME COMPLEXITY = O(n)

In brute force, we iterated over all strings in hashmap for every input - O(N)

Now,
we iterate over only list of strings, with prefix formed by input character,  obtained from trie  - O(n)
and
heapify these in a min heap priority queue

n = average number of strings with right prefix for every character input

k = 3 = constant


SPACE COMPLEXITY = O(N*M)

Hash map space O(N)

Trie space O(N*M)
N = length of input sentences and times arrays
M = average length of each sentence in string array

Min Heap priority Queue has constant O(1) space
*/

/*
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */