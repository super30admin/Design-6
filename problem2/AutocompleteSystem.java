// Time Complexity : O(1)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
package problem2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class AutocompleteSystem {
	Map<String, Integer> countMap;
	StringBuilder ip;
	TrieNode root;

	/********************* Hashmap + PriorityQueue *********************/
	// Time Complexity : O(n), n -> Number of sentences
	// Space Complexity : O(n)
	// Did this code successfully run on Leetcode : Yes
	// Any problem you faced while coding this : No
	/*
	 * public AutocompleteSystem(String[] sentences, int[] times) { countMap = new
	 * HashMap<String, Integer>(); ip = new StringBuilder();
	 * 
	 * for (int i = 0; i < sentences.length; i++) { countMap.put(sentences[i],
	 * countMap.getOrDefault(sentences[i], 0) + times[i]); } }
	 */

	public List<String> inputPQueue(char c) {
		if (c == '#') {
			String inputString = ip.toString();
			insert(inputString, 1);
			ip = new StringBuilder();
			return new ArrayList<String>();
		}

		ip.append(c);

		Map<String, Integer> countMap = searchHashMap(ip.toString());

		PriorityQueue<String> queue = new PriorityQueue<>((a, b) -> {
			if (countMap.get(a) == countMap.get(b)) {
				return b.compareTo(a);
			} else {
				return countMap.get(a) - countMap.get(b);
			}
		});

		for (String key : countMap.keySet()) {
			if (key.startsWith(ip.toString())) {
				queue.add(key);
				if (queue.size() > 3) {
					queue.poll();
				}
			}
		}

		List<String> result = new ArrayList<String>();
		while (!queue.isEmpty()) {
			result.add(0, queue.poll());
		}
		return result;
	}

	/********************* Trie + HashMap *********************/
	// Time Complexity : O(m), m -> Length of hashmap
	// Space Complexity : O(n*m), n -> Number of sentences
	// Did this code successfully run on Leetcode : Yes
	// Any problem you faced while coding this : No

	/*
	 * public AutocompleteSystem(String[] sentences, int[] times) { root = new
	 * TrieNode(); ip = new StringBuilder();
	 * 
	 * for (int i = 0; i < sentences.length; i++) { insert(sentences[i], times[i]);
	 * } }
	 */

	public List<String> inputTrieHashMap(char c) {
		if (c == '#') {
			String inputString = ip.toString();
			insertTrieHashMap(inputString, 1);
			ip = new StringBuilder();
			return new ArrayList<String>();
		}

		ip.append(c);

		Map<String, Integer> countMap = searchHashMap(ip.toString());

		PriorityQueue<String> queue = new PriorityQueue<>((a, b) -> {
			if (countMap.get(a) == countMap.get(b)) {
				return b.compareTo(a);
			} else {
				return countMap.get(a) - countMap.get(b);
			}
		});

		for (String key : countMap.keySet()) {
			if (key.startsWith(ip.toString())) {
				queue.add(key);
				if (queue.size() > 3) {
					queue.poll();
				}
			}
		}

		List<String> result = new ArrayList<String>();
		while (!queue.isEmpty()) {
			result.add(0, queue.poll());
		}
		return result;
	}

	private void insertTrieHashMap(String sentence, int times) {
		TrieNode curr = root;
		for (int i = 0; i < sentence.length(); i++) {
			char ch = sentence.charAt(i);
			if (!curr.children.containsKey(ch)) {
				curr.children.put(ch, new TrieNode());
			}
			curr = curr.children.get(ch);
			curr.countMap.put(sentence, curr.countMap.getOrDefault(sentence, 0) + times);
		}
	}

	private Map<String, Integer> searchHashMap(String prefix) {
		TrieNode curr = root;

		for (int i = 0; i < prefix.length(); i++) {
			char ch = prefix.charAt(i);
			if (!curr.children.containsKey(ch)) {
				return new HashMap<String, Integer>();
			}
			curr = curr.children.get(ch);
		}
		return curr.countMap;
	}

	/********************* Trie + PriorityQueue *********************/
	// Time Complexity : O(1)
	// Space Complexity : O(1)
	// Did this code successfully run on Leetcode : Yes
	// Any problem you faced while coding this : No

	public AutocompleteSystem(String[] sentences, int[] times) {
		root = new TrieNode();
		ip = new StringBuilder();
		countMap = new HashMap<>();

		for (int i = 0; i < sentences.length; i++) {
			countMap.put(sentences[i], countMap.getOrDefault(sentences[i], 0) + times[i]);
			insert(sentences[i], times[i]);
		}
	}

	public List<String> input(char c) {
		if (c == '#') {
			String inputString = ip.toString();
			countMap.put(inputString, countMap.getOrDefault(inputString, 0) + 1);
			insert(inputString, 1);
			ip = new StringBuilder();
			return new ArrayList<String>();
		}

		ip.append(c);

		return search(ip.toString());
	}

	private void insert(String sentence, int times) {
		TrieNode curr = root;
		for (int i = 0; i < sentence.length(); i++) {
			char ch = sentence.charAt(i);
			if (!curr.children.containsKey(ch)) {
				curr.children.put(ch, new TrieNode());
			}
			curr = curr.children.get(ch);

			List<String> temp = curr.pqueue;

			if (!temp.contains(sentence)) {
				temp.add(sentence);
			}

			Collections.sort(temp, (a, b) -> {
				if (countMap.get(a) == countMap.get(b)) {
					return a.compareTo(b);
				} else {
					return countMap.get(b) - countMap.get(a);
				}
			});

			if (temp.size() > 3) {
				temp.remove(temp.size() - 1);
			}
		}
	}

	private List<String> search(String prefix) {
		TrieNode curr = root;

		for (int i = 0; i < prefix.length(); i++) {
			char ch = prefix.charAt(i);
			if (!curr.children.containsKey(ch)) {
				return new ArrayList<String>();
			}
			curr = curr.children.get(ch);
		}
		return curr.pqueue;
	}

	private void print(List<String> sentences) {
		if (sentences.size() > 0) {
			for (int i = 0; i < sentences.size() - 1; i++) {
				System.out.print(sentences.get(i) + ", ");
			}
			System.out.print(sentences.get(sentences.size() - 1));
		}
		System.out.println();
	}

	public static void main(String[] args) {
		String[] sentences = { "i love you", "island", "iroman", "i love leetcode" };
		int[] times = { 5, 3, 2, 2 };
		AutocompleteSystem obj = new AutocompleteSystem(sentences, times);

		System.out.print("i: ");
		obj.print(obj.input('i'));
		System.out.print("Empty space: ");
		obj.print(obj.input(' '));
		System.out.print("a: ");
		obj.print(obj.input('a'));
		System.out.print("#: ");
		obj.print(obj.input('#'));
	}

}
