import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class AutocompleteSystem {
	class Node {
		Map<String, Integer> times = new HashMap<>();
		Map<Character, Node> children = new HashMap<>();
	}

	Node root = new Node();
	String prefix = "";

	/**
	 * TC: O(N) SC: O(N)
	 */
	public void add(String s, Integer time) {
		Node cur = root;
		for (char c : s.toCharArray()) {
			if (!cur.children.containsKey(c))
				cur.children.put(c, new Node());
			cur = cur.children.get(c);
			cur.times.put(s, cur.times.getOrDefault(s, 0) + time);
		}
	}

	public AutocompleteSystem(String[] sentences, int[] times) {
		for (int i = 0; i < sentences.length; i++) {
			add(sentences[i], times[i]);
		}
	}

	/**
	 * TC: O(N) SC: O(N)
	 */
	public List<String> input(char c) {
		if (c == '#') {
			add(prefix, 1);
			prefix = "";
			return new ArrayList<>();
		}

		prefix += c;
		Node cur = root;
		for (char cc : prefix.toCharArray()) {
			if (!cur.children.containsKey(cc))
				return new ArrayList<>();
			cur = cur.children.get(cc);
		}

		PriorityQueue<Map.Entry<String, Integer>> heap = new PriorityQueue<>((a,
				b) -> a.getValue() == b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue() - b.getValue());

		for (Map.Entry<String, Integer> entry : cur.times.entrySet()) {
			heap.offer(entry);
			if (heap.size() > 3)
				heap.poll();
		}

		List<String> res = new ArrayList<>();
		while (!heap.isEmpty())
			res.add(0, heap.poll().getKey());

		return res;
	}
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
