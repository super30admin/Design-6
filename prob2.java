// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes

class AutocompleteSystem {
    class TrieNode {
       private TrieNode[] children;
       private ArrayList<String> hotlist;
        
       public TrieNode() {
           children = new TrieNode[27];
           hotlist = new ArrayList<String>();
       }
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
        private boolean greater(String a, String b) {
            int cntA = count.get(a);
            int cntB = count.get(b);
            if(cntA != cntB) {
                return cntA > cntB;
            }
            return a.compareTo(b) < 1 ? true : false;
        }
    }
    private TrieNode root;
    private TrieNode curr;
    private StringBuilder sb;
    private HashMap<String, Integer> count;
    
    public AutocompleteSystem(String[] sentences, int[] times) {
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

