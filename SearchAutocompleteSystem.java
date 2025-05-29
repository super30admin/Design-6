class AutocompleteSystem {
    HashMap<String, Integer> map;
    String search;

    class TrieNode {
        TrieNode[] children;
        List<String> startsWith;

        public TrieNode() {
            this.children = new TrieNode[256];
            this.startsWith = new ArrayList<>();
        }
    }

    private void insert(String word) {
        TrieNode curr = root;
        for(char c: word.toCharArray()) {
            if(curr.children[c - ' '] == null) {
                curr.children[c - ' '] = new TrieNode();
            }
            curr = curr.children[c - ' '];
            curr.startsWith.add(word);
        }
    }

    private List<String> searchPrefix(String prefix) {
        TrieNode curr = root;
        for(char c: prefix.toCharArray()) {
            if(curr.children[c - ' ']000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000 
            /)
        }
    }

    public AutocompleteSystem(String[] sentences, int[] times) {
        this.search = "";
        this.map = new HashMap<>();;
        for(int i=0; i<sentences.length; i++) {
            map.put(sentences[i], map.getOrDefault(sentences[i], 0)+ times[i]);
        }
        
    }
    
    public List<String> input(char c) {
        if(c == '0') {
            map.put(search, map.getOrDefault(search, 0)+1);
            search = "";
            return new ArrayList<>();
        }

        search += c;

        List<String> matches = new ArrayList<>();

        for(String sentence: map.keySet()) {
            if(sentence.startsWith(search)) {
                matches.add(sentence);
            }
        }
        Collections.sort(matches, (a,b) => {
            if(map.get(a) == map.get(b)) {
                return a.compareTo(b);
            }
            return map.get(b) - map.get(a);
        });

        List<String> result = new ArrayList<>();

        for(int i=0; i<3 && i<matches.size(); i++) {
            result.add(matches.get(i));
        }

        return result;
    }
}