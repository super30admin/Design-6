import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Time Complexity: for search - O(M + log 3) where M is the length of the
 * input prefix. We maintain only top 3 sentences at each TrieNode.
 * Space Complexity: O(N * L) for storing the Trie and frequency map. N is the number
 * of sentences and L is the average length of the sentences.
 */
public class AutocompleteSystem1 {

    private String search = "";
    private Trie trie = new Trie();
    private static Map<String, Integer> frequencyMap = new HashMap<>();

    public AutocompleteSystem1(String[] sentences, int[] times) {
        for (int i = 0; i < sentences.length; i++) {
            frequencyMap.put(sentences[i], times[i]);
            trie.insert(sentences[i]);
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
                    n.top3.add(word);
                    curr = n;
                } else {
                    if (!child.top3.contains(word)) // check if word already exists
                        child.top3.add(word);
                    curr = child;
                }
                if (curr.top3.size() > 3) {
                    sort(curr);
                    curr.top3.remove(3);
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
            sort(curr);
            return curr.top3.subList(0, Math.min(3, curr.top3.size()));
        }

        private void sort(TrieNode curr) {
            Collections.sort(curr.top3, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    int freq1 = frequencyMap.getOrDefault(o1, 1);
                    int freq2 = frequencyMap.getOrDefault(o2, 1);
                    if (freq1 != freq2) {
                        return freq2 - freq1; // Descending order of frequency
                    } else {
                        return o1.compareTo(o2); // Ascending lexicographical order
                    }
                };
            });
        }

        static class TrieNode {
            TrieNode[] children = new TrieNode[256];
            List<String> top3 = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        AutocompleteSystem1 obj = new AutocompleteSystem1(
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
