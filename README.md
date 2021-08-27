# Design-6

## Problem1 Design Phone Directory (https://leetcode.com/problems/design-phone-directory/)

//Time Complexity - O(1)
//Space Complexity - O(N)

class PhoneDirectory {

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
     HashSet<Integer> set;
     Queue<Integer> queue;
     int max;
    public PhoneDirectory(int maxNumbers) {
        set = new HashSet<>();
        queue = new LinkedList<>();
        max = maxNumbers - 1;
        for(int i = 0 ; i < maxNumbers; i++) {
            queue.add(i);
        }

    }

    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(!queue.isEmpty()) {
            int current = queue.poll();
            set.add(current);
            return current;
        }
        return -1;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        if(number < 0 || number > max) {
            return false;
        } else {
            return !set.contains(number);
        }
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if(set.contains(number)) {
            set.remove(number);
            queue.add(number);
        }

    }

}

/\*\*

- Your PhoneDirectory object will be instantiated and called as such:
- PhoneDirectory obj = new PhoneDirectory(maxNumbers);
- int param_1 = obj.get();
- boolean param_2 = obj.check(number);
- obj.release(number);
  \*/

## Problem2 Design Autocomplete System (https://leetcode.com/explore/interview/card/amazon/81/design/3000/)

//Time Complexity - O(1)
//Space Complexity - O(N)

class AutocompleteSystem {

    HashMap<String, Integer> countMap;

    class TrieNode {
        List<String> pqueue;
        HashMap<Character, TrieNode> children;

        public TrieNode() {
            pqueue = new ArrayList<>();
            children = new HashMap<>();
        }
    }
    TrieNode root;

    public void insert(String sentence, int times) {
        TrieNode current = root;
        for(int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if(!current.children.containsKey(c)) {
                current.children.put(c, new TrieNode());
            }
            current = current.children.get(c);

            if(!current.pqueue.contains(sentence)) {
                current.pqueue.add(sentence);
            }

            Collections.sort(current.pqueue, (a,b) -> countMap.get(a) == countMap.get(b) ?
                            a.compareTo(b) : countMap.get(b) - countMap.get(a));

            if(current.pqueue.size() > 3) {
                current.pqueue.remove(current.pqueue.size()-1);
            }

        }
    }
    List<String> search(String prefix) {
        TrieNode current = root;
        for(int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if(!current.children.containsKey(c)) {
                return new ArrayList<>();
            }
            current = current.children.get(c);
        }
        return current.pqueue;
    }

    StringBuilder input;


    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        countMap = new HashMap<>();
        input = new StringBuilder();

        for(int i = 0 ; i < sentences.length; i++) {
            countMap.put(sentences[i], countMap.getOrDefault(sentences[i],0) + times[i]);
            insert(sentences[i],times[i]);
        }
    }

    public List<String> input(char c) {
        if(c == '#') {
            String inputStr = input.toString();
            countMap.put(inputStr, countMap.getOrDefault(inputStr,0) + 1);
            insert(inputStr,1);
            input = new StringBuilder();
            return new ArrayList<>();
        }
        input.append(c);
        return search(input.toString());

    }

}

/\*\*

- Your AutocompleteSystem object will be instantiated and called as such:
- AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
- List<String> param_1 = obj.input(c);
  \*/
