import java.util.Collection;
import java.util.HashMap;

//TimeComplexity:O(1)

class AutoCompleteSystem {
    HashMap<String, Integer> map;
    StringBuilder input;
        class TrieNode {
            TrieNode []children;
            List<String> startsWith;
            TrieNode() {
                this.startsWith = new ArrayList<>();
                this.children = new TrieNode[256];
            }
        }

        AutoCompleteSystem(String []sentences, int []times) {
           this.map = new HashMap<>();
           for(int i =0; i<sentences.length;i++) {
            String eachSentence = sentences[i];
            if(!map.containsKey(eachSentence)) {
                map.put(eachSentence,0);
            }
            map.put(eachSentence, map.get(eachSentence)+1);
           }

        }

        void insert(TrieNode root, String word) {
            TreeNode current = root;
            for(int i =0; i<word.length();i++) {
                char c = word.charAt(i);
                if(current.children[c-' '] == null) {
                    current.children[c-' '] = new TrieNode();
                } 
                    current = current.children[c-' '];
                    List<String> mylist = current.startsWith;
                    if(!mylist.contains(word)) {
                       mylist.add(word);
                    }
                    Collections.sort(mylist, ((a,b)-> {
                         if(map.get(a) == map.get(b)) {
                            return a.compareTo(b);
                         }

                        return map.get(b)-map.get(a);
                    }));

                    if(mylist.size()>3) {
                        mylist.remove(mylist.size()-1);
                    }
            }

        }

        List<String> search(TrieNode root, String prefix) {
            TrieNode current = root;
            for(int i =0; i<prefix.length();i++) {
                char c = prefix.charAt(c);
                if(current.children[c-' '] == null) {
                    return new ArrayList<>();
                }
                current = current.children[c-' '];
                current.startsWith.add(word);
            }
           
          return current.startsWith;
        }
        TrieNode root;
        List<String> input(Char c) {
            if(c == '#') {
                String word = input.toString();
                if(!map.containsKey(word)) {
                    map.put(word,0);
                
                }
                map.put(word, map.get(word)+1);
                insert(root,word);
                input = new StringBuilder();
                return new ArrayList<>();
            }
            input.append(c);
            String prefix = input.toString();
            return search(root,prefix);

        }

        


}