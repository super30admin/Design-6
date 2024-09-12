import java.util.*;

//Time Complexity : O(N LOGN)
//Space Complexity : O(N LOGN)
//Did this code successfully run on Leetcode : Yes
//Any problem you faced while coding this : No


class Pair {
	String sentence;
	int count;

	public Pair(String sentence, int count) {
		this.sentence = sentence;
		this.count = count;
	}

	@Override
	public String toString() {
		return "Pair [sentence=" + sentence + ", count=" + count + "]";
	}

}

public class AutoCompleteSystem {
	Map<String, Integer> map;
	static int topSentences = 3;
	PriorityQueue<Pair> pq;
	String[] sentences;
	StringBuilder str;
	int[] times;

	public AutoCompleteSystem(String[] sentences, int[] times) {
		this.sentences = sentences;
		this.times = times;
		str = new StringBuilder();
		map = new HashMap<>();

		pq = new PriorityQueue<>(topSentences, new Comparator<Pair>() {
			@Override
			public int compare(Pair pair1, Pair pair2) {
				int count1 = pair1.count;
				int count2 = pair2.count;
				if (count1 == count2) {
					return pair1.sentence.compareTo(pair2.sentence);
				}
				return count2 - count1;
			}
		});

		this.addToMap();
	}

	private void addToMap() {
		int size = sentences.length;
		for (int i = 0; i < size; i++) {
			map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
		}
	}

	public List<String> input(char c) {
		if (c == '#') {
			str.setLength(0);
			map.put(str.toString(), map.getOrDefault(str.toString(), 0) + 1);
			return new ArrayList<>();
		} else {
			List<String> output = new ArrayList<>();
			str.append(c);
			for (Map.Entry<String, Integer> entry : map.entrySet()) {
				if (entry.getKey().startsWith(str.toString())) {
						pq.add(new Pair(entry.getKey(), entry.getValue())); 
				}
			}
			while (output	.size() < 3 && !pq.isEmpty()) {
				Pair pair = pq.poll();
				if (pair != null) {
					output.add(pair.sentence);
				}
			}
			return output;
		}
	}

	public static void main(String args[]) {
		String sentences[] = { "i love you", "island", "ironman", "i love leetcode" };
		int times[] = { 5, 3, 2, 2 };
		AutoCompleteSystem system = new AutoCompleteSystem(sentences, times);

		System.out.println(system.input('i'));

		System.out.println(system.input(' '));
		System.out.println(system.input('a'));
		System.out.println(system.input('#'));

	}
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */