//Time Complexity: O(n^2)
//Space Complexity: O(n)
class AutocompleteSystem {
    HashMap<String, Integer> database = new HashMap<>();
    String input_char = "";

    public AutocompleteSystem(String[] sentences, int[] times) {
        for(int i = 0; i < sentences.length; i++) {
            database.put(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        if(c == '#') {
            if(input_char != "") {
                int count = database.getOrDefault(input_char, 0) + 1;
                database.put(input_char, count);
            }
            input_char = "";
            return new ArrayList<>();
        }

        input_char += c;
        PriorityQueue<Pair<String, Integer>> pq = new PriorityQueue<>(new Comparator<Pair<String, Integer>>(){
            @Override
            public int compare(Pair<String, Integer> p1, Pair<String, Integer> p2) {
                if(p1.getValue() != p2.getValue()) {
                    return p2.getValue() - p1.getValue();
                }
                return p1.getKey().compareTo(p2.getKey());
            }
        });

        for(String sent: database.keySet()) {
            if(sent.startsWith(input_char)) {
                pq.add(new Pair(sent, database.get(sent)));
            }
        }

        List<String> res = new ArrayList<>();
        while(!pq.isEmpty() && res.size() < 3) {
            res.add(pq.poll().getKey());
        }
        return res;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */