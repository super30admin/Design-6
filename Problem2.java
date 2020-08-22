//Time Complexity: O(nm*nlog(n)) nlogn = because we are adding a string at each node and it take nlogn time to sort in PQ.
//Space Complexity: O(nm) n=sent.length, m=avg length of word

class AutoCompleteSystem {
    HashMap<String, Integer> map;
    StringBuilder input;

    class Node {
        Node[] link = new Node[128];
        PriorityQueue<String> q = new PriorityQueue<>((s1, s2) -> {
            if (map.get(s1) < map.get(s2))
                return 1;
            else if (map.get(s1) > map.get(s2))
                return -1;
            else {
                return s1.compareTo(s2);
            }
        });
    }

    Node root, curr;

    public AutoCompleteSystem(String[] sent, int[] times) {
        map = new HashMap<>();
        input = new StringBuilder();
        root = new Node();

        // making hashmap
        for (int i = 0; i < sent.length; i++) {
            map.put(sent[i], times[i]);
        }

        // creating dictionary using tries
        for (String s : sent) {
            curr = root;
            for (char c : s.toCharArray()) {
                curr.link[c] = new Node();
                curr.q.offer(s);
                curr = curr.link[c];
            }
        }

        curr = root;
    }

    public List<String> input(char c) {
        if (c == '#') {
            map.put(input.toString(), map.getOrDefault(input, 0) + 1);
            input = new StringBuilder();
            curr = root;
            return new ArrayList<String>();
        }

        input.append(c);
        curr = curr.link[c];

        if (curr == null) {
            return new ArrayList<String>();
        } else {
            if (curr.q.size() <= 3)
                return new ArrayList<String>(curr.q);
            else {
                ArrayList<String> li = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    li.add(curr.q.poll());
                }
                return li;
            }
        }
    }
}