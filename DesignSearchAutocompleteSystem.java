import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
// Time Complexity :O(nk)
// Space Complexity : O(nk)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this :getting started

// Your code here along with comments explaining your approach
public class DesignSearchAutocompleteSystem {

    //TrieNode class
    class TrieNode implements Comparable<TrieNode>{
        TrieNode[] children;
        String s;
        int times;
        List<TrieNode> hot;

        //constructor
        public TrieNode(){
            children = new TrieNode[128];//number of ascii characters
            s=null;
            times = 0;
            hot = new ArrayList<>();
        }

        public int compareTo(TrieNode o){
            if(this.times == o.times){
                return this.s.compareTo(o.s);
            }
            return o.times - this.times;
        }

        //update
        public void update(TrieNode node){
            if(!this.hot.contains(node)){
                this.hot.add(node);
            }

            Collections.sort(hot);

            if(hot.size() > 3) hot.remove(hot.size()-1);
        }
    }

    class AutocompleteSystem {
        TrieNode root, curr;
        StringBuilder sb;

        public AutocompleteSystem(String[] sentences, int[] times) {
            root = new TrieNode();
            curr = root;
            sb = new StringBuilder();

            for (int i = 0; i < times.length; i++) {
                add(sentences[i], times[i]);
            }
        }

        //add to trienode
        public void add(String sentence, int times){
            TrieNode tmp = root;

            List<TrieNode> visited = new ArrayList<>();
            for(char c:sentence.toCharArray()){
                if(tmp.children[c] == null){
                    tmp.children[c] = new TrieNode();
                }
                tmp = tmp.children[c];
                visited.add(tmp);
            }
            tmp.s = sentence;
            tmp.times += times;

            for(TrieNode node: visited){
                node.update(tmp);
            }
        }

        public List<String> input(char c) {
            List<String> result = new ArrayList<>();
            if(c == '#'){
                add(sb.toString(), 1);
                sb = new StringBuilder();
                curr = root;
                return result;
            }
            sb.append(c);
            if(curr != null){
                curr = curr.children[c];
            }

            if(curr == null) return result;
            for(TrieNode node: curr.hot){
                result.add(node.s);
            }

            return result;
        }
    }

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */


}
