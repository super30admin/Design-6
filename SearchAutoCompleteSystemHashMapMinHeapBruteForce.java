import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class SearchAutoCompleteSystemHashMapMinHeapBruteForce {

        // Brute Force - Hash Map - Time O(N) = O(log(n)) and space O(n)

        HashMap<String, Integer> map;

        public SearchAutoCompleteSystemHashMapMinHeapBruteForce(String[] sentences, int[] times) {

            map = new HashMap<>();

            int n = sentences.length;

            // build sentence frequency map
            for(int i = 0; i < n; i++) {     // O(n)

                String sentence = sentences[i];
                int frequency = times[i];

                map.put(sentence, map.getOrDefault(sentence, 0) + frequency);
            }

        }

        StringBuilder sb = new StringBuilder();

        public List<String> input(char c) {              // O(N)

            // current search sentence is over and be added to frequency map for future inputs
            if(c == '#') {

                String newSentence = sb.toString();

                map.put(newSentence, map.getOrDefault(newSentence, 0) + 1);

                // make search string builder empty again as current search sentence has just ended
                sb = new StringBuilder();

                // output an empty list when sentence finishes
                return new ArrayList<>();
            }

            // maintain a Min Heap of sentences frequency wise
            PriorityQueue<String> topThree = new PriorityQueue<>((a, b) -> {

                // if frequency matches, heapify by lexicographical order
                if(map.get(a) == map.get(b)) {

                    return b.compareTo(a);
                }

                // min heap condition
                return map.get(a) - map.get(b);
            });

            // add input character to search string builder
            sb.append(c);

            String searchStr = sb.toString();

            // search for this new search string in frequency map for top three search with it as prefix
            for(String sentence: map.keySet()) {

                // add those sentences having search string as prefix to min heap
                if(sentence.startsWith(searchStr)) {

                    topThree.add(sentence);

                    // limit min heap size to 3 as only top 3 searches are asked for
                    if(topThree.size() > 3) {

                        topThree.poll();
                    }
                }
            }

            List<String> result = new ArrayList<>();

            while(!topThree.isEmpty()) {

                // min heap root has minimum frequency and the highest frequency sentence will be at the last of min heap which has to be appended to the front of result list
                result.add(0, topThree.poll());
            }

            // output list for every input character
            return result;
        }

        public static void main(String[] args) {

            String[] sentences = {"i love you", "island", "iroman", "i love leetcode"};
            int[] times = {5, 3, 2, 2};

            SearchAutoCompleteSystemHashMapMinHeapBruteForce obj =
                    new SearchAutoCompleteSystemHashMapMinHeapBruteForce(sentences, times);

            char[] inChar = {'i', ' ', 'a', '#'};

            for(int i = 0; i < 4; i++) {

                List<String> ls = obj.input(inChar[i]);

                System.out.println("");
                System.out.println("Top three searches now: ");

                for (String str : ls) {

                    System.out.println(str);
                }

                if (inChar[i] == '#') System.out.println("Search sentence completely entered now");
            }
        }


}

/*
TIME COMPLEXITY = O(N)

For, MinHeap time = O(klogN) = O(log(n)) = O(N)

k = 3 = constant
n = length of input sentences and times arrays

SPACE COMPLEXITY = O(n)
Hash Map space = O(n)
*/

/*
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */