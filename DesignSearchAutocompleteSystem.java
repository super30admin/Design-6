// Time Complexity : O(NLogk), N = #sentences k = #hot results
// Space Complexity : O(N+k)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
public class DesignSearchAutocompleteSystem {
    class AutocompleteSystem {
        //HashMap with priority Queue
        private HashMap<String, Integer> map;
        private StringBuilder search;

        public AutocompleteSystem(String[] sentences, int[] times) {
            map = new HashMap<>();
            search = new StringBuilder();
            for(int i=0; i< sentences.length; i++) {
                map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
            }
        }

        public List<String> input(char c) {
            if(c == '#') {
                String srStr = search.toString();
                map.put(srStr, map.getOrDefault(srStr, 0) + 1);
                search = new StringBuilder();
                return new ArrayList<>();
            }
            search.append(c);
            PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
                int cnt1 = map.get(a);
                int cnt2 = map.get(b);
                if(cnt1 == cnt2) {
                    return b.compareTo(a);
                }
                return cnt1 - cnt2;
            });

            for(String str : map.keySet()) {
                String se = search.toString();
                if(str.startsWith(se)) {
                    pq.add(str);
                    if(pq.size() > 3) {
                        pq.poll();
                    }
                }
            }

            List<String> result = new ArrayList<>();

            while(!pq.isEmpty()) {
                result.add(0, pq.poll());
            }

            return result;
        }
    }

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
}
