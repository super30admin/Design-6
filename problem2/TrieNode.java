package problem2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrieNode {
	Map<String, Integer> countMap;
	Map<Character, TrieNode> children;
	List<String> pqueue;

	public TrieNode() {
		countMap = new HashMap<>();
		children = new HashMap<>();
		pqueue = new ArrayList<String>();
	}
}
