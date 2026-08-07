//problem1

class PhoneDirectory {
    HashSet<Integer> li;
    Queue<Integer> q;
     public PhoneDirectory(int maxNumbers) {
         this.li=new HashSet<>();
         this.q=new LinkedList<>();
         for(int i=0;i<maxNumbers;i++){
             li.add(i);
             q.add(i);
         }
     }
     public int get() {
         if(q.isEmpty()) return -1;
         int r=q.poll();
         li.remove(r);
         return r;
         }
         public boolean check(int number) {
             if(!li.contains(number)) return false;
                  return true;
         }
         public void release(int number) {
             if(li.contains(number)) return;
             li.add(number);
             q.add(number);
         }
  //problem2
class AutocompleteSystem {
    HashMap<String,Integer> map;
    trieNode root;
    String search;
    class trieNode{
        trieNode[] children;
        List<String> top30res;
        public trieNode(){
            this.children=new trieNode[256];
            this.top30res=new ArrayList<>();
        }
    }
    private void insert(String word) {
        trieNode curr=root;
        for(char ch:word.toCharArray()){
            if(curr.children[ch-' ']==null) {
                curr.children[ch-' ']=new trieNode();
            }
            curr=curr.children[ch-' '];
            List<String>list=curr.top30res;
            if(!list.contains(word)){
                list.add(word);
            }
            Collections.sort(list,(a,b)->{
                if(map.get(a).equals(map.get(b))){
                return a.compareTo(b);
                }else{
                return map.get(b)-map.get(a);
                }
            });
            if(list.size()>3) list.remove(list.size()-1);
        }
    }
 private List<String> searchPrefix(String prefix) {
        trieNode curr=root;
        for(char ch:prefix.toCharArray()){
            if(curr.children[ch-' ']==null)
               return new ArrayList<>();
            curr=curr.children[ch-' '];
        }
        return curr.top30res;
    }
public AutocompleteSystem(String[] sentences, int[] times) {
    this.map=new HashMap<>();
    this.root=new trieNode();
    this.search="";
    for(int i=0;i<sentences.length;i++){
        map.put(sentences[i],map.getOrDefault(sentences[i],0)+times[i]);
        insert(sentences[i]);
    }
}
public List<String> input(char c) {
    if(c=='#'){
        map.put(search,map.getOrDefault(search,0)+1);
        insert(search);
        search="";
        return new ArrayList<>();
    }
    search+=c;
    return searchPrefix(search);
}
}
