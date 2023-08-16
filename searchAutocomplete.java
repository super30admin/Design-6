class AutocompleteSystem {
    private TrieNode root;
    private StringBuilder currentInput;

    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        currentInput = new StringBuilder();
        
        for (int i = 0; i < sentences.length; i++) {
            addSentence(sentences[i], times[i]);
        }
    }
    
    public List<String> input(char c) {
        if (c == '#') {
            String sentence = currentInput.toString();
            addSentence(sentence, 1);
            currentInput = new StringBuilder();
            return new ArrayList<>();
        }
        
        currentInput.append(c);
        TrieNode node = searchNode(currentInput.toString());
        if (node == null) {
            return new ArrayList<>();
        }
        
        List<SentenceWithCount> suggestions = new ArrayList<>();
        getAllSentences(node, currentInput.toString(), suggestions);
        
        Collections.sort(suggestions, (a, b) -> a.count != b.count ? b.count - a.count : a.sentence.compareTo(b.sentence));
        
        List<String> result = new ArrayList<>();
        for (int i = 0; i < Math.min(3, suggestions.size()); i++) {
            result.add(suggestions.get(i).sentence);
        }
        
        return result;
    }
    
    private void addSentence(String sentence, int times) {
        TrieNode node = root;
        for (char c : sentence.toCharArray()) {
            if (node.children[c] == null) {
                node.children[c] = new TrieNode();
            }
            node = node.children[c];
        }
        node.isEndOfSentence = true;
        node.count += times;
    }
    
    private TrieNode searchNode(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (node.children[c] == null) {
                return null;
            }
            node = node.children[c];
        }
        return node;
    }
    
    private void getAllSentences(TrieNode node, String prefix, List<SentenceWithCount> suggestions) {
        if (node.isEndOfSentence) {
            suggestions.add(new SentenceWithCount(prefix, node.count));
        }
        
        for (char c = 'a'; c <= 'z'; c++) {
            if (node.children[c] != null) {
                getAllSentences(node.children[c], prefix + c, suggestions);
            }
        }
    }
}

class TrieNode {
    TrieNode[] children;
    boolean isEndOfSentence;
    int count;
    
    public TrieNode() {
        children = new TrieNode[128]; // ASCII characters
        isEndOfSentence = false;
        count = 0;
    }
}

class SentenceWithCount {
    String sentence;
    int count;
    
    public SentenceWithCount(String sentence, int count) {
        this.sentence = sentence;
        this.count = count;
    }
}

