// Optimal Approach : Using HashMap, list, Trie

public class AutoCompleteSystem {
    
    Map<String, Integer> map;
    StringBuilder sb;
    TrieNode root;
    
    // TrieNode
    class TrieNode {
        TrieNode[] children;
        List<String> words;
        private TrieNode() {
            this.children = new TrieNode[256];
            this.words = new ArrayList<>();
        }
    }
    
    // Constructor
    public AutoCompleteSystem(String[] sentences, int[] times) {
        this.map = new HashMap<>();
        this.sb = new StringBuilder();
        this.root = new TrieNode();
        for(int i=0; i<sentences.length; i++) {
            String sentence = sentences[i];
            map.put(sentence, map.getOrDefault(sentence, 0) + times[i]);
            insert(sentence);
        }
    }
    
    // input
    public List<String> input(char c) {
        
        // end of search
        if(c == '#') {
            String sentence = sb.toString();
            map.put(sentence, map.getOrDefault(sentence, 0) + 1);
            insert(sentence);
            this.sb = new StringBuilder();
            return new ArrayList<>();
        }
        
        // search going on
        sb.append(c);
        
        return searchSentence(sb.toString());
        
    }
    
    // insert words in Trie
    public void insert(String sentence) {
        TrieNode curr = root;
        for(int i=0; i<sentence.length(); i++) {
            char c = sentence.charAt(i);
            if(curr.children[c - ' '] == null)
                curr.children[c - ' '] = new TrieNode();
            curr = curr.children[c - ' '];
            
            // getting list of senences from Trie
            List<String> list = curr.words;
            if(!list.contains(sentence))
                list.add(sentence);
            
            // sorting
            Collections.sort(
                list,
                (a, b) -> {
                    int ca = map.get(a);
                    int cb = map.get(b);
                    if(ca == cb)
                        return a.compareTo(b);
                    return cb-ca;
                }
            );
            
            // removing last word if needed
            if(list.size() > 3)
                list.remove(list.size()-1);
            
        }
    }
    
    // search words from Trie and get list of sentences starting with it
    public List<String> searchSentence(String sentence) {
        TrieNode curr = root;
        for(int i=0; i<sentence.length(); i++) {
            char c = sentence.charAt(i);
            if(curr.children[c - ' '] == null)
                return new ArrayList<>();
            curr = curr.children[c - ' '];
        }
        return curr.words;
    }
    
    
    
    // MAIN
    public static void main(String[] args) {
        
        // variables
        String[] sentences = {"i love you", "leetcode", "i love leetcode", "island", "ironman", "india", "ispat"};
        int[] times = {5, 7, 4, 3, 3, 2, 1};
        
        // Constructor
        AutoCompleteSystem obj = new AutoCompleteSystem(sentences, times);
        
        // before running input
        for (Map.Entry<String, Integer> entry : obj.map.entrySet()) {
          System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        // input
        String str1 = "i love leetcode#";
        for(int i=0; i<str1.length(); i++) {
            char c = str1.charAt(i);
            System.out.println(Arrays.toString(obj.input(c).toArray()));   
        }
        
        String str2 = "i love india#";
        for(int i=0; i<str2.length(); i++) {
            char c = str2.charAt(i);
            System.out.println(Arrays.toString(obj.input(c).toArray()));   
        }
        
        // after running input
        for (Map.Entry<String, Integer> entry : obj.map.entrySet()) {
          System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    
}

// // Intermediate Approach : Using HashMap, MinHeap, Trie

// public class AutoCompleteSystem {
    
//     Map<String, Integer> map;
//     StringBuilder sb;
//     TrieNode root;
    
//     // TrieNode
//     class TrieNode {
//         TrieNode[] children;
//         List<String> words;
//         private TrieNode() {
//             this.children = new TrieNode[256];
//             this.words = new ArrayList<>();
//         }
//     }
    
//     // Constructor
//     public AutoCompleteSystem(String[] sentences, int[] times) {
//         this.map = new HashMap<>();
//         this.sb = new StringBuilder();
//         this.root = new TrieNode();
//         for(int i=0; i<sentences.length; i++) {
//             String sentence = sentences[i];
//             if(!map.containsKey(sentence))
//                 insert(sentence);
//             map.put(sentence, map.getOrDefault(sentence, 0) + times[i]);
//         }
//     }
    
//     // input
//     public List<String> input(char c) {
        
//         // end of search
//         if(c == '#') {
//             String sentence = sb.toString();
//             if(!map.containsKey(sentence))
//                 insert(sentence);
//             map.put(sentence, map.getOrDefault(sentence, 0) + 1);
//             sb = new StringBuilder();
//             return new ArrayList<>();
//         }
        
//         // search going on
//         sb.append(c);
        
//         // declare minheap for every character being added
//         PriorityQueue<String> pq = new PriorityQueue<>(
//             (a, b) -> {
//                 int ca = map.get(a);
//                 int cb = map.get(b);
//                 if(ca == cb) 
//                     return b.compareTo(a);
//                 return ca-cb;
//             }
//         );
        
//         // get all sentences from map starting with current sentence, add it in minheap
//         String sentence = sb.toString();
//         List<String> list = searchSentence(sentence);
//         for(String str : list) {
//             pq.add(str);
//             if(pq.size() > 3) 
//                 pq.poll();
//         }
        
//         //getting values from minhead for result
//         List<String> result = new ArrayList<>();
//         while(!pq.isEmpty()) {
//             result.add(pq.poll());
//         }
//         return result;
        
//     }
    
//     // insert words in Trie
//     public void insert(String sentence) {
//         TrieNode curr = root;
//         for(int i=0; i<sentence.length(); i++) {
//             char c = sentence.charAt(i);
//             if(curr.children[c - ' '] == null)
//                 curr.children[c - ' '] = new TrieNode();
//             curr = curr.children[c - ' '];
//             curr.words.add(sentence);
//         }
//     }
    
//     // search words from Trie and get list of sentences starting with it
//     public List<String> searchSentence(String sentence) {
//         TrieNode curr = root;
//         for(int i=0; i<sentence.length(); i++) {
//             char c = sentence.charAt(i);
//             if(curr.children[c - ' '] == null)
//                 return new ArrayList<>();
//             curr = curr.children[c - ' '];
//         }
//         return curr.words;
//     }
    
    
    
//     // MAIN
//     public static void main(String[] args) {
        
//         // variables
//         String[] sentences = {"i love you", "leetcode", "i love leetcode", "island", "ironman", "india", "ispat"};
//         int[] times = {5, 7, 4, 3, 3, 2, 1};
        
//         // Constructor
//         AutoCompleteSystem obj = new AutoCompleteSystem(sentences, times);
        
//         // before running input
//         for (Map.Entry<String, Integer> entry : obj.map.entrySet()) {
//           System.out.println(entry.getKey() + ": " + entry.getValue());
//         }
        
//         // input
//         String str1 = "i love leetcode#";
//         for(int i=0; i<str1.length(); i++) {
//             char c = str1.charAt(i);
//             System.out.println(Arrays.toString(obj.input(c).toArray()));   
//         }
        
//         String str2 = "i love india#";
//         for(int i=0; i<str2.length(); i++) {
//             char c = str2.charAt(i);
//             System.out.println(Arrays.toString(obj.input(c).toArray()));   
//         }
        
//         // after running input
//         for (Map.Entry<String, Integer> entry : obj.map.entrySet()) {
//           System.out.println(entry.getKey() + ": " + entry.getValue());
//         }
//     }
    
// }


// // Brute Force Approach : Using HashMap, MinHeap, checking prefix every time

// public class AutoCompleteSystem {
    
//     Map<String, Integer> map;
//     StringBuilder sb;
    
//     public AutoCompleteSystem(String[] sentences, int[] times) {
//         this.map = new HashMap<>();
//         this.sb = new StringBuilder();
//         for(int i=0; i<sentences.length; i++) {
//             String sentence = sentences[i];
//             map.put(sentence, map.getOrDefault(sentence, 0) + times[i]);
//         }
//     }
    
//     public List<String> input(char c) {
        
//         // end of search
//         if(c == '#') {
//             String sentence = sb.toString();
//             map.put(sentence, map.getOrDefault(sentence, 0) + 1);
//             sb = new StringBuilder();
//             return new ArrayList<>();
//         }
        
//         // search going on
//         sb.append(c);
        
//         // declare minheap for every character being added
//         PriorityQueue<String> pq = new PriorityQueue<>(
//             (a, b) -> {
//                 int ca = map.get(a);
//                 int cb = map.get(b);
//                 if(ca == cb) 
//                     return b.compareTo(a);
//                 return ca-cb;
//             }
//         );
        
//         // get all sentences from map starting with current sentence, add it in minheap
//         String sentence = sb.toString();
//         for(String key : map.keySet()) {
//             if(key.startsWith(sentence))
//                 pq.add(key);
//             if(pq.size() > 3) 
//                 pq.poll();
//         }
        
//         //getting values from minhead for result
//         List<String> result = new ArrayList<>();
//         while(!pq.isEmpty()) {
//             result.add(pq.poll());
//         }
//         return result;
        
//     }
    
//     public static void main(String[] args) {
        
//         // variables
//         String[] sentences = {"i love you", "leetcode", "i love leetcode", "island", "ironman", "india", "ispat"};
//         int[] times = {5, 7, 4, 3, 3, 2, 1};
        
//         // Constructor
//         AutoCompleteSystem obj = new AutoCompleteSystem(sentences, times);
        
//         // before running input
//         for (Map.Entry<String, Integer> entry : obj.map.entrySet()) {
//           System.out.println(entry.getKey() + ": " + entry.getValue());
//         }
        
//         // input
//         String str1 = "i love leetcode#";
//         for(int i=0; i<str1.length(); i++) {
//             char c = str1.charAt(i);
//             System.out.println(Arrays.toString(obj.input(c).toArray()));   
//         }
        
//         String str2 = "i love india#";
//         for(int i=0; i<str2.length(); i++) {
//             char c = str2.charAt(i);
//             System.out.println(Arrays.toString(obj.input(c).toArray()));   
//         }
        
//         // after running input
//         for (Map.Entry<String, Integer> entry : obj.map.entrySet()) {
//           System.out.println(entry.getKey() + ": " + entry.getValue());
//         }
//     }
    
// }

