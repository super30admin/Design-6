import java.util.*;
public class DesignAutocompleteSystem {
    HashMap<String, Integer> map;
    String search;

    public DesignAutocompleteSystem(String[] sentences, int[] times) {
        this.search = "";
        this.map = new HashMap<>();

        for(int i=0; i<sentences.length; i++){
            map.put(sentences[i], map.getOrDefault(sentences[i], 0)+ times[i]);
        }
    }
    public List<String> input(char c) {
        if(c == '#'){
            map.put(search, map.getOrDefault(search, 0)+1);
            search = "";
            return new ArrayList<>();
        }

        search += c;
        PriorityQueue<String> pq = new PriorityQueue<>((a,b) ->{
            if(map.get(a) == map.get(b)){
                return b.compareTo(a);
            }
            return map.get(a) - map.get(b);
        });

        List<String> matches = new ArrayList<>();
        for(String sentence: map.keySet()){
            if(sentence.startsWith(search)){
                pq.add(sentence);
                if(pq.size() > 3){
                    pq.poll();
                }
            }
        }

        List<String> result = new ArrayList<>();
        while(!pq.isEmpty()) result.add(pq.poll());
        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        //["AutocompleteSystem", "input", "input", "input",              "input"]
        //[[["i love you",       "island", "iroman", "i love leetcode"], [5, 3, 2, 2]], ["i"], [" "], ["a"], ["#"]]
        //Output: [null, ["i love you", "island", "i love leetcode"], ["i love you", "i love leetcode"], [], []]
        String[] sentences = {"i love you", "island", "iroman", "i love leetcode"};
        int[] times = {5, 3, 2, 2};

        DesignAutocompleteSystem system = new DesignAutocompleteSystem(sentences, times);

        List<String> res1 = system.input('i');
        System.out.println(res1);

        List<String> res2 = system.input(' ');
        System.out.println(res2);

        List<String> res3 = system.input('a');
        System.out.println(res3);

        List<String> res4 = system.input('#');
        System.out.println(res4);
    }
}
