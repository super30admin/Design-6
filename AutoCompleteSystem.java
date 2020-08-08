
//TC - O(N) (Not sure)
//SC - O(N) (Not sure)
import java.util.*;

class AutocompleteSystem {
	HashMap<String, Integer> map;
	String input;

	public AutocompleteSystem(String[] sentences, int[] times) {
		map = new HashMap<>();
		input = "";
		for (int i = 0; i < sentences.length; i++) {
			map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
		}
	}

	public List<String> input(char c) {
		if (c == '#') {
			map.put(input, map.getOrDefault(input, 0) + 1);
			input = "";
			return new ArrayList<>();
		}
		PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
			if (a.count == b.count) {
				return b.sentence.compareTo(a.sentence);
			} else {
				return a.count - b.count;
			}
		});
		input += c;
		for (String key : map.keySet()) {
			if (key.startsWith(input)) {
				pq.add(new Pair(key, map.get(key)));
				if (pq.size() > 3) {
					pq.poll();
				}
			}
		}
		List<String> result = new ArrayList<>();
		while (!pq.isEmpty()) {
			result.add(0, pq.poll().sentence);
		}
		return result;
	}

	class Pair {
		String sentence;
		int count;

		public Pair(String sentence, int count) {
			this.sentence = sentence;
			this.count = count;
		}
	}
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */