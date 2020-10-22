//Time Complexity-O(n*mlog3)
//Space Complexity-O(n)
class AutocompleteSystem {
    HashMap<String,Integer>map=new HashMap();
    String input_str="";
    public AutocompleteSystem(String[] sentences, int[] times) {
        for(int i=0;i<sentences.length;i++)
        {
            map.put(sentences[i],times[i]);
        }
    }
    
    public List<String> input(char c) {
        if(c=='#')
        {
            map.put(input_str,map.getOrDefault(input_str,0)+1);
            input_str="";
            return new ArrayList();
        }
        input_str+=c;
        PriorityQueue<minHeapPair> pq= new PriorityQueue<>((a,b)->(b.count!=a.count?a.count-b.count:b.sentence.compareTo(a.sentence)));
    
        for(String key:map.keySet())
        {
            if(key.startsWith(input_str))
            {
                pq.add(new minHeapPair(key,map.get(key)));
            }
            if(pq.size()>3)
            {
                pq.remove();
            }
        }
        List<String>output=new ArrayList();
        while(!pq.isEmpty())
        {
            output.add(pq.remove().sentence);
        }
        Collections.reverse(output);
        return output;
    }
}


 class minHeapPair{
     String sentence;
     int count;
    minHeapPair(String sentence,int count)
    {
        this.sentence=sentence;
        this.count=count;
    }
}



/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */