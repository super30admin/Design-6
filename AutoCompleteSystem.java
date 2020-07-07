//time o(max(n,m)), m - length of a string, n - sentences array length
//space o(1), map space is compensated as it is same as the input array size.
//using min heap
class AutocompleteSystem {

    String input = "";
    HashMap<String, Integer> map;
    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        for(int i=0; i<sentences.length; i++) {
            map.put(sentences[i], times[i]);
        }
    }
    
    public List<String> input(char c) {
        if(c == '#') {
            map.put(input, map.getOrDefault(input, 0) + 1);
            input="";
            return new ArrayList<>();
        }
        input = input + c;
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
            if(a.count == b.count) {
                return b.sentence.compareTo(a.sentence);
            }
            return a.count - b.count;
        });
        
        for(String s: map.keySet()) {
            if(s.startsWith(input)) {
                if(pq.size() < 3) {
                    pq.add(new Pair(s, map.get(s)));
                } else {
                    int incount = map.get(s);
                    if(incount == pq.peek().count) {
                        if(pq.peek().sentence.compareTo(s) > 0) {
                            pq.poll();
                            pq.add(new Pair(s, map.get(s)));
                        }
                    }
                    else if(incount > pq.peek().count) {
                        pq.poll();
                        pq.add(new Pair(s, map.get(s)));
                    }
                }
            }
        }
        
        List<String> res = new ArrayList<>();
        
        while(!pq.isEmpty()) {
            res.add(0, pq.poll().sentence);
        }
        return res;
    }
    
    private class Pair {
        String sentence;
        int count;
        public Pair(String sentence, int count) {
            this.sentence = sentence;
            this.count = count;
        }
    }
}