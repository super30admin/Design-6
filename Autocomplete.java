/*

WORKED ON LC : YES

SPACE COMPLEXITY : O(n*(3*MAX(l))) for Trie and HashMap O(n) 

we are storing 3 words every node ( for 3 word we have max. space complexity = 3 * longest word in system( we only have 3 elements so sorting is O(1))
TIME COMPLEXITY : INSERT a single search query O(L) , total insert complexity O(n*l) for n words
Autocomplete Time complexity : O(l)


*/


class AutocompleteSystem {
    
     private class TrieNode  {
        
        Map<Character,TrieNode> children ;
        
        List<String> words;
        TrieNode() {
            words = new ArrayList();
            children = new HashMap();
            
        }
        
    }
    
    
    Map<String,Integer> hotCounter;
    TrieNode root = new TrieNode();
    StringBuilder Query = new StringBuilder();
    
    
    
    private void insert(String word, int rank) {    
        char[] arr = word.toCharArray();
        TrieNode tempRoot = root;
        for(int i=0;i<arr.length; i++) {
            if(!tempRoot.children.containsKey(arr[i])){
                tempRoot.children.put(arr[i], new TrieNode());
            }
            tempRoot = tempRoot.children.get(arr[i]);
            
            List<String> temp = tempRoot.words;
            if(!temp.contains(word)){
                temp.add(word);    
            }
            
            Collections.sort(temp, (a,b) -> {
                //System.out.println( " a:  " + a + " b : "+b); 
                
                if(hotCounter.get(a) == hotCounter.get(b)) {
                    return a.compareTo(b);
                }
                return hotCounter.get(b) - hotCounter.get(a);
            });
            
            if(temp.size() > 3) {
                temp.remove(3);
            }
            
            tempRoot.words = temp;
        }
    }

     
    private List<String> startsWith(String word) {
        char[] arr = word.toCharArray();
        TrieNode tempRoot = root;
        for(int i=0;i<arr.length; i++) {
            if(!tempRoot.children.containsKey(arr[i])){
                return new ArrayList();
            }
            tempRoot = tempRoot.children.get(arr[i]);
        }
        return tempRoot.words;
    }

    
    public AutocompleteSystem(String[] sentences, int[] times) {
        hotCounter = new HashMap();
        // add initial dictionary to trie
        for(int i=0;i< sentences.length; i++) {
            hotCounter.put(sentences[i], hotCounter.getOrDefault(sentences[i],0)+ times[i]);
            insert(sentences[i], times[i]);
            
        }  
    }
    
    public List<String> input(char c) {
        
        if(c == '#') {
            
            String input = Query.toString();
            Query = new StringBuilder();
            hotCounter.put(input, hotCounter.getOrDefault(input,0)+1);
            
            insert(input,1);
            
            return new ArrayList();
        }
        
        Query.append(c);
        String input = Query.toString();
            
        return startsWith(input);
    
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
