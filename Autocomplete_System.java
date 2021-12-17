class AutocompleteSystem{
  HashMap<String, Integer>;
  TrieNode root;
  
  class TrieNode{
    List<String> pq;
    HashMap<Character, TrieNode> children;
    public TrieNode(){
      pq = new ArrayList<>();
      children = new HashMap<>();
    }
  }
  
  private void insert(String Sentence, int time){
    TrieNode curr = root;
    for(int i = 0; i < sentence.length; i++){
      char c  = sentence.charAt(i);
      if(!curr.children.containsKey(c)){
        curr.children.put(c, new TrieNode());
      }
      curr = curr.children.get(c);
      List<String> currLi = curr.pq;
      if(!.currLi.contains(sentence)){
        currLi.add(sentence);
      }
      Collections.sort(currLi,(a,b) -> {
        if(map.get(a) == map.get(b)){
          return a.compareTo(b);
        }
        return map.get(b) - map.get(a);
      });
      
      if(currLi.size() > 3){
          currLi.remove(3);
      }
      curr.pq = currLi;
      
    }
  }
  private List<String> search(String input){
      TrieNode curr = root;
      for(int i = 0; i < input.length(); i++){
        char c = input.charAt(i);
        if(!curr.children.containsKey(c)){
          return new ArrayList<>();
        }
        curr = curr.children.get(c);
      }
    return curr.pq;
  }
  StringBuilder input;
  public AutocompleteSystem(String[] sentences, int[] times){
    input = new StringBuilder();
    map = new HashMap<>();
    root = new TrieNode<>();
    for(int i= 0; i < sentences.length; i++){
      map.put(sentences[i], map.getOrDefault(sentences[i], 0)+ times[i]);
      insert(sentences[i], times[i]);
    }
  }
  
  public List<String> input(char c){
    if(c == '#'){
      String in = sb.toString();
      map.put(in, map.getOrDefault(in, 0) + 1);
      insert(in, 1);
      sb = new StringBuilder();
      return new ArrayList<>();
    }
    sb.append(c);
    string in = sb.toString();
    return search(in);
  }
}

//TC: Space Optimized Trie O(N) + O(n) + O(1)

        
