/**
Design Search Autocomplete System
Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#'). For each character they type except '#', you need to return the top 3 historical hot sentences that have prefix the same as the part of sentence already typed. Here are the specific rules:
The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
If less than 3 hot sentences exist, then just return as many as you can.
When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
Your job is to implement the following functions:
The constructor function:
AutocompleteSystem(String[] sentences, int[] times): This is the constructor. The input is historical data. Sentences is a string array consists of previously typed sentences. Times is the corresponding times a sentence has been typed. Your system should record these historical data.
Now, the user wants to input a new sentence. The following function will provide the next character the user types:
List<String> input(char c): The input c is the next character typed by the user. The character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#'). Also, the previously typed sentence should be recorded in your system. The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.
 
Example:
Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
The system have already tracked down the following sentences and their corresponding times:
"i love you" : 5 times
"island" : 3 times
"ironman" : 2 times
"i love leetcode" : 2 times
Now, the user begins another search:
Operation: input('i')
Output: ["i love you", "island","i love leetcode"]
Explanation:
There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored.
Operation: input(' ')
Output: ["i love you","i love leetcode"]
Explanation:
There are only two sentences that have prefix "i ".
Operation: input('a')
Output: []
Explanation:
There are no sentences that have prefix "i a".
Operation: input('#')
Output: []
Explanation:
The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.
 
Note:
The input sentence will always start with a letter and end with '#', and only one blank space will exist between two words.
The number of complete sentences that to be searched won't exceed 100. The length of each sentence including those in the historical data won't exceed 100.
Please use double-quote instead of single-quote when you write test cases even for a character input.
Please remember to RESET your class variables declared in class AutocompleteSystem, as static/class variables are persisted across multiple test cases. Please see here for more details.
**/










/**
Brute Force
**/

class AutocompleteSystem {
    StringBuilder input;
    int k;
    Map<String, Integer> counter;
    public AutocompleteSystem(String[] sentences, int[] times) {
        k = 3;
        counter = new HashMap();
        for(int i =0; i< sentences.length; i++) {
            String sentence = sentences[i];
            counter.put(sentence, counter.getOrDefault(sentence, 0) + times[i]);
        } 
        input = new StringBuilder();
    }
    
    public List<String> input(char c) {
        // if sentence ends 
        if(c == '#') {
            // add current sentence and update map and then update input
            String se = input.toString();
            counter.put(se, counter.getOrDefault(se,0)+1);
            input = new StringBuilder();
            return  new ArrayList();
        }
        
        input.append(c);
        return getResult();
        
    }
    
    private List<String> getResult() {
        
        List<String> res = new ArrayList();
        Queue<String> pq = new PriorityQueue( (a,b) -> {
            if(counter.get(b) == counter.get(a)) {
              return  String.valueOf(b).compareTo(String.valueOf(a));
            }
            return counter.get(a)  - counter.get(b);
            
        });
       
        
        String inputStr = input.toString();
        
        for(String key : counter.keySet()) {
            if(key.startsWith(inputStr)) {
                pq.add(key);
                if(pq.size() > k) {
                    pq.remove();
                }
            }
        }
        
        while(!pq.isEmpty()) {
            res.add(0, pq.poll());
        }
        
        return res;
    } 
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */


/**

Optimized version with Trie DataStructure:

Insert Complexity O(k*L) L is number of senetence , K is avg length of all the sentence

input() search complexity : O(i + l  + k*logk) i is sentence length formed till now, l is longest length till end of Trie in worst case and k logk for hnadling sorting part
**/

class AutocompleteSystem {
    
    private class TrieNode {
        // childrent of this node
        HashMap<Character, TrieNode> children;
        // maintain all the string and count starts with that String
        HashMap<String, Integer> counter;
        
        public TrieNode() {
            children = new HashMap();
            counter = new HashMap();
        }
        
    }
    
    TrieNode root;
    StringBuilder input;
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        input = new StringBuilder();
        root = new TrieNode();
        // add all the sentence into our Trie Data Structure
        for(int i = 0; i< sentences.length; i++) {
            add(root, sentences[i], times[i]);
        }
    }
    
    // Helper function to add String to Trie
    
    private void add(TrieNode root, String s, int time) {
        // if root contains string s then update the count
        // else add String to root
        TrieNode curr = root;
        // String s not in trie node yet
        for(Character ch :  s.toCharArray()) {
            if(!curr.children.containsKey(ch)) {
                curr.children.put(ch, new TrieNode());
            }
            curr = curr.children.get(ch);
            curr.counter.put(s, curr.counter.getOrDefault(s, 0) +time);
        }

        
        
    }
    
    // helper method to search the input String in Trie
    // return map <String,count> that starts with inputStr
    private HashMap<String, Integer> search(String inputStr) {
        HashMap<String, Integer> map = new HashMap();
        //         traverse the Trie till you find the inputString
        
        TrieNode curr = root;
        for(Character ch :  inputStr.toCharArray()) {
            if(curr.children.get(ch) == null) {
                return null;
            }
            
            curr  = curr.children.get(ch);
            
        }
        
        return curr.counter;
        
    }
    
    // user typed char c add to current search and return the 
    // suggestions based on past history
    
    public List<String> input(char c) {
        
        // if current char is # that means it is end of search
        // return the []
        // add current search in Trie Data Structure
        if(c == '#') {
            // end of the input search
            String inputStr = input.toString();
            add(root, inputStr, 1);
            input = new StringBuilder();
            return new ArrayList();
        }
        // Search
        input.append(c);
        String inputStr = input.toString();
        
        HashMap<String, Integer> map = search(inputStr);
        
        if(map == null) {
            return new ArrayList();
        }
        
        List<String> res = new ArrayList();
        
        // get top 3 senetence based on hotness
        Queue<String> pq = new PriorityQueue((a,b) -> {
           if(map.get(a) == map.get(b)) {
               return String.valueOf(b).compareTo(String.valueOf(a));
           } 
            
            return map.get(a) - map.get(b);
        });
        
        for(String key : map.keySet()) {
            pq.add(key);
            if(pq.size() > 3) {
                pq.remove();
            }
        }
        
        while(!pq.isEmpty()) {
            res.add(0, pq.poll());
        }
        
        return res;
    }
}


/**

one more way


**/


class AutocompleteSystem {
    
    private class TrieNode {
        HashMap<Character, TrieNode> children;
        ArrayList<String> list;
        
        TrieNode(){
            children = new HashMap();
            list = new ArrayList();
        }
        
        
    }
    
    HashMap<String,Integer> hashMap = new HashMap();
    StringBuilder input = new StringBuilder();
    TrieNode root  = new TrieNode();
    
        
        

    private void add( String s) {
        
        TrieNode curr = root;
        for(Character ch : s.toCharArray()) {
            if(!curr.children.containsKey(ch)) {
                curr.children.put(ch, new TrieNode());
            }
            curr = curr.children.get(ch);
             ArrayList<String> temp = curr.list;
            if(!temp.contains(s)) {
               temp.add(s);    
            }
            
           
            Collections.sort(temp, (a,b) -> {
                
               if(hashMap.get(a) == hashMap.get(b)) {
                   return String.valueOf(a).compareTo(String.valueOf(b));
               } 
                
                
                return hashMap.get(b) - hashMap.get(a);
                
            });
            
            // remove extra values
            if(temp.size() > 3) {
                temp.remove(3);
            }
            curr.list = temp;
        }
    }
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        
        
        for(int i = 0;i<sentences.length;i++) {
            // add sentence to global counter map
            hashMap.put(sentences[i], hashMap.getOrDefault(sentences[i],0) + times[i]);
            
            // add input sentence to Trie and build Trie Data Structure
            add(sentences[i]);
        }
        
        
        
    }
    
    private List<String> search(String input) {
        
        List<String> res = new ArrayList();
        
        TrieNode curr =root;
        
        for(Character ch : input.toCharArray()) {
            if(!curr.children.containsKey(ch)) {
                return  new ArrayList();
            }
            curr = curr.children.get(ch);
        }
        
        return curr.list;
        
        
    }
    
    public List<String> input(char c) {
        // if special char
        if(c == '#'){
            String inputStr = input.toString();
           hashMap.put(inputStr,hashMap.getOrDefault(inputStr,0)+1);
            add( inputStr);
            
            input = new StringBuilder();
            return new ArrayList();
        }
        
        input.append(c);
        String inputStr = input.toString();
       
        
        return search(inputStr);
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
