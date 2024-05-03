
/*
 * class AutocompleteSystem {
 * HashMap<String, Integer> map;
 * StringBuilder sb;
 * 
 * public AutocompleteSystem(String[] sentences, int[] times) {
 * // Tc: O(n) Sc: O(n)
 * map = new HashMap<>();
 * sb = new StringBuilder();
 * for (int i = 0; i < sentences.length; i++) {
 * map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
 * }
 * 
 * }
 * 
 * public List<String> input(char c) {
 * // Tc: O(k*mlogm) k = map size as we iterate over the map. Adding elements to
 * // priority queue takes O(logm) time.
 * // Sc: O(m) m = priority Queue size (3)
 * // = 3)
 * if (c == '#') {
 * String newSearch = sb.toString();
 * map.put(newSearch, 1);
 * sb = new StringBuilder();
 * return new ArrayList<>();
 * }
 * sb.append(c);
 * PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
 * int ca = map.get(a);
 * int cb = map.get(b);
 * // If both the frequencies are equal:
 * // Descending alphabetical order
 * if (ca == cb) {
 * return b.compareTo(a);
 * }
 * return ca - cb; // Ascending order of frequencies
 * });
 * String search = sb.toString();
 * for (String key : map.keySet()) {
 * if (key.startsWith(search)) {
 * pq.add(key);
 * if (pq.size() > 3)
 * pq.poll();
 * }
 * }
 * List<String> res = new ArrayList<>();
 * while (!pq.isEmpty()) {
 * res.add(0, pq.poll());
 * }
 * return res;
 * 
 * }
 * }
 */
/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */

/*
 * class TrieNode {
 * Map<Character, TrieNode> children;
 * Map<String, Integer> sentences;
 * public TrieNode() {
 * children = new HashMap<>();
 * sentences = new HashMap<>();
 * }
 * }
 * 
 * class AutocompleteSystem {
 * TrieNode root;
 * TrieNode currNode;
 * TrieNode dead;
 * StringBuilder currSentence;
 * 
 * public AutocompleteSystem(String[] sentences, int[] times) {
 * root = new TrieNode();
 * for (int i = 0; i < sentences.length; i++) {
 * addToTrie(sentences[i], times[i]);
 * }
 * 
 * currSentence = new StringBuilder();
 * currNode = root;
 * dead = new TrieNode();
 * }
 * 
 * public List<String> input(char c) {
 * if (c == '#') {
 * addToTrie(currSentence.toString(), 1);
 * currSentence.setLength(0);
 * currNode = root;
 * return new ArrayList<String>();
 * }
 * 
 * currSentence.append(c);
 * if (!currNode.children.containsKey(c)) {
 * currNode = dead;
 * return new ArrayList<String>();
 * }
 * 
 * currNode = currNode.children.get(c);
 * PriorityQueue<String> heap = new PriorityQueue<>((a, b) -> {
 * int hotA = currNode.sentences.get(a);
 * int hotB = currNode.sentences.get(b);
 * if (hotA == hotB) {
 * return b.compareTo(a);
 * }
 * 
 * return hotA - hotB;
 * });
 * 
 * for (String sentence: currNode.sentences.keySet()) {
 * heap.add(sentence);
 * if (heap.size() > 3) {
 * heap.remove();
 * }
 * }
 * 
 * List<String> ans = new ArrayList<>();
 * while (!heap.isEmpty()) {
 * ans.add(heap.remove());
 * }
 * 
 * Collections.reverse(ans);
 * return ans;
 * }
 * 
 * private void addToTrie(String sentence, int count) {
 * TrieNode node = root;
 * for (char c: sentence.toCharArray()) {
 * if (!node.children.containsKey(c)) {
 * node.children.put(c, new TrieNode());
 * }
 * 
 * node = node.children.get(c);
 * node.sentences.put(sentence, node.sentences.getOrDefault(sentence, 0) +
 * count);
 * }
 * }
 * }
 * /**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
