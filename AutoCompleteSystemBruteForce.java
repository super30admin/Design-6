public class AutoCompleteSystemBruteForce
{
    Map<String, Integer> map;
    PriorityQueue<String> priorityQueue;
    StringBuilder stringBuilder;

    public AutoCompleteSystemBruteForce(String[] sentences, int[] times)
    {
        this.map = new HashMap<>();
        this.priorityQueue = new PriorityQueue<>((a,b)-> {
            int countA = this.map.get(a);
            int countB = this.map.get(b);

            if(countA == countB) {
                return b.compareTo(a);
            }

            return countA - countB;
        });
        this.initHashMap(sentences, times);
        this.stringBuilder = new StringBuilder();
    }

    public List<String> input(char c)
    {
        if(c == '#') {
            String currentString = this.stringBuilder.toString();
            this.map.put(currentString, this.map.getOrDefault(currentString, 0) + 1);
            this.stringBuilder = new StringBuilder();
            return new ArrayList<>();
        }
        else {
            this.stringBuilder.append(c);
            String currentString = this.stringBuilder.toString();

            for(String string: this.map.keySet()) {
                if(string.startsWith(currentString)) {
                    this.priorityQueue.add(string);
                }
            }

            this.resizePriorityQueue();

            List<String> result = getSearchResults();
            this.priorityQueue.clear();
            return result;
        }
    }

    private void initHashMap(String[] sentences, int[] times)
    {
        for(int i = 0; i < sentences.length; i++) {
            this.map.put(sentences[i], this.map.getOrDefault(sentences[i],0 ) + times[i]);
        }
    }

    private void resizePriorityQueue()
    {
        while(this.priorityQueue.size() > 3) {
            this.priorityQueue.poll();
        }
    }

    private List<String> getSearchResults()
    {
        List<String> output = new ArrayList<>();
        while(!this.priorityQueue.isEmpty()) {
            output.add(0, this.priorityQueue.poll());
        }

        return output;
    }
}