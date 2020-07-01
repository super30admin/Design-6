//Leetcode: 642. Design Search Autocomplete System
//Time complexity: O(n*alogk) // n is no of sentences, a is avg length of each sentence and k =3.
//Space Complexity: O(2n+3) // List and hashmap of size n, while heap of size 3.
class AutocompleteSystem {
    class Node{
        String s;
        int freq;
        Node(String s, int freq){
            this.s= s;
            this.freq= freq;
        }
    }
    PriorityQueue<Node> pq;
    String temp;
    List<String> sList;   
    HashMap<String, Integer> hm;
    public AutocompleteSystem(String[] sentences, int[] times) {
        temp="";
        sList= new ArrayList<>(Arrays.asList(sentences));      
        hm = new HashMap<>();       
        for (int i=0;i<sList.size();i++){
            hm.put(sList.get(i),times[i]);
        }      
    }    
    public List<String> input(char c) {
        //Min heap and if same freq maxm ascii will be on top. 
         pq = new PriorityQueue<>((a,b)->(a.freq==b.freq? (b.s.compareTo(a.s)):a.freq-b.freq )); 
        List<String> lst = new ArrayList<>();   
        if(c=='#'){
            if(hm.containsKey(temp)){
                hm.put(temp,hm.get(temp)+1);
            }
            else{
                sList.add(temp);
                hm.put(temp,1);
            }
           temp="";
            return lst;
        }
        else{
            temp=temp+c; 
        }
        for (int i=0;i<sList.size();i++){
            if(sList.get(i).startsWith(temp)){
                pq.offer(new Node(sList.get(i),hm.get(sList.get(i))));
                if(pq.size()>3) pq.poll();
            }
        }        
        while(!pq.isEmpty()){
            Node n = pq.poll();
            lst.add(n.s);
        }        
        Collections.reverse(lst);
        return lst;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */