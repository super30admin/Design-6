/**
 * intuition: Each sentence and its hot count are stored int he hashmap and then each input is checked and traversed throught he hashmap to get the matching string and top 3 strings are returned
 * If the char is # it means the end of the string, then the string prefix is added to the hashmap andthe empty arraylsit is returned.
 * Time: O(n^2log3) ~ O(n^2) where n - length of the hashmap also Java function startswith takes some time as well. that has to be counted as well - n^2*k where k-lengh of the longest prefix string
 * Space: O(n) length of the hashmap
 */
class AutocompleteSystem {
    HashMap<String,Integer> hotcnt;
    StringBuilder pre;
    public AutocompleteSystem(String[] sentences, int[] times) {
        hotcnt = new HashMap<>();
        for(int i=0;i<sentences.length;i++){
            hotcnt.put(sentences[i],times[i]);
        }
        pre = new StringBuilder();
        // for(Map.Entry<String,Integer> e: hotcnt.entrySet())
        //     System.out.println(e.getKey()+" "+e.getValue());
    }

    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if(c == '#'){
            hotcnt.put(pre.toString(),hotcnt.getOrDefault(pre.toString(),0)+1);
            pre = new StringBuilder();
            return res;
        }else{
            pre.append(c);

            PriorityQueue<Map.Entry<String,Integer>> pq = new PriorityQueue<>(
                    (a,b)->
                    { return a.getValue() == b.getValue() ? b.getKey().compareTo(a.getKey()) :
                            a.getValue()-b.getValue(); }
            );

            for(Map.Entry<String,Integer> e: hotcnt.entrySet()){

                if(e.getKey().startsWith(pre.toString())){
                    pq.add(e);
                    if(pq.size() > 3) pq.poll();
                }
            }

            while(!pq.isEmpty()){
                res.add(0,pq.poll().getKey());
            }
            return res;
        }
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */