//Time Complexity O(1)
//Space Complexity O(M+N)
//Leetcode tested

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AutoCompleteSystem {
    TrieNode root,cur;
    StringBuilder sb;
    public AutoCompleteSystem(String[] sentences,int[] times){
        root = new TrieNode();
        cur = root;
        sb = new StringBuilder();

        for (int i = 0; i < times.length; i++) {
            add(sentences[i],times[i]);
        }

    }
    public void add(String sentence, int t){
        TrieNode tmp = root;

        List<TrieNode> visited = new ArrayList<>();
        for (char c: sentence.toCharArray()) {
            if(tmp.children[c] == null){
                tmp.children[c] = new TrieNode();
            }
            visited.add(tmp);
            tmp = tmp.children[c];
        }
        tmp.s = sentence;
        tmp.times += t;
        for (int i = 0; i < visited.size(); i++) {
            visited.get(i).update(tmp);
        }

    }
    public List<String> input(char c){
        List<String> result = new ArrayList<>();
        if(c == '#'){
            add(sb.toString(),1);
            sb = new StringBuilder();
            cur = root;
            return result;
        }
        sb.append(c);
        if(cur != null){
            cur = cur.children[c];
        }
        if(cur == null){
            return result;
        }

        for (TrieNode node:cur.hot) {
            result.add(node.s);
        }
        return result;
    }
    class TrieNode  implements Comparable<TrieNode>{
        TrieNode children[];
        String s;
        int times;
        List<TrieNode> hot;

        TrieNode(){
            children = new TrieNode[128];
            s=null;
            times = 0;
            hot  = new ArrayList<>();
        }

        @Override
        public int compareTo(TrieNode o) {
            if(this.times == o.times){
                return this.s.compareTo(o.s);
            }
            return o.times - this.times;
        }

        public void update(TrieNode node){
            if(!this.hot.contains(node)){
                this.hot.add(node);
            }
            Collections.sort(hot);
            if(hot.size() > 3){
                hot.remove(hot.size()-1);
            }
        }
    }
}
