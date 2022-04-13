// Space Complexity: Trie + Map O(nk) where n is number of sentence of average length k
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :No


// Your code here along with comments explaining your approach
// Trie with HashMap (for sorting based on count and then lexicographic string order if count is the same)
// Maintain top 3 list at each trie child that match till that prefix.
public class AutocompleteSystem {

    class TrieNode {
        List<String> result;
        Map<Character, TrieNode> children;
        public TrieNode()
        {
            result = new ArrayList<>();
            children = new HashMap<>();
        }
    }

    TrieNode root;
    StringBuilder curr;
    Map<String, Integer> map;

    public AutocompleteSystem(String[] sentences, int[] times)
    {
        map = new HashMap<>();
        root = new TrieNode();
        curr = new StringBuilder();

        // populate trie
        for(int i = 0 ; i < sentences.length; i ++)
        {
            // to add new strings and get count
            map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
            // for easy look up of starts with
            insert(sentences[i], times[i]);
        }
    }

    // O(n)
    public List<String> input(char c)
    {
        // end of sentence add it to map
        if(c == '#')
        {
            String input = curr.toString();

            // add to map
            map.put(input, map.getOrDefault(input, 0) + 1);
            // add new string to trie
            insert(input, 1); // O(n)
            // reset String buffer
            curr = new StringBuilder();
            return new ArrayList<>();
        }

        curr.append(c);

        // get all strings starting with curr from Trie
        List<String> result = search(curr.toString()); // O(n)
        return result;
    }

    // O(n)
    public void insert(String s, int times)
    {
        TrieNode curr = root;
        for(int i = 0 ; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if(!curr.children.containsKey(c))
            {
                curr.children.put(c, new TrieNode());
            }

            curr = curr.children.get(c);

            List<String> newResult = curr.result;
            if(!newResult.contains(s))
            {
                newResult.add(s);
            }

            // sort in descending order
            Collections.sort(newResult, (a, b) -> {
                if(map.get(a) == map.get(b)) {
                    return a.compareTo(b);
                }
                return map.get(b) - map.get(a);
            });

            if(newResult.size() > 3) {
                newResult.remove(newResult.size()-1);
            }

            curr.result = newResult;
        }
    }

    // O(n)
    public List<String> search(String prefix)
    {
        TrieNode curr = root;
        for(int i = 0 ; i < prefix.length(); i++)
        {
            char c = prefix.charAt(i);
            if(!curr.children.containsKey(c)) {
                return new ArrayList<>();
            }
            curr = curr.children.get(c);
        }

        return curr.result;
    }
}