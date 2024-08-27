# Design-6

## Problem1 Design Phone Directory (https://leetcode.com/problems/design-phone-directory/)

Explain approach in 3 lines: In the initialization, we will store all available phone numbers from 0 to maximum numbers in both hashset and a queue. In the get function we will retrieve and remove a number from queue and set and we will return it. If no numbers are available then we can return -1. In the check function we will check if a number is available by looking it up in the set and in the release function we will add it back to both the set and the queue if it is not already present.

Time Complexity: For all the functions it is O(1). For get operation we poll from the queue and remove from set, so we will get it in O(1) time. To check the number we can just look up in set with O(1) time. Also, in release we add the new number in constant time in queue and set.

Space Complexity: O(n) where n is maximum numbers where we are storing all numbers in the set and queue

Approached Code:

class PhoneDirectory {

    private HashSet<Integer> set;
    private Queue<Integer> queue;

    public PhoneDirectory(int maxNumbers) {
        this.set = new HashSet<>();
        this.queue = new LinkedList<>();

        for(int i = 0; i < maxNumbers; i++) {
            set.add(i);
            queue.add(i);
        }
    }
    
    public int get() {
        if(queue.isEmpty()) {
            return -1;
        }

        int re = queue.poll();
        set.remove(re);

        return re;
    }
    
    public boolean check(int number) {
        return set.contains(number);
    }
    
    public void release(int number) {
        if(!set.contains(number)) {
            set.add(number);
            queue.add(number);
        }
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */



## Problem2 Design Autocomplete System (https://leetcode.com/problems/design-search-autocomplete-system/)

Explain approach in 3 lines: We will be storing the sentences in a Trie maintaining a list of the top 3 suggestions at each node which will be sorted by frequency and lexicographical order. In the insertion and update operation when a new sentence is added or updated we will insert it into the Trie and we will adjust the top 3 list at each node. In the search operation as user types we will traverse the Trie according to the input prefix and return the top 3 suggestions.

Time Complexity: For insertion we will need O(n * m * log3) time where n is the number of sentences m is the average length of the sentences and log3 due to maintaining top 3 list. For search it will be O(p) where p is the length of the prefix being searched.

Space Complexity: O(n*m) which will be space for Trie where n is number of sentences and m is the average length of the sentences. O(k) will be the additional space for storing frequency map where k is the number of unique sentences.

Approached Code:

class AutocompleteSystem {

    class TrieNode {
        // TrieNode[] children;
        HashMap<Character, TrieNode> children;
        // List<String> startsWith;
        List<String> top3;

        public TrieNode() {
            // this.children = new TrieNode[256];
            this.children = new HashMap<>();
            // this.startsWith = new ArrayList<>();
            this.top3 = new ArrayList<>();
        }
    }

    private void insert (TrieNode root, String word) {
        TrieNode curr = root;

        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            // if(curr.children[c - ' '] == null) {
            //     curr.children[c - ' '] = new TrieNode();
            // }

            if(!curr.children.containsKey(c)) {
                curr.children.put(c, new TrieNode());
            }

            // curr = curr.children[c - ' '];
            curr = curr.children.get(c);
            // curr.startsWith.add(word);

            List<String> li = curr.top3;
            if(!li.contains(word)) {
                li.add(word);
            }

            Collections.sort(li, (a, b) -> {
                int countA = map.get(a);
                int countB = map.get(b);

                if(countA == countB) {
                    return a.compareTo(b);
                }

                return countB - countA;
            });

            if(li.size() > 3) {
                li.remove(li.size() - 1);
            }

            // curr.top3.add(word);
        }
    }

    private List<String> search(TrieNode root, String prefix) { 
        TrieNode curr = root;

        for(int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);

            // if(curr.children[c - ' '] == null) {
            //     return new ArrayList<>();
            // }

            // curr = curr.children[c - ' '];

            if(!curr.children.containsKey(c)) {
                return new ArrayList<>();
            }

            curr = curr.children.get(c);
        }

        // return curr.startsWith;
        return curr.top3;
    }

    private HashMap<String, Integer> map;
    private StringBuilder input;

    private TrieNode root;

    public AutocompleteSystem(String[] sentences, int[] times) {
        this.map = new HashMap<>();
        this.input = new StringBuilder();

        this.root = new TrieNode();

        for(int i = 0; i < times.length; i++) {
            String str = sentences[i];
            int count = times[i];

            // if(!map.containsKey(str)) {
            //     insert(root, str);
            // }

            map.put(str, map.getOrDefault(str, 0) + count);
            insert(root, str);
        }
    }
    
    public List<String> input(char c) {
        if(c == '#') {
            String in = input.toString();

            // if(!map.containsKey(in)) {
            //     insert(root, in);
            // }

            map.put(in, map.getOrDefault(in, 0) + 1);
            insert(root, in);
            input = new StringBuilder();
            return new ArrayList<>();
        }

        input.append(c);

        String searchTerm = input.toString();
        
        // PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
        //     int count1 = map.get(a);
        //     int count2 = map.get(b);

        //     if(count1 == count2) {
        //         return b.compareTo(a);
        //     }

        //     return count1 - count2;
        // });

        // List<String> startsWith = search(root, searchTerm);

        // // for(String word: map.keySet()) {
        // for(String word: startsWith) {
        //     if(word.startsWith(searchTerm)) {
        //         pq.add(word);

        //         if(pq.size() > 3) {
        //             pq.poll();
        //         }
        //     }
        // }

        // List<String> result = new ArrayList<>();

        // while(!pq.isEmpty()) {
        //     result.add(0, pq.poll());
        // }

        // return result;

        return search(root, searchTerm);
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */


