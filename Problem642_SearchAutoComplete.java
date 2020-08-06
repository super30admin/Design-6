/**
 * Time Complexity - O(nlogn) n = number of strings
 * Space Complexity - O(nL) L = length of maximum String
 */

class AutocompleteSystem {

    class TrieNode{
        Map<String,Integer> counts;
        Map<Character,TrieNode> childrens;
        public TrieNode(){
            counts = new HashMap<>();
            childrens = new HashMap<>();
        }
    }
    TrieNode root;
    String prefix;
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        prefix = "";
        for(int i=0; i<sentences.length; i++){      // O(n*L)
            addToTrie(sentences[i], times[i]);
        }
    }

    public void addToTrie(String sentence, int count){      // O(L) =  maxlength of String
        TrieNode curr = root;

        for(char ch : sentence.toCharArray()){
            TrieNode children = curr.childrens.get(ch);
            if(children == null){
                children = new TrieNode();
                curr.childrens.put(ch, children);
            }
            curr = children;
            curr.counts.put(sentence, curr.counts.getOrDefault(sentence,0)+count);
        }
    }

    public List<String> input(char c) {

        if(c == '#'){
            addToTrie(prefix,1);
            prefix = "";
            return new ArrayList<>();
        }

        prefix = prefix + c;
        TrieNode curr = root;
        for(char ch : prefix.toCharArray()){            // O(L)  L = prefix length
            TrieNode children = curr.childrens.get(ch);
            if(children == null)
                return new ArrayList<>();
            curr = curr.childrens.get(ch);
        }

        Map<String,Integer> counts = curr.counts;

        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> a.c == b.c ? a.s.compareTo(b.s) : b.c - a.c); // O(nlogn)

        for(String s : counts.keySet()){
            pq.add(new Pair(s,counts.get(s)));
        }

        List<String> result = new ArrayList<>();

        for (int i = 0; i < 3 && !pq.isEmpty(); i++) {
            result.add(pq.poll().s);
        }
        return result;
    }

    class Pair {
        String s;
        int c;
        public Pair(String _s, int _c){
            s = _s;
            c = _c;
        }
    }

}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */