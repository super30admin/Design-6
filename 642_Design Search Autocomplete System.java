// "static void main" must be defined in a public class.
/*
 * Take a map, in constructor store all the string, and its freq to map. 
 while getting inut string, if we ever reach '#' we add that string to map, and clear search. to reinitiate the search.\

Else. if there's another charatcer, we'll add it to the search string. 
now, we'll go to map's keyset, and check if there's any key string that starts with "Search"; if so; we'll add it to the heap
 based on conditions given, we just want to return 3 high freq string. and when freq are equal we return lexicographically smaller one.
 *so  we'll use min heap of size 3; why min heap? cause, when we go through all the keysets, we add it to the heap, and once ithe size is greater than 3; we'll remove the string that has lower frequencies. 
 * at lst, heap will have 3 most recent/ high freq string. 
 * we'll poll it and add it to the result in Descending order, and return.
 * 
 * TC: O(nk) // nk = N; for all the input we check stringstartswith over all the keyset, and perform all operations - adding to hep, building list etc. n = for length of String array; k times the charatcer + hashing over length of string for umbe of times character entered.
 * sc: O(n)+ o(k) - > map+ minheap
 */

public class Main {

    // autocomplete system
    static class AutoCompleteSystem {

        Map<String, Integer> map;
        String search;

        public AutoCompleteSystem(String[] sentences, int[] times) {
            map = new HashMap<>();
            search = "";

            for (int i = 0; i < times.length; i++) {
                map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
            }
        }

        public List<String> input(char c) {
            // end of the string
            if (c == '#') {
                map.put(search, map.getOrDefault(search, 0) + 1);
                search = "";
                return new ArrayList<>();
            }
            search = search + c;
            System.out.println("/n input char :" + c + "  final string: " + search);
            // take a pq, and add all the enttries from map to it; it's min heap

            PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
                if (map.get(a) == map.get(b))
                    return b.compareTo(a); // we want to remove lexicographically bigger string
                else
                    return map.get(a) - map.get(b);
            });

            // traverse through all the keys and put in minheap, to store string with higher
            // freq
            for (String key : map.keySet()) {
                if (key.startsWith(search)) {
                    pq.add(key);

                    if (pq.size() > 3)
                        pq.poll();
                }
            }

            // we've added all keys to PQ, and now PQ has only 3 mstrings that got the
            // highest freq.
            System.out.println("Queue size for " + search + "  is : " + pq.size());
            List<String> result = new ArrayList<>();
            while (!pq.isEmpty()) {
                result.add(0, pq.poll());
            }

            return result;
        }
    }

    public static void main(String[] args) {
        // System.out.println("Hello World!");

        AutoCompleteSystem acs = new AutoCompleteSystem(
                new String[] { "abc", "ad", "ac", "ab", "aa", "k", "ks", "ksd", "kf", "kq", "aba", "abd", "pq", "pqr",
                        "pqrs", "prs", "ayt", "aty", "aox", "axd" },
                new int[] { 1, 2, 1, 5, 1, 6, 1, 5, 4, 3, 9, 1, 1, 4, 3, 2, 2, 2, 2, 2 });

        List<String> res1 = acs.input('a');
        if (res1 == null)
            System.out.println("It's Empty");
        System.out.print("List1: ");
        for (String str : res1) {
            System.out.print("  " + str);
        }
        res1 = acs.input('b');
        System.out.println("'ab' : Answer : " + res1.toString());
        // res1 = acs.input('a');
        // System.out.println("'a' : Answer : "+res1.toString());
    }
}