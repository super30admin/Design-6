class AutocompleteSystem {
    private HashMap<String, Integer> map;
    private StringBuilder sb;
    private TrieNode root;
    private TrieNode current;

    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        sb = new StringBuilder();
        root = new TrieNode();
        current = root;
        
        for (int i = 0; i < sentences.length; i++) {
            map.put(sentences[i], times[i]);
            root.add(sentences[i], 0);
        }
    }
    
    public List<String> input(char c) {
        if (c == '#') {
            String str = sb.toString();
            map.put(str, map.getOrDefault(str, 0) + 1);
            root.add(str, 0);
            sb = new StringBuilder();
            current = root;
            return new ArrayList<>();
        }
        sb.append(c);
        current = current.getNext(c);
        return current.top3;
    }
    
    private class TrieNode {
        private List<String> top3;
        private TrieNode[] next;
        
        private TrieNode() {
            this.top3 = new ArrayList<>();
            this.next = new TrieNode[27];
        }
        
        private void add(String s, int start) {
            if (!top3.contains(s)) top3.add(s);
            Collections.sort(top3, (a, b) -> {
                if (map.get(a) == map.get(b)) return a.compareTo(b);
                return map.get(b) - map.get(a);
            });
            if (top3.size() > 3) top3.remove(top3.size() - 1);
            if (start == s.length()) return;
            getNext(s.charAt(start)).add(s, start + 1);
        }
        
        private TrieNode getNext(char ch) {
            int index = ch == ' ' ? 26 : ch - 'a';
            if (next[index] == null) next[index] = new TrieNode();
            return next[index];
        }
    }
}
