// 642.

//time - O(length of input ^ 2)
//space - O(length of sentences[] + length of input)
class AutocompleteSystem {
    
    HashMap<String, Integer> database; //map of sentence to its freq
    String inputTracker; //current i/p query string - maintained till # is pressed
    int MAX_RESULT_SIZE; //number of sugesstions in result
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        inputTracker = "";
        database = new HashMap<>();
        MAX_RESULT_SIZE = 3;
        
        for(int i = 0; i < sentences.length; i++)
        {
            //no duplicates in sentences[] - else handlle duplicates
            database.put(sentences[i], times[i]);
        }
    }
    
    public List<String> input(char c) {
        //if c = '#' - handle enter
        if(c == '#')
        {
            if(inputTracker.length() != 0) //if there are chars in input tracker, add he input tracker string to db
            {
                if(database.containsKey(inputTracker))
                {
                    database.put(inputTracker, database.get(inputTracker) + 1);
                }
                else
                {
                    database.put(inputTracker, 1);
                }
            }
            inputTracker = ""; //reset input tracker to empty
            return new ArrayList<>(); //return empty list
        }
        //else append c to input
        inputTracker += c;
        
        //build pq
        //pq is max heap with sentence frequence (lexicographic order in case of same frequency)
        PriorityQueue<Pair> support = new PriorityQueue<>((a, b) -> (a.count == b.count ? a.sentence.compareTo(b.sentence) : b.count - a.count));
        for(String sentence : database.keySet()) //add all sentences that starts with input tracker into pq
        {
            if(sentence.startsWith(inputTracker))
            {
                support.offer(new Pair(sentence, database.get(sentence)));
            }
        }
        
        //populate result and output
        List<String> result = new ArrayList<>();
        while(!support.isEmpty() && result.size() < MAX_RESULT_SIZE)
        {
            result.add(support.poll().sentence);
        }
        return result;
    }
}

public class Pair {
    String sentence;
    int count;
    
    public Pair(String sentence, int count) {
        this.sentence = sentence;
        this.count = count;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
