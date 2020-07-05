#Time Complexity : O(n + m) where m is number of sentences and n is number of new inputs to maintain (for the input() method)
        String s;
        int count;
        Pair(String se, int c){
            this.s = se;
            this.count = c;
        }
        public String getSentence(){return this.s;}
        public int getCount(){return this.count;}
    }
    HashMap<String, Integer> store;
    public AutocompleteSystem(String[] sentences, int[] times) {
        store = new HashMap<>();
        for(int i =0; i < sentences.length; i++){
            store.put(sentences[i], times[i]);
        }
    }
    String search ="";
    public List<String> input(char c) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)-> {
                if(a.count == b.count){
                    return b.s.compareTo(a.s);
                }
            return a.count - b.count;});
        List<String> result = new ArrayList<>();
        if(c == '#'){
            store.put(search, store.getOrDefault(search, 0) + 1);
            search = "";
            return result;}
        else{
        search += c; 
        for(String s: store.keySet()){
            if(s.startsWith(search)){
            if(pq.size() < 3){
                pq.add(new Pair(s, store.get(s)));
            } else {
                if(pq.peek().getCount() == store.get(s)){
                    if(pq.peek().getSentence().compareTo(s) > 0){
                        pq.poll();
                        pq.add(new Pair(s, store.get(s)));
                    }
                } else
                if(pq.peek().getCount() < store.get(s)){
                    pq.poll();
                    pq.add(new Pair(s, store.get(s)));
                }
            }
        }
        }
        while(!pq.isEmpty()){
            Pair p = pq.poll();
            result.add(0, p.getSentence());
        }
    }
        return result;
    }
}