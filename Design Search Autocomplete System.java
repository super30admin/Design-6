/*
class Wrapper:
    def __init__(self, sentence, count):
        self.sentence = sentence
        self.count = count
    
    def __lt__(self, other):
        if self.count == other.count:
            return other.sentence < self.sentence
        return self.count < other.count
    
class AutocompleteSystem:
    def __init__(self, sentences: List[str], times: List[int]):
        self.map = dict()
        self.inputstr = ""
        for i in range(len(sentences)):
            self.map[sentences[i]] = self.map.get(sentences[i], 0) + times[i]         

    def input(self, c: str) -> List[str]:
        if c == "#":
            self.map[self.inputstr] =  self.map.get(self.inputstr, 0) + 1
            self.inputstr = ""
            return []
        
        self.inputstr += c
        heap = []
        for sentence, count in self.map.items():
            if sentence.startswith(self.inputstr):
                heapq.heappush(heap, Wrapper(sentence, count))
            
            if len(heap) > 3:
                heapq.heappop(heap)
                
        result = []
        while len(heap) > 0:
            result.insert(0, heapq.heappop(heap).sentence)
        return result
        

class TrieNode:
    def __init__(self):
        self.map = dict()
        self.children = [None]*(256)

class Wrapper:
    def __init__(self, sentence, count):
        self.sentence = sentence
        self.count = count
        
    def __lt__(self, other):
        if self.count == other.count:
            return other.sentence < self.sentence
        return self.count < other.count
    
class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        self.root = TrieNode()
        self.inputstr = ""
        for i in range(len(sentences)):
            self.insert(sentences[i], times[i])
        
    def insert(self, sentence, count):
        cur = self.root
        for i in range(len(sentence)):
            if cur.children[ord(sentence[i]) - ord(' ')] is None:
                cur.children[ord(sentence[i]) - ord(' ')] = TrieNode()
            cur = cur.children[ord(sentence[i]) - ord(' ')]
            cur.map[sentence] = cur.map.get(sentence, 0) + count
            
    def search(self, sentence):
        cur = self.root
        for i in range(len(sentence)):
            if cur.children[ord(sentence[i]) - ord(' ')] is None:
                return dict()
            cur = cur.children[ord(sentence[i]) - ord(' ')]
        return cur.map
        

    def input(self, c: str) -> List[str]:
        if c == "#":
            self.insert(self.inputstr, 1)
            self.inputstr = ""
            return []
        self.inputstr += c
        heap = []
        history = self.search(self.inputstr)
        
        for k,v in history.items():
            if k.startswith(self.inputstr):
                heapq.heappush(heap, Wrapper(k,v))
                if len(heap) > 3:
                    heapq.heappop(heap)
        result = []
        while len(heap) > 0:
            wrapper = heapq.heappop(heap)
            result.insert(0, wrapper.sentence)
        return result


*/
/*
class AutocompleteSystem {
    HashMap<String, Integer> map;
    String inputstr;
    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        inputstr = "";
        for (int i=0; i<sentences.length; i++){
            map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
        }
        
    }
    
    public List<String> input(char c) {
        if (c == '#'){
            map.put(inputstr, map.getOrDefault(inputstr, 0) + 1);
            inputstr= "";
            return new ArrayList<>();
        }
        inputstr += c;
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->{
           if (a.count == b.count){
               return b.sentence.compareTo(a.sentence);
           }
            return a.count - b.count;
        });
        
        for (String s:map.keySet()){
            if (s.startsWith(inputstr)){
                pq.add(new Pair(s, map.get(s)));
                if (pq.size() > 3)
                    pq.poll();
            }
        }
        List<String> result = new ArrayList<>();
        while (!pq.isEmpty()){
            result.add(0, pq.poll().sentence);
        }
        return result;
    }
    
    class Pair{
        String sentence;
        int count;
        public Pair(String sentence, int count){
            this.sentence = sentence;
            this.count = count;
        }
    }
}
*/

// time - O(m*n) where m is size of sentence array and n is average length of a string
// space - O(m*n) to store trie
// logic - first stored the history in the trie and also maintained a hashmap in each level to hold all the strings which start with
// those particular chars and then pulled out history which is maintained at each level in trie and put it in heap
class AutocompleteSystem {
    class TrieNode{
        HashMap<String, Integer> map;
        TrieNode[] children;
        public TrieNode(){
            map = new HashMap<>();
            children = new TrieNode[256];
        }
    }
    
    String inputstr;
    TrieNode root;
    public AutocompleteSystem(String[] sentences, int[] times) {
        inputstr = "";
        root = new TrieNode();
        
        for (int i=0; i<sentences.length; i++){
            insert(sentences[i], times[i]);
        }
    }
    
    public void insert(String sentence, int count){
        TrieNode cur = root;
        for (int i=0; i<sentence.length(); i++){
            char c = sentence.charAt(i);
            if (cur.children[c - ' '] == null)
                cur.children[c - ' '] = new TrieNode();
            
            cur = cur.children[c - ' '];
            cur.map.put(sentence, cur.map.getOrDefault(sentence, 0) + count);
        }
    }
    
    public HashMap<String, Integer> search(String sentence){
        TrieNode cur = root;
        for (int i=0; i<sentence.length(); i++){
            char c = sentence.charAt(i);
            if (cur.children[c - ' '] == null)
                return new HashMap<>();
            
            cur = cur.children[c- ' '];
        }
        return cur.map;
    }
    
    public List<String> input(char c) {
        if (c == '#'){
            insert(inputstr,1);
            inputstr= "";
            return new ArrayList<>();
        }
        inputstr += c;
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->{
           if (a.count == b.count){
               return b.sentence.compareTo(a.sentence);
           }
            return a.count - b.count;
        });
        
        HashMap<String, Integer> history = search(inputstr);
        for (String s : history.keySet()){
            if (s.startsWith(inputstr)){
                pq.add(new Pair(s, history.get(s)));
                if (pq.size() > 3)
                    pq.poll();
            }
        }
        List<String> result = new ArrayList<>();
        while (!pq.isEmpty()){
            result.add(0, pq.poll().sentence);
        }
        return result;
    }
    
    class Pair{
        String sentence;
        int count;
        public Pair(String sentence, int count){
            this.sentence = sentence;
            this.count = count;
        }
    }
}
