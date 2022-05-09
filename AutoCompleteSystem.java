/**
 * 
 * Approach - 1
 * 
 *    Using HashMap and a PriorityQueue
 * 
 */
class AutocompleteSystem {
  Map<String, Integer> map;
  PriorityQueue<String> pq;
  StringBuilder sb;
  public AutocompleteSystem(String[] sentences, int[] times) {
      this.map = new HashMap<>();
      for(int i = 0; i < sentences.length; i++){
          String sent = sentences[i];
          map.put(sent, map.getOrDefault(sent, 0) + times[i]);
      }
      
      sb = new StringBuilder();
      pq = new PriorityQueue<>((a, b) -> {
          if(map.get(a) - map.get(b) == 0){
              return b.compareTo(a); // we want the lexicographically inferior String to be on top so we can remove it when pq.size > 3
          }
          
          return map.get(a) - map.get(b);
      });
  }
  
  public List<String> input(char c) {
      if(c == '#'){
          String temp = sb.toString();
          map.put(temp, map.getOrDefault(temp, 0) + 1);
          sb = new StringBuilder();
          return new ArrayList<>();
      }
      
      List<String> result = new ArrayList<>();
      sb.append(c);
      String partString = sb.toString();
      
      for(String key : map.keySet()){
          if(key.startsWith(partString)){
              pq.add(key);
              if(pq.size() > 3) pq.poll();
          }
      }
      
      while(!pq.isEmpty()){
          result.add(0, pq.poll());
      }
      
      return result;
  }
}

/**
 * Optimization 1 
 * 
 *    Instead of iterating over all the keys in the HashMap on line 41 above,
 *    maintaing all String starting with a prefix in a Trie.
 */

class AutocompleteSystem {
  class TrieNode {
      Set<String> li;
      Map<Character, TrieNode> childrenMap;
      
      public TrieNode(){
          li = new HashSet<>();
          childrenMap = new HashMap<>();
      }
  }
  
  private void insert(String sentence){
      TrieNode curr = root;
      for(int i = 0; i < sentence.length(); i++){
          char c = sentence.charAt(i);
          if(!curr.childrenMap.containsKey(c)){
              curr.childrenMap.put(c, new TrieNode());
          }
          
          curr = curr.childrenMap.get(c);
          curr.li.add(sentence);

      }
  }
  
  private Set<String> prefixSearch(String prefix){
      TrieNode curr = root;
      for(int i = 0; i < prefix.length(); i++){
          char c = prefix.charAt(i);
          if(!curr.childrenMap.containsKey(c)){
              return new HashSet<>();
          }
          curr = curr.childrenMap.get(c);
      }
      
      return curr.li;
  }
  
  Map<String, Integer> map;
  PriorityQueue<String> pq;
  StringBuilder sb;
  TrieNode root;
  public AutocompleteSystem(String[] sentences, int[] times) {
      this.map = new HashMap<>();
      this.root = new TrieNode();
      for(int i = 0; i < sentences.length; i++){
          String sent = sentences[i];
          map.put(sent, map.getOrDefault(sent, 0) + times[i]);
          insert(sent);
      }
      
      sb = new StringBuilder();
      pq = new PriorityQueue<>((a, b) -> {
          if(map.get(a) - map.get(b) == 0){
              return b.compareTo(a); // we want the lexicographically inferior String to be on top so we can remove it when pq.size > 3
          }
          
          return map.get(a) - map.get(b);
      });
      
      
  }
  
  public List<String> input(char c) {
      if(c == '#'){
          String temp = sb.toString();
          map.put(temp, map.getOrDefault(temp, 0) + 1);
          insert(temp);
          sb = new StringBuilder();
          return new ArrayList<>();
      }
      
      List<String> result = new ArrayList<>();
      sb.append(c);
      String partString = sb.toString();
      Set<String> searchTerms = prefixSearch(partString);
      for(String s : searchTerms){
          pq.add(s);
          if(pq.size() > 3) pq.poll();
      }
      
      while(!pq.isEmpty()){
          result.add(0, pq.poll());
      }
      
      return result;
  }
}

/**
 * Optimization - 2
 * 
 * Instead of maintaining all sentences with a prefix in each node on the Trie, only maintain 3 sentences according to their priority.
 * 
 * Time Compexity: O(sentence.length)
 * 
 *    insert (into Trie) -> Takes the time to iterate over the  sentence, after the corresponding node is reached, will return
 *                           top elements in O(1) time,.
 * 
 * Space Complexity: O(26 * n) -> Occupied by the Trie
 *    n -> Lenght of the longest sentence
 *    
 */

class AutocompleteSystem {
  class TrieNode {
      List<String> li;
      Map<Character, TrieNode> childrenMap;
      public TrieNode(){
          li = new ArrayList<>();
          childrenMap = new HashMap<>();
      }
  }
  
  private void insert(String sentence){
      TrieNode curr = root;
      for(int i = 0; i < sentence.length(); i++){
          char c = sentence.charAt(i);
          if(!curr.childrenMap.containsKey(c)){
              curr.childrenMap.put(c, new TrieNode());
          }

          curr = curr.childrenMap.get(c);
          if(!curr.li.contains(sentence)) curr.li.add(sentence);
          
          // Arrange elements in the list according to their priority
          PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
              if(map.get(a) - map.get(b) == 0){
                  return b.compareTo(a);
              }
              return map.get(a) - map.get(b);
          });
          
          
          for(String s : curr.li){
              pq.add(s);
              if(pq.size() > 3) pq.poll();
          }
          
          List<String> topStrings = new ArrayList<>();
          while(!pq.isEmpty()){
              topStrings.add(0, pq.poll());
          }
          curr.li = topStrings;
      }
  }
  
  private List<String> prefixSearch(String prefix){
      TrieNode curr = root;
      for(int i = 0; i < prefix.length(); i++){
          char c = prefix.charAt(i);
          if(!curr.childrenMap.containsKey(c)){
              return new ArrayList<>();
          }
          curr = curr.childrenMap.get(c);
      }
      
      return curr.li;
  }
  
  Map<String, Integer> map;
  PriorityQueue<String> pq;
  StringBuilder sb;
  TrieNode root;
  public AutocompleteSystem(String[] sentences, int[] times) {
      this.map = new HashMap<>();
      this.root = new TrieNode();
      for(int i = 0; i < sentences.length; i++){
          String sent = sentences[i];
          map.put(sent, map.getOrDefault(sent, 0) + times[i]);
          insert(sent);
      }
      
      sb = new StringBuilder();
      pq = new PriorityQueue<>((a, b) -> {
          if(map.get(a) - map.get(b) == 0){
              return b.compareTo(a); // we want the lexicographically inferior String to be on top so we can remove it when pq.size > 3
          }
          
          return map.get(a) - map.get(b);
      });
  }

  public List<String> input(char c) {
      if(c == '#'){
          String temp = sb.toString();
          map.put(temp, map.getOrDefault(temp, 0) + 1);
          insert(temp);
          sb = new StringBuilder();
          return new ArrayList<>();
      }
      
      sb.append(c);
      String partString = sb.toString();
      return prefixSearch(sb.toString());
  }
}
