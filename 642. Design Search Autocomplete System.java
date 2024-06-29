class AutocompleteSystem {

    // This is the most optimal approach also look into the 2 previous submissions
    HashMap<String, Integer> map;
    StringBuilder sb;

    class TrieNode
    {
        // This is exactly same as TrieNode[] children, we are just changing the implementation
        HashMap<Character, TrieNode> children;

        // This will only hold 3 hot sentences
        List<String> result;

        public TrieNode()
        {
            children = new HashMap();
            result = new ArrayList();
        }
    }

    TrieNode root;

    private void insert(String sentence)
    {
        TrieNode curr = root;

        for(int i = 0; i < sentence.length(); i++)
        {
            char c = sentence.charAt(i);

            // If the trienode doesn't contain this child then create a new trienode
            if(!curr.children.containsKey(c)) curr.children.put(c, new TrieNode());

            // Keep moving forward
            curr = curr.children.get(c);

            List<String> temp = curr.result;

            // If list doesn't contain sentence only then you add it
            if(!temp.contains(sentence)) temp.add(sentence);

            Collections.sort(temp, (a, b) -> {

            // This is bcoz we need to remove the least frequency string from our list
            if(map.get(a) == map.get(b)) return a.compareTo(b);

            // This is bcoz we are sorting the array in such a way that the string at 0th index would have 
            // highest frequency
            return map.get(b) - map.get(a);
            });

            if(temp.size() > 3)
            {
                temp.remove(temp.size() - 1);
            }

            // Assign it back to the result inside node
            curr.result = temp;
        }
    }

    private List<String> search(String prefix)
    {
        // In this function we search for a prefix and return it's associated array list

        TrieNode curr = root;

        for(int i = 0; i < prefix.length(); i++)
        {
            char c = prefix.charAt(i);

            if(!curr.children.containsKey(c)) return new ArrayList();

            curr = curr.children.get(c);
        }

        // Return the associated list
        return curr.result;
    }

    public AutocompleteSystem(String[] sentences, int[] times) {

        root = new TrieNode();
        sb = new StringBuilder();
        map = new HashMap();

        // Let us first add all the strings into the trie and global map
        // Given that both the sentences and times array are of same length
        int n = sentences.length;

        for(int i = 0; i < n; i++)
        {
            String sentence = sentences[i];

            // Add this entry into global map
            map.put(sentence, map.getOrDefault(sentence, 0) + times[i]);

            // Eg => "i love you"
            insert(sentence);
        }
    }

    public List<String> input(char c) {

        // If the user entered # then we don't need to give search recommendations. We need to add this entry into our
        // map
        if(c == '#')
        {
            // Store the string as we need to add this to map as well as in trie
            String soFar = sb.toString();

            sb = new StringBuilder();

            // Add this entry into global map
            map.put(soFar, map.getOrDefault(soFar, 0) + 1);

            insert(soFar);

            // We have to return an empty array list if user types #
            return new ArrayList();
        }

        sb.append(c);
        String soFar = sb.toString();

        // Just return the result we get
        return search(soFar);        
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
