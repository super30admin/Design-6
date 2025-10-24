/*
642. Design Search Autocomplete System
Ran on leetcode: Yes
SC: O(M * L) M is number of sentences and L is average length of the sentence
TC: insert - O(L) L length of sentence, input - O(P) length of prefix
Create a trie, which has all the sentences added to it and each letter should have top3 words associaated with it. When doing input if we see # input it in freq map and in trie else search for it in trie and return top3 word associated with the prefix.
*/

class AutocompleteSystem {

    private Map<String, Integer> wordCountMap;
    private String search;
    private TrieNode root;

    class TrieNode{
        TrieNode[] children;
        List<String> top3Words;

        TrieNode(){
            this.children = new TrieNode[256];
            this.top3Words = new ArrayList<>();
        }
    }

    // Add the the word to the trie, each letter should have top3 words for it
    private void insert(String word){
        TrieNode curr = root;
        for(char letter: word.toCharArray()){ // Lopp throuh word and create trienode for each letter
            if(curr.children[letter - ' '] == null){
                curr.children[letter - ' '] = new TrieNode();
            }
            curr = curr.children[letter - ' '];
            List<String> currList = curr.top3Words;
            if(!currList.contains(word)){ // if it does not have the word add it
                currList.add(word);
            }
            Collections.sort(currList, (a, b) -> { // sort it based on freq or lexographically if freq is same
                if(wordCountMap.get(a) == wordCountMap.get(b)){
                    return a.compareTo(b);
                }
                return wordCountMap.get(b) - wordCountMap.get(a);
            });
            if(currList.size() > 3) currList.remove(currList.size() - 1); // remove last word to maintain 3 words
        }
    }

    // search for the prefix in the trie and return top 3 words for it
    private List<String> searchPrefix(String prefix){
        TrieNode curr = root;
        for(char letter: prefix.toCharArray()){ // search for the prefix if not found then return empty list
            if(curr.children[letter - ' '] == null){
                return new ArrayList<>();
            }
            curr = curr.children[letter - ' ']; 
        }
        return curr.top3Words;
    }

    public AutocompleteSystem(String[] sentences, int[] times) {
        this.wordCountMap = new HashMap<>();
        this.search = "";
        this.root = new TrieNode();

        for(int i = 0; i < sentences.length; i++){
            wordCountMap.put(sentences[i], wordCountMap.getOrDefault(sentences[i], 0) + times[i]); // Create freq map
            insert(sentences[i]); // insert all the words
        }
    }
    
    // If input is # save the word and reset. Else search for the prefix and return top 3 words.
    public List<String> input(char c) {
        if(c == '#'){
            wordCountMap.put(search, wordCountMap.getOrDefault(search, 0) + 1); // add it to the freq map
            insert(search); // insert in trie node
            this.search = ""; // reset the search
            return new ArrayList<>(); 
        }
        search += c; // update search
        return searchPrefix(search); // return top 3 words for the search
    }
}