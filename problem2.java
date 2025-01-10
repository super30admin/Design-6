import java.util.*;

public class problem2 {

    class AutocompleteSystem {

        // Time Complexity: O(1)
        // Space Complexity: O(n^2 l^2)

        TrieNode root;
        HashMap<String, Integer> map;
        String search;

        class TrieNode{
            HashMap<Character,TrieNode >children;
            List<String> topResults;

            public TrieNode(){
                this.children = new HashMap<>();
                this.topResults = new ArrayList<>();
            }
        }

        public AutocompleteSystem(String[] sentences, int[] times) {
            this.map = new HashMap<>();
            this.search = "";
            this.root = new TrieNode();

            for (int i = 0; i < sentences.length; i++) {
                String sentence = sentences[i];
                int time = times[i];
              //  if (!map.containsKey(sentence)) {

               // }
                map.put(sentence, map.getOrDefault(sentence, 0) + time);
                insert(sentence);
            }

        }
        private void insert(String word) {
            TrieNode curr= root;
            for (char c: word.toCharArray()) {
                if (!curr.children.containsKey(c)) {
                    curr.children.put(c, new TrieNode());
                }
                curr = curr.children.get(c);
                List<String> lst= curr.topResults;
                if (!lst.contains(word)) {
                    lst.add(word);
                }
                Collections.sort(lst,(a,b)->{
                    if (map.get(a)==map.get(b)) {
                        return a.compareTo(b);
                    }
                    return map.get(b)-map.get(a);
                });
                if (lst.size()>3)
                {
                    lst.remove(lst.size()-1);
                }
               // curr.startsWith.add(word);

            }
        }
        private List<String> searchPreifx(String prefix)
        {
            TrieNode curr = root;
            for (char c: prefix.toCharArray()) {
                if (!curr.children.containsKey(c)) {
                    return new ArrayList<>();
                }
                curr = curr.children.get(c);
            }
            return curr.topResults;
        }
        public List<String> input(char c) {
            if (c=='#')
            {
//                if (!map.containsKey(search))
//                {
//                    insert(search);
//                }
                map.put(search, map.getOrDefault(search, 0) + 1);
                insert(search);
                search="";
                return new ArrayList<>();
            }
            search+=c;



            return searchPreifx(search);
        }
    }
// approach 2
// Time Complexity: O(nlogk)
// Space Complexity: O(n^2 l^2)
    class AutocompleteSystem3 {
        TrieNode root;
        HashMap<String, Integer> map;
        String search;

        class TrieNode{
            TrieNode[] children;
            List<String> startsWith;

            public TrieNode(){
                this.children = new TrieNode[256];
                this.startsWith = new ArrayList<>();
            }
        }

        public AutocompleteSystem3(String[] sentences, int[] times) {
            this.map = new HashMap<>();
            this.search = "";
            this.root = new TrieNode();

            for (int i = 0; i < sentences.length; i++) {
                String sentence = sentences[i];
                int time = times[i];
                if (!map.containsKey(sentence)) {
                    insert(sentence);
                }
                map.put(sentence, map.getOrDefault(sentence, 0) + time);
            }

        }
        private void insert(String word) {
            TrieNode curr= root;
            for (char c: word.toCharArray()) {
                if (curr.children[c-' '] == null) {
                    curr.children[c-' '] = new TrieNode();
                }
                curr = curr.children[c-' '];
                curr.startsWith.add(word);

            }
        }
        private List<String> searchPreifx(String prefix)
        {
            TrieNode curr = root;
            for (char c: prefix.toCharArray()) {
                if (curr.children[c-' '] == null) {
                    return new ArrayList<>();
                }
                curr = curr.children[c-' '];
            }
            return curr.startsWith;
        }
        public List<String> input(char c) {
            if (c=='#')
            {
                if (!map.containsKey(search))
                {
                    insert(search);
                }
                map.put(search, map.getOrDefault(search, 0) + 1);
                search="";
                return new ArrayList<>();
            }
            search+=c;
            PriorityQueue<String> pq = new PriorityQueue<>((a,b) ->{
                if (map.get(a)==map.get(b))
                {
                    return b.compareTo(a);
                }
                return map.get(a)-map.get(b);
            });
            List<String> lst= searchPreifx(search);
            for (String sentence : lst){
                pq.add(sentence);
                if (pq.size()>3)
                {
                    pq.poll();
                }
            }
            List<String> result= new ArrayList<>();
            while (!pq.isEmpty())
            {
                result.add(0,pq.poll());

            }
            return result;
        }
    }
    //approach 3
    class AutocompleteSystem2 {
        HashMap<String, Integer> map;
        String search;
        public AutocompleteSystem2(String[] sentences, int[] times) {
            this.map= new HashMap<>();
            this.search="";

            for(int i=0;i<sentences.length;i++)
            {
                String sentence= sentences[i];
                int time = times[i];
                map.put(sentence, map.getOrDefault(sentence,0)+time);
            }
        }

        public List<String> input(char c) {
            if(c=='#')
            {
                map.put(search, map.getOrDefault(search,0)+1);
                search="";
                return new ArrayList<>();
            }
            search+=c;
            PriorityQueue<String> pq= new PriorityQueue<>((a,b) ->{
                if(map.get(a) ==map.get(b))
                {
                    return b.compareTo(a);
                }
                return map.get(a) - map.get(b);
            });
            for(String sentence: map.keySet())
            {
                if(sentence.startsWith(search))
                {
                    pq.add(sentence);
                    if(pq.size()>3)
                    {
                        pq.poll();
                    }
                }
            }
            List<String> result= new ArrayList<>();
            while(!pq.isEmpty())
            {
                result.add(0,pq.poll());
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
