//time complexity : O(1) for input function
//space complexity : O(n)

class AutocompleteSystem {

    //code 3 - maintain only top 3 sentences at each trie node instead of the while list in which the node is a prefix
    //retrieval of top 3 at any input will become O(1)

    class TrieNode {

        ArrayList<String> list;
//at each char maintain a list of top 3 strings that have that char's chain from root as their prefix
        HashMap<Character, TrieNode> children;
//we can't have 26 length array now because there maybe other chars like a space in a sentence

        public TrieNode() {
            list = new ArrayList();
            children = new HashMap();
        }
    }

    private void insert(String sentence) {

        TrieNode curr = root;
        for(int i=0; i<sentence.length(); i++) {
            char c = sentence.charAt(i);

            //c is not a child of curr yet, add a new node
            if(!curr.children.containsKey(c)) {
                curr.children.put(c, new TrieNode());
            }

            //move to the char c child
            curr = curr.children.get(c);

            //add sentence to this child node's list if not already there
            if(!curr.list.contains(sentence))
                curr.list.add(sentence);

            //sort this child node's list according to times in descending order
            ArrayList<String> currList = curr.list;

            Collections.sort(currList, (a,b) -> {
               if(map.get(a) == map.get(b))
                   return a.compareTo(b);
                return map.get(b) - map.get(a);
            });

            //if size of this list becomes greater than 3, remove the last element which is the smallest count since list is sorted
            if(curr.list.size() > 3)
                currList.remove(currList.size()-1);
        }

    }

    private ArrayList<String> search(String prefix) {

        TrieNode curr = root;
        //iterate over all the characters of the input
        for(int i=0; i<prefix.length(); i++) {
            char c = prefix.charAt(i);

            //not found in the trie, no search result, return empty list
            if(!curr.children.containsKey(c))
                return new ArrayList();

            //move curr to child node
            curr = curr.children.get(c);
        }

        //at the last char, return that node's top 3 list
        return curr.list;
    }

    HashMap<String, Integer> map;
    StringBuilder input;
    TrieNode root;

    public AutocompleteSystem(String[] sentences, int[] times) {

        map = new HashMap();
        input = new StringBuilder();
        root = new TrieNode();

        //put all sentences in map against their counts and in the trie also
        for(int i=0; i<sentences.length; i++) {
            map.put(sentences[i], map.getOrDefault((sentences[i]),0) + times[i]);
            insert(sentences[i]);
        }
    }

    public List<String> input(char c) {

        //store inout till now in system(map) and return empty list
        if(c == '#') {
            String in = input.toString();
            map.put(in, map.getOrDefault(in,0) + 1);
            insert(in);
            input = new StringBuilder();
            return new ArrayList<String>();
        }

        //append char to input till now and convert to string
        input.append(c);
        String in = input.toString();

        return search(in);
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
