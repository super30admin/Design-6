class AutocompleteSystem {
    //tc-o(n) + map(o(n))
    HashMap<String, Integer> map;
    StringBuilder inputs;
    public AutocompleteSystem(String[] sentences, int[] times) {
        this.map = new HashMap<>();
        this.inputs = new StringBuilder();

        for(int i=0;i<sentences.length;i++)
        {
            String sentence = sentences[i];
            map.put(sentence, map.getOrDefault(sentence,0)+times[i]);
        }

    }
    
    public List<String> input(char c) {
        if(c == '#')
        {
            String search = inputs.toString();
            map.put(search, map.getOrDefault(search,0)+1);
            inputs = new StringBuilder();
            return new ArrayList<>();

        }
        inputs.append(c);
        PriorityQueue<String> pq = new PriorityQueue<>((a,b)->{
            if(map.get(a) == map.get(b))//when freq are equal smaller letter will have precendence
            {
                return b.compareTo(a);// 
                /*String myStr1 = "ir";
String myStr2 = "is";
System.out.println(myStr2.compareTo(myStr1)); s2.comapres1 retruns 1 , s1comapres2 retruns -1
  String myStr1 = "ii";//1st
String myStr2 = "im";//4nd
System.out.println(myStr1.compareTo(myStr2));//i-m = -4*/
            }
            else
            {
                return map.get(a) - map.get(b);//string with lesser freq should be on the top
            }
         });
         String prefix = inputs.toString();
         for(String key : map.keySet())
         {
             if(key.startsWith(prefix))
             {
                 pq.add(key);
             }
             if(pq.size()>3)
             {
               pq.poll();
             }
         }
        List<String> result = new ArrayList<>();
        while(!pq.isEmpty())
        {
            result.add(0,pq.poll());
        }
        return result;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */