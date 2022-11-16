// Time Complexity : O(N), N -> Prefix length
// Space complextiy : O(KN + N) where the KN is the size of trie, N is the size of hashmap.
class AutoCompleteSystem {

    class TrieNode {
        HashMap<Character, TrieNode> children;
//        HashMap<String, Integer> map;
        List<String> pq;
        public TrieNode () {
            children = new HashMap<>();
//            map = new HashMap<>();
            pq = new ArrayList<>();
        }
    }

    TrieNode root;

    private void insert (String sentence, int times) {
        TrieNode curr = root;
        for (int i =0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            // if character is not presnt in the trie children
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
//            curr.map.put(sentence, curr.map.getOrDefault(sentence, 0) + times);
            List<String> temp = curr.pq;
            if(!temp.contains(sentence)) {
                temp.add(sentence);
            }

            Collections.sort(temp, (a, b) -> {
                if (map.get(b)== map.get(a)) {
                    return a.comparteTo(b);
                }
                map.get(b) - map.get(a);
            });

            if (temp.size() > 3 ) {
                temp.remove(temp.size() - 1);
            }

            curr.pq = temp;
        }
    }

    private List<String> search(String prefix) {
        TrieNode curr = root;
        for (int i=0; i <prefix.length(); i++) {
            char c = prefix.charAt(i);
            if(!curr.children.containsKey(c)) {
                return new ArrayList<>();
            }
            curr = curr.chidren.get(c);
        }
        curr.pq;
    }

    //HashMap<String, Integer> map;
    StringBuilder sb;

    public AutoCompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        root = new TrieNode();
        sb = new StringBuilder();
        int n = sentences.length;

        for (int i=0; i < n; i++) {
            String sentence = sentences[i];
            map.put(sentence, map.getOrDefault(sentence, 0) + times[i]);
            insert(sentence, times[i]);
        }
    }

    public List<String> input (char c) {
        // Handle hash in a special way
        if (c == '#') {
            String stringsofar = sb.toString();
            map.put(stringsofar, map.getOrDefault(stringsofar, 0) + 1);
            insert(stringsofar, 1);
            return new ArrayList<>();
        }
        sb.append(c);
        String stringofar = sb.toString();
        return search(stringofar);

//        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
//            // using as min heap
//            if (map.get(a) == map.get(b)) {
//                return b.compareTo(a);
//            }
//            return map.get(a) - map.get(b);
//        });
//
//        for (String key: map.keySet()) {
//            if (key.startsWith(stringofar)) {
//                pq.add(key);
//                if (pq.size() > 3) {
//                    pq.poll();
//                }
//            }
//        }
//
//        List<String> result = new ArrayList<>();
//        while(!pq.isEmpty()) {
//            result.add(0, pq.poll());
//        }
//
//        return result;
    }

}
