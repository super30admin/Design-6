'''
Was coded in java because custom heap is not available in Python
'''

class AutocompleteSystem {
    HashMap<String, Integer> sentences_map;
    String input = "";
    private class Pair {
        String sentence;
        int count;
        public Pair(String s, int c) {
            sentence = s;
            count = c;
        }
    }
    public AutocompleteSystem(String[] sentences, int[] times) {
        sentences_map = new HashMap<>();
        for(int i = 0; i < sentences.length; i++){
            sentences_map.put(sentences[i], sentences_map.getOrDefault(sentences[i], 0) + times[i]);
        }
    }
    public List<String> input(char c) {
        if(c =='#'){
            sentences_map.put(input, sentences_map.getOrDefault(input, 0) + 1);
            input = "";
            return new ArrayList<>();
        }
        input += c;
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
            if(a.count == b.count){
                return b.sentence.compareTo(a.sentence);
            } else {
                return a.count - b.count;
            }
        });
        for (String s : sentences_map.keySet()){
            if (s.startsWith(input)){
                pq.add(new Pair(s, sentences_map.get(s)));
                if(pq.size() > 3){
                    pq.poll();
                }
            }
        }
        List<String> result = new ArrayList<>();
        while(!pq.isEmpty()) {
            Pair p = pq.poll();
            result.add(0, p.sentence);
        }
        return result;
    }
}
