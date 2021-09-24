//https://leetcode.com/problems/design-search-autocomplete-system/
/*
Time: O(1)
Space: O(n) where n is the total number of input words
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : None
*/
class SearchAutoCompleteSystem {

    TrieNode root;
    TrieNode cur;
    StringBuilder sb; // to record the chars we inputted so far

    // --Start of TriNode class--

    // Has compareTo and update methods
    class TrieNode implements Comparable<TrieNode> {
        TrieNode[] children; // usually 26, but 128 size because of ascii
        String string; // "island" etc
        int times; // 5,3,2,2 //only in the last character
        List<TrieNode> hot; // when we input a char, we also want it's hot list

        // constructor
        public TrieNode() {
            children = new TrieNode[128]; // ascii
            string = null;
            times = 0;
            hot = new ArrayList<>();
        }

        // to sort TrieNode by their hotkey and their ascii code
        public int compareTo(TrieNode existingTrieNode) {
            if (this.times == existingTrieNode.times) {
                return this.string.compareTo(existingTrieNode.string);
                // compare this.string with existing.string
            }

            return existingTrieNode.times - this.times;
        }

        // In some cases, the hotlist will exceed size 3, so we need to renew(update)
        // the list
        public void update(TrieNode node) {
            if (!this.hot.contains(node)) {
                this.hot.add(node);
            }

            // The returned top 3 hot sentences should be sorted by hot degree
            Collections.sort(hot);

            if (hot.size() > 3) {
                hot.remove(hot.size() - 1);
            }
        }
    }
    // --End of TriNode class--

    // -AutoComplete class start
   public AutocompleteSystem(String[] sentences, int[] times) 
   {
       root = new TrieNode();
       cur = root;
       sb = new StringBuilder();

       //For every sentence, we insert each sentence to the Trie nodes
       for (int i = 0; i < times.length; i++) 
       {
           insert(sentences[i], times[i]);
       }
   }
    // -AutoComplete class end

    public void insert(String sentence, int t) {
        TrieNode tmp = root;

        List<TrieNode> visited = new ArrayList<>();

        // We insert every char in the sentence
        for (char c : sentence.toCharArray()) {
            if (tmp.children[c] == null) {
                tmp.children[c] = new TrieNode();
            }

            tmp = tmp.children[c];
            visited.add(tmp);
        }

        // tmp is the last char in the sentence
        tmp.string = sentence;
        tmp.times = tmp.times + t;

        // For every TrieNode in visited array
        for (TrieNode node : visited) {
            node.update(tmp);
        }
    }

    // This indicates that the user typed the character c
   public List<String> input(char c) 
   {
       List<String> res = new ArrayList<>();
       if (c == '#') 
       { // it means the sentence ends
           insert(sb.toString(), 1); //stores the inputted sentence in the system.
           sb = new StringBuilder();
           cur = root;
           return res; //Returns an empty ArrayList
       }
       
       //Otherwise append the char to sb
       sb.append(c);
       
       if (cur != null) 
       {
           cur = cur.children[c];
       }

       //we cannot find the ending matched sentence
       if (cur == null) 
           return res;
       
       for (TrieNode node : cur.hot) 
       {
           res.add(node.string);
       }

       return res;
   }
