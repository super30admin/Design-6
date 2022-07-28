import java.util.*;

public class AutocompleteSystem {
    TrieNode root;
    HashMap<String, Integer> sentMap = new HashMap<>();
    StringBuilder sb;
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        sb = new StringBuilder();
        for(int i=0; i<sentences.length; i++){
            sentMap.put(sentences[i], sentMap.getOrDefault(sentences[i], 0) + times[i]);
            insert(sentences[i]);
        }
    }

    public List<String> input(char c) {
        if(c == '#'){
            String s = sb.toString();
            sentMap.put(s, sentMap.getOrDefault(s, 0) + 1);
            insert(sb.toString());
            sb = new StringBuilder();
            return new ArrayList<>();
        }
        sb.append(c);
        List<String> res = search(sb.toString());
        return res;
    }

    private List<String> search(String sent){
        TrieNode curr = root;
        for(int i=0; i<sent.length(); i++){
            char c = sent.charAt(i);
            int idx = c-' ';
            if(curr.childrens[idx] != null){
                curr = curr.childrens[idx];
            } else {
                return new ArrayList<>();
            }
        }
        return curr.topRes;
    }

    private void insert(String sentence){
        TrieNode curr = root;
        for(int i=0; i<sentence.length(); i++){
            char c = sentence.charAt(i);
            int idx = c-' ';
            if(curr.childrens[idx] == null){
                curr.childrens[idx] = new TrieNode();
            }
            List<String> pq = curr.childrens[idx].topRes;
            if(!pq.contains(sentence)){
                pq.add(sentence);
            }

            Collections.sort(pq, new PQComparator());
            if(pq.size() > 3){
                pq.remove(pq.size()-1);
            }
            curr = curr.childrens[idx];
        }
        System.out.println("\n");
    }
    class PQComparator implements Comparator<String> {
        public int compare(String a, String b){
            if(sentMap.get(a) == sentMap.get(b)){
                return a.compareTo(b);
            }
            return sentMap.get(b) - sentMap.get(a);
        }
    }
}

class TrieNode {
    List<String> topRes;
    TrieNode[] childrens = new TrieNode[256];
    TrieNode(){
        childrens = new TrieNode[256];
        topRes = new ArrayList<>();
    }
}