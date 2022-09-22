import java.util.ArrayList;
import java.util.List;

// Time Complexity : O(1)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
//642. Design Search Autocomplete System (Hard) - https://leetcode.com/problems/design-search-autocomplete-system/
//class AutocompleteSystem {
//
//    private HashMap<String, Integer> map;
//    private StringBuilder input;
//    
//    public AutocompleteSystem(String[] sentences, int[] times) {
//        this.map = new HashMap<>();
//        this.input = new StringBuilder();
//        for (int i = 0; i < sentences.length; i++) {
//            String sentence = sentences[i];
//            map.put(sentence, map.getOrDefault(sentence, 0) + times[i]);
//        }
//    }
//    
//    // Time Complexity : O(n) where n = length of sentences array
//    // Space Complexity : O(n) where n = length of sentences array
//    public List<String> input(char c) { // O(n)
//        if (c == '#') {
//            String searchString = input.toString();
//            map.put(searchString, map.getOrDefault(searchString, 0) + 1);
//            input = new StringBuilder();
//            return new ArrayList<>();
//        }
//        
//        input.append(c);
//        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
//            int ca = map.get(a);
//            int cb = map.get(b);
//            
//            if (ca == cb) {
//                return b.compareTo(a);
//            }
//            
//            return map.get(a) - map.get(b);
//        });
//        
//        String prefix = input.toString();
//        for (String key : map.keySet()) { // O(n) where n = length of sentences array
//            if (key.startsWith(prefix)) {
//                pq.add(key);
//                
//                if (pq.size() > 3) {
//                    pq.poll();
//                }
//            }
//        }
//        
//        List<String> result = new ArrayList<>();
//        
//        while (!pq.isEmpty()) {
//            result.add(0, pq.poll());
//        }
//        
//        return result;
//    }
//}

//class AutocompleteSystem {
//
//    class TrieNode {
//        TrieNode[] children;
//        List<String> list;
//        
//        public TrieNode() {
//            this.children = new TrieNode[256];
//            this.list = new ArrayList<>();
//        }
//    }
//    
//    private void insert(String sentence) {
//        TrieNode curr = root;
//        
//        for (int i = 0; i < sentence.length(); i++) {
//            char ch = sentence.charAt(i);
//            
//            if (curr.children[ch - ' '] == null) {
//                curr.children[ch - ' '] = new TrieNode();
//            }
//            
//            curr = curr.children[ch - ' '];
//            curr.list.add(sentence);
//        }
//    }
//    
//    private List<String> search(String prefix) {
//        TrieNode curr = root;
//        
//        for (int i = 0; i < prefix.length(); i++) {
//            char ch = prefix.charAt(i);
//            
//            if (curr.children[ch - ' '] == null) {
//                return new ArrayList<>();
//            }
//            
//            curr = curr.children[ch - ' '];
//        }
//        
//        return curr.list;
//    }
//    
//    private HashMap<String, Integer> map;
//    private StringBuilder input;
//    private TrieNode root;
//    
//    public AutocompleteSystem(String[] sentences, int[] times) {
//        this.map = new HashMap<>();
//        this.root = new TrieNode();
//        this.input = new StringBuilder();
//        for (int i = 0; i < sentences.length; i++) {
//            String sentence = sentences[i];
//            
//            if (!map.containsKey(sentence)) {
//                insert(sentence);
//            }
//            
//            map.put(sentence, map.getOrDefault(sentence, 0) + times[i]);
//        }
//    }
//    
//    public List<String> input(char c) { // O(k)
//        if (c == '#') {
//            String searchString = input.toString();
//            
//            if (!map.containsKey(searchString)) {
//                insert(searchString);
//            }
//            
//            map.put(searchString, map.getOrDefault(searchString, 0) + 1);
//            input = new StringBuilder();
//            return new ArrayList<>();
//        }
//        
//        input.append(c);
//        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
//            int ca = map.get(a);
//            int cb = map.get(b);
//            
//            if (ca == cb) {
//                return b.compareTo(a);
//            }
//            
//            return map.get(a) - map.get(b);
//        });
//        
//        String prefix = input.toString();
//        List<String> prefixList = search(prefix);
//        
//        for (String str : prefixList) { // O(k) where k = sentences starting with prefix
//            if (str.startsWith(prefix)) {
//                pq.add(str);
//
//                if (pq.size() > 3) {
//                    pq.poll();
//                }
//            }
//        }
//        
//        List<String> result = new ArrayList<>();
//        
//        while (!pq.isEmpty()) {
//            result.add(0, pq.poll());
//        }
//        
//        return result;
//    }
//}

//class AutocompleteSystem {
//
//	class TrieNode {
//		TrieNode[] children;
//		List<String> list;
//
//		public TrieNode() {
//			this.children = new TrieNode[256];
//			this.list = new ArrayList<>();
//		}
//	}
//
//	private void insert(String sentence) {
//		TrieNode curr = root;
//
//		for (int i = 0; i < sentence.length(); i++) {
//			char ch = sentence.charAt(i);
//
//			if (curr.children[ch - ' '] == null) {
//				curr.children[ch - ' '] = new TrieNode();
//			}
//
//			curr = curr.children[ch - ' '];
//
//			List<String> li = curr.list;
//
//			if (!li.contains(sentence)) {
//				li.add(sentence);
//			}
//
//			Collections.sort(li, (a, b) -> {
//				int ca = map.get(a);
//				int cb = map.get(b);
//
//				if (ca == cb) {
//					return a.compareTo(b);
//				}
//
//				return cb - ca;
//			});
//
//			if (li.size() > 3) {
//				li.remove(li.size() - 1);
//			}
//		}
//	}
//
//	private List<String> search(String prefix) {
//		TrieNode curr = root;
//
//		for (int i = 0; i < prefix.length(); i++) {
//			char ch = prefix.charAt(i);
//
//			if (curr.children[ch - ' '] == null) {
//				return new ArrayList<>();
//			}
//
//			curr = curr.children[ch - ' '];
//		}
//
//		return curr.list;
//	}
//
//	private HashMap<String, Integer> map;
//	private StringBuilder input;
//	private TrieNode root;
//
//	public AutocompleteSystem(String[] sentences, int[] times) {
//		this.map = new HashMap<>();
//		this.root = new TrieNode();
//		this.input = new StringBuilder();
//		for (int i = 0; i < sentences.length; i++) {
//			String sentence = sentences[i];
//			map.put(sentence, map.getOrDefault(sentence, 0) + times[i]);
//			insert(sentence);
//		}
//	}
//
//	public List<String> input(char c) {
//		if (c == '#') {
//			String searchString = input.toString();
//
//			map.put(searchString, map.getOrDefault(searchString, 0) + 1);
//			insert(searchString);
//			input = new StringBuilder();
//			return new ArrayList<>();
//		}
//
//		input.append(c);
//
//		String prefix = input.toString();
//		return search(prefix);
//	}
//}

class AutocompleteSystem {

    class TrieNode {
        Character ch;
        HashMap<Character, TrieNode> children;
        List<String> list;
        
        public TrieNode() {
            this.children = new HashMap<>();
            this.list = new ArrayList<>();
        }
    }
    
    private void insert(String sentence) {
        TrieNode curr = root;
        
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new TrieNode());
            }
            
            curr = curr.children.get(c);
            curr.ch = c;
            
            List<String> li = curr.list;
            
            if (!li.contains(sentence)) {
                li.add(sentence);
            }
            
            Collections.sort(li, (a, b) -> {
                int ca = map.get(a);
                int cb = map.get(b);
                
                if (ca == cb) {
                    return a.compareTo(b);
                }
                
                return cb - ca;
            });
            
            if (li.size() > 3) {
                li.remove(li.size()-1);
            }
        }
    }
    
    private List<String> search(String prefix) {
        TrieNode curr = root;
        
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            
            if (!curr.children.containsKey(ch)) {
                return new ArrayList<>();
            }
            
            curr = curr.children.get(ch);
        }
        
        return curr.list;
    }
    
    private HashMap<String, Integer> map;
    private StringBuilder input;
    private TrieNode root;
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        this.map = new HashMap<>();
        this.root = new TrieNode();
        this.input = new StringBuilder();
        for (int i = 0; i < sentences.length; i++) {
            String sentence = sentences[i];
            map.put(sentence, map.getOrDefault(sentence, 0) + times[i]);
            insert(sentence);
        }
    }
    
    public List<String> input(char c) { // O(1)
        if (c == '#') {
            String searchString = input.toString();
            
            map.put(searchString, map.getOrDefault(searchString, 0) + 1);
            insert(searchString);
            input = new StringBuilder();
            return new ArrayList<>();
        }
        
        input.append(c);
        
        String prefix = input.toString();
        return search(prefix);
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */