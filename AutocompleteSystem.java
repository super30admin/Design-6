import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In this solution, we store all the results in the list at each TrieNode.
 * We will sort the list based on the frequency and lexicographical order when
 * we search for the prefix.
 * 
 * Time Complexity: for search - O(M + K log K) where M is the length of the
 * input prefix and K is the number of sentences with the given prefix. This is
 * because we sort the list of sentences at the TrieNode corresponding to the
 * end of the prefix.
 * Space Complexity: O(N * L) for storing the Trie and frequency map. N is the number
 * of sentences and L is the average length of the sentences.+
 */
public class AutocompleteSystem {

    private String search = "";
    private Trie trie = new Trie();
    private static Map<String, Integer> frequencyMap = new HashMap<>();

    public AutocompleteSystem(String[] sentences, int[] times) {
        for (int i = 0; i < sentences.length; i++) {
            trie.insert(sentences[i]);
            frequencyMap.put(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        if (c == '#') {
            trie.insert(search);
            search = "";
            return new ArrayList<>();
        }
        search += c;
        return trie.search(search);
    }

    static class Trie {

        private TrieNode root = new TrieNode();

        public void insert(String word) {
            TrieNode curr = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                TrieNode child = curr.children[c - ' '];
                if (child == null) {
                    TrieNode n = new TrieNode();
                    curr.children[c - ' '] = n;
                    n.list.add(word);
                    curr = n;
                } else {
                    if (!child.list.contains(word)) // check if word already exists
                        child.list.add(word);
                    curr = child;
                }
                if (i == word.length() - 1) {
                    frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
                }
            }
        }

        public List<String> search(String word) {
            TrieNode curr = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (curr.children[c - ' '] == null) {
                    return new ArrayList<>();
                } else {
                    curr = curr.children[c - ' '];
                }
            }
            Collections.sort(curr.list, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    int freq1 = frequencyMap.get(o1);
                    int freq2 = frequencyMap.get(o2);
                    if (freq1 != freq2) {
                        return freq2 - freq1; // Descending order of frequency
                    } else {
                        return o1.compareTo(o2); // Ascending lexicographical order
                    }
                };
            });
            return curr.list.subList(0, Math.min(3, curr.list.size()));
        }

        static class TrieNode {
            TrieNode[] children = new TrieNode[256];
            List<String> list = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        AutocompleteSystem obj = new AutocompleteSystem(
                new String[] { "i love you", "island", "ironman", "i love leetcode" },
                new int[] { 5, 3, 2, 2 });
        System.out.println(obj.input('i')); // return ["i love you", "island","i love leetcode"]
        System.out.println(obj.input(' ')); // return ["i love you","i love leetcode"]
        System.out.println(obj.input('a')); // return []
        System.out.println(obj.input('#')); // return []

        // iran
        System.out.println(obj.input('i')); // return ["i love you", "island","i love leetcode"]
        System.out.println(obj.input('r')); // return ["ironman"]
        System.out.println(obj.input('a')); // return []
        System.out.println(obj.input('n')); // return []
        System.out.println(obj.input('#')); // return []

        // 2nd time iran
        System.out.println(obj.input('i')); // return ["i love you", "island","i love leetcode"]
        System.out.println(obj.input('r')); // return ["ironman"]
        System.out.println(obj.input('a')); // return []
        System.out.println(obj.input('n')); // return []
        System.out.println(obj.input('#')); // return []

        // 3rd time iran
        System.out.println(obj.input('i')); // return ["i love you", "island","i love leetcode"]
        System.out.println(obj.input('r')); // return ["ironman"]
        System.out.println(obj.input('a')); // return []
        System.out.println(obj.input('n')); // return []
        System.out.println(obj.input('#')); // return []

        // india
        System.out.println(obj.input('i')); // return ["i love you", "island","i love leetcode"]
        System.out.println(obj.input('n')); // return []
        System.out.println(obj.input('d')); // return []
        System.out.println(obj.input('i')); // return []
        System.out.println(obj.input('a')); // return []
        System.out.println(obj.input('#')); // return []

        // search "in"
        System.out.println(obj.input('i')); // return ["i love you", "island","i love leetcode"]
        System.out.println(obj.input('n')); // return ["in"]

    }

}
