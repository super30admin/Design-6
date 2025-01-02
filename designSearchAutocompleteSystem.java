// Time complexity = O(n)
// Space complexity = O(n)

class AutocompleteSystem {
  Map<String, Integer> map;
  StringBuilder sb;
  public AutocompleteSystem(String[] sentences, int[] times) {
    map = new HashMap<>();
    sb = new StringBuilder();
    for(int i = 0; i< sentences.length; i++) {
      map.put(sentences[i], map.getOrDefault(sentences[i], 0)+times[i]);
    }
  }
  public List<String> input(char c) {
    if(c == '#') {
      String sentence = sb.toString();
      sb = new StringBuilder();
      map.put(sentence, map.getOrDefault(sentence, 0)+1);
      return new ArrayList<>();
    }
    sb.append(c);
    PriorityQueue<String> pq = new PriorityQueue<>((a,b) -> {
      if(map.get(a) == map.get(b)) {
        // Lexographical order
        b.compareTo(a);
      }
      // Frequency order
      return map.get(a) - map.get(b);
    });
    for(String key: map.keySet()) {
      if(key.startsWith(sb.toString())) {
        pq.add(key);
        // Keeping only top 3
        if(pq.size() > 3) pq.poll();
      }
    }
    List<String> result = new ArrayList<>();
    // Retrieving result in lexographical order
    if(!pq.isEmpty()) result.add(0, pq.poll());
    return result;
  }
}