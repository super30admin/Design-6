import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

class AutoCompleteSystem{
    class TrieNode{
        HashMap<Character,TrieNode> child;
        List<String> startsWith;
        List<String> topList;
        public TrieNode(){
            this.child = new HashMap<>();
            this.startsWith = new ArrayList<>();
            this.topList = new ArrayList<>();    
        }
    }
    private void insert(String sentence){
        TrieNode curr = root;
        for(int i=0;i<sentence.length();i++){
            char c = sentence.charAt(i);
            if(!curr.child.containsKey(c)){
                curr.child.put(c,new TrieNode());
            }
            curr = curr.child.get(c);
            List<String> top3 = curr.startsWith;
            if(!top3.contains(sentence)){
                top3.add(sentence);
            }
            Collections.sort(top3,(a,b)->{
                int fa = map.get(a);
                int fb = map.get(b);
                if(fa == fb){
                    return a.compareTo(b);
                }
                return fa-fb;
            });
            if(top3.size()>3){
                top3.remove(top3.size()-1);
            }
            
        }
    }

    private List<String> searchTrie(String searchTerm){
        TrieNode curr = root;
        for(int i=0;i<searchTerm.length();i++){
            char c = searchTerm.charAt(i);
            if(!curr.child.containsKey(c)){
                return new ArrayList<>();
            }
            curr = curr.child.get(c);
        }
        return curr.startsWith;
    }

    HashMap<String,Integer> map;
    StringBuilder search;
    TrieNode root;
    public AutoCompleteSystem(String[] sentences, int[] times){
        map = new HashMap<>();
        search = new StringBuilder();
        root = new TrieNode();
        // Fill the HashMap
        for(int i=0;i<sentences.length;i++){
           
            map.put(sentences[i],map.getOrDefault(sentences[i], 0)+times[i]);
            //  if(!map.containsKey(sentences[i])){
                insert(sentences[i]);    
            // }
            
        }
    }

    public List<String> input(char c){
        if(c == '#'){
            // Whatever is the search term add it inside map i.e. cache
            String searchTerm = search.toString();
           
            map.put(searchTerm,map.getOrDefault(searchTerm, 0)+1);
            // if(!map.containsKey(searchTerm)){
                insert(searchTerm);
            // }
            return new ArrayList<>();
        }
        PriorityQueue<String> pq = new PriorityQueue<>((a,b)->{
            int fa = map.get(a); // good practice to avoid null related errors
            int fb = map.get(b); // good practice to avoid null related errors
            if(fa == fb){
                return b.compareTo(a);
            }
            return fa - fb;
        });
        search.append(c);
        String searchTerm = search.toString();
/*         We search for all the sentences which startswith our
        search term and then store the top three out of them 
        into the priority queue */
        List<String> li = searchTrie(searchTerm);
        for(String sentence:li){

                pq.add(sentence);
                if(pq.size()>3){
                    pq.poll();
                }

        }

        List<String> result = new ArrayList<>();
        while(!pq.isEmpty()){
            result.add(0,pq.poll());
        }
        return result;

    }

    public static void main(String[] args){
        String[] sentences = {"i love you", "island","ironman","i love leetcode"};
        int[] times = {5,3,2,2};
        AutoCompleteSystem acs = new AutoCompleteSystem(sentences, times);
        System.out.println(acs.input('i') + acs.search.toString());
        System.out.println(acs.input('s') + acs.search.toString());
        System.out.println(acs.input(' ') + acs.search.toString());
        System.out.println(acs.input('#') + acs.search.toString());
    }
}

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.PriorityQueue;

// class AutoCompleteSystem{
//     class TrieNode{
//         HashMap<Character,TrieNode> child;
//         List<String> startsWith;
//         public TrieNode(){
//             this.child = new HashMap<>();
//             this.startsWith = new ArrayList<>();    
//         }
//     }
//     private void insert(String sentence){
//         TrieNode curr = root;
//         for(int i=0;i<sentence.length();i++){
//             char c = sentence.charAt(i);
//             if(!curr.child.containsKey(c)){
//                 curr.child.put(c,new TrieNode());
//             }
//             curr = curr.child.get(c);
//             curr.startsWith.add(sentence);
//         }
//     }

//     private List<String> searchTrie(String searchTerm){
//         TrieNode curr = root;
//         for(int i=0;i<searchTerm.length();i++){
//             char c = searchTerm.charAt(i);
//             if(!curr.child.containsKey(c)){
//                 return new ArrayList<>();
//             }
//             curr = curr.child.get(c);
//         }
//         return curr.startsWith;
//     }

//     HashMap<String,Integer> map;
//     StringBuilder search;
//     TrieNode root;
//     public AutoCompleteSystem(String[] sentences, int[] times){
//         map = new HashMap<>();
//         search = new StringBuilder();
//         root = new TrieNode();
//         // Fill the HashMap
//         for(int i=0;i<sentences.length;i++){
//             if(!map.containsKey(sentences[i])){
//                 insert(sentences[i]);
//             }
//             map.put(sentences[i],map.getOrDefault(sentences[i], 0)+times[i]);
//         }
//     }

//     public List<String> input(char c){
//         if(c == '#'){
//             // Whatever is the search term add it inside map i.e. cache
//             String searchTerm = search.toString();
//             if(!map.containsKey(searchTerm)){
//                 insert(searchTerm);
//             }
//             map.put(searchTerm,map.getOrDefault(searchTerm, 0)+1);
//             return new ArrayList<>();
//         }
//         PriorityQueue<String> pq = new PriorityQueue<>((a,b)->{
//             int fa = map.get(a); // good practice to avoid null related errors
//             int fb = map.get(b); // good practice to avoid null related errors
//             if(fa == fb){
//                 return b.compareTo(a);
//             }
//             return fa - fb;
//         });
//         search.append(c);
//         String searchTerm = search.toString();
// /*         We search for all the sentences which startswith our
//         search term and then store the top three out of them 
//         into the priority queue */
//         List<String> li = searchTrie(searchTerm);
//         for(String sentence:li){

//                 pq.add(sentence);
//                 if(pq.size()>3){
//                     pq.poll();
//                 }

//         }

//         List<String> result = new ArrayList<>();
//         while(!pq.isEmpty()){
//             result.add(0,pq.poll());
//         }
//         return result;

//     }

//     public static void main(String[] args){
//         String[] sentences = {"i love you", "island","ironman","i love leetcode"};
//         int[] times = {5,3,2,2};
//         AutoCompleteSystem acs = new AutoCompleteSystem(sentences, times);
//         System.out.println(acs.input('i') + acs.search.toString());
//         System.out.println(acs.input('s') + acs.search.toString());
//         System.out.println(acs.input(' ') + acs.search.toString());
//         System.out.println(acs.input('#') + acs.search.toString());
//     }
// }

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.PriorityQueue;

// class AutoCompleteSystem{
//     class TrieNode{
//         TrieNode[] children;
//         List<String> startsWith;
//         public TrieNode(){
//             this.children = new TrieNode[256];
//             this.startsWith = new ArrayList<>();    
//         }
//     }
//     private void insert(String sentence){
//         TrieNode curr = root;
//         for(int i=0;i<sentence.length();i++){
//             char c = sentence.charAt(i);
//             if(curr.children[c-' '] == null){
//                 curr.children[c-' '] = new TrieNode();
//             }
//             curr = curr.children[c-' '];
//             curr.startsWith.add(sentence);
//         }
//     }

//     private List<String> searchTrie(String searchTerm){
//         TrieNode curr = root;
//         for(int i=0;i<searchTerm.length();i++){
//             char c = searchTerm.charAt(i);
//             if(curr.children[c - ' '] == null){
//                 return new ArrayList<>();
//             }
//             curr = curr.children[c - ' '];
//         }
//         return curr.startsWith;
//     }

//     HashMap<String,Integer> map;
//     StringBuilder search;
//     TrieNode root;
//     public AutoCompleteSystem(String[] sentences, int[] times){
//         map = new HashMap<>();
//         search = new StringBuilder();
//         root = new TrieNode();
//         // Fill the HashMap
//         for(int i=0;i<sentences.length;i++){
//             if(!map.containsKey(sentences[i])){
//                 insert(sentences[i]);
//             }
//             map.put(sentences[i],map.getOrDefault(sentences[i], 0)+times[i]);
//         }
//     }

//     public List<String> input(char c){
//         if(c == '#'){
//             // Whatever is the search term add it inside map i.e. cache
//             String searchTerm = search.toString();
//             if(!map.containsKey(searchTerm)){
//                 insert(searchTerm);
//             }
//             map.put(searchTerm,map.getOrDefault(searchTerm, 0)+1);
//             return new ArrayList<>();
//         }
//         PriorityQueue<String> pq = new PriorityQueue<>((a,b)->{
//             int fa = map.get(a); // good practice to avoid null related errors
//             int fb = map.get(b); // good practice to avoid null related errors
//             if(fa == fb){
//                 return b.compareTo(a);
//             }
//             return fa - fb;
//         });
//         search.append(c);
//         String searchTerm = search.toString();
// /*         We search for all the sentences which startswith our
//         search term and then store the top three out of them 
//         into the priority queue */
//         List<String> li = searchTrie(searchTerm);
//         for(String sentence:li){

//                 pq.add(sentence);
//                 if(pq.size()>3){
//                     pq.poll();
//                 }

//         }

//         List<String> result = new ArrayList<>();
//         while(!pq.isEmpty()){
//             result.add(0,pq.poll());
//         }
//         return result;

//     }

//     public static void main(String[] args){
//         String[] sentences = {"i love you", "island","ironman","i love leetcode"};
//         int[] times = {5,3,2,2};
//         AutoCompleteSystem acs = new AutoCompleteSystem(sentences, times);
//         System.out.println(acs.input('i') + acs.search.toString());
//         System.out.println(acs.input(' ') + acs.search.toString());
//         System.out.println(acs.input('l') + acs.search.toString());
//         System.out.println(acs.input('#') + acs.search.toString());
//     }
// }


// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.PriorityQueue;

// class AutoCompleteSystem{


//     HashMap<String,Integer> map;
//     StringBuilder search;
//     public AutoCompleteSystem(String[] sentences, int[] times){
//         map = new HashMap<>();
//         search = new StringBuilder();
//         // Fill the HashMap
//         for(int i=0;i<sentences.length;i++){
//             map.put(sentences[i],map.getOrDefault(sentences[i], 0)+times[i]);
//         }
//     }

//     public List<String> input(char c){
//         if(c == '#'){
//             // Whatever is the search term add it inside map i.e. cache
//             String searchTerm = search.toString();
//             map.put(searchTerm,map.getOrDefault(searchTerm, 0)+1);
//             return new ArrayList<>();
//         }
//         PriorityQueue<String> pq = new PriorityQueue<>((a,b)->{
//             int fa = map.get(a); // good practice to avoid null related errors
//             int fb = map.get(b); // good practice to avoid null related errors
//             if(fa == fb){
//                 return b.compareTo(a);
//             }
//             return fa - fb;
//         });
//         search.append(c);
//         String searchTerm = search.toString();
// /*         We search for all the sentences which startswith our
//         search term and then store the top three out of them 
//         into the priority queue */
//         for(String sentence:map.keySet()){
//             if(sentence.startsWith(searchTerm)){
//                 pq.add(sentence);
//                 if(pq.size()>3){
//                     pq.poll();
//                 }
//             }
//         }

//         List<String> result = new ArrayList<>();
//         while(!pq.isEmpty()){
//             result.add(0,pq.poll());
//         }
//         return result;

//     }

//     public static void main(String[] args){
//         String[] sentences = {"i love you", "island","ironman","i love leetcode"};
//         int[] times = {5,3,2,2};
//         AutoCompleteSystem acs = new AutoCompleteSystem(sentences, times);
//         System.out.println(acs.input('i') + acs.search.toString());
//         System.out.println(acs.input('r') + acs.search.toString());
//         System.out.println(acs.input('o') + acs.search.toString());
//         System.out.println(acs.input('#') + acs.search.toString());
//     }
// }