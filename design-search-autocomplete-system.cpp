class AutoCompleteSystem{
  
    class TrieNode {
        vector<TrieNode*> children (256,NULL);
        unordered_map<string,int> map;
        bool isEnd = false;
    };
    
    Trie* root;
    
    class Trie {
        TrieNode* root;
        
        Trie(){
            root = new TrieNode();
        }
    
        void insert(string sentence, int count){
            TrieNode* curr = root;
            for(auto c:sentence){
                if(curr->children[c - ' '] == NULL) curr->children[c - ' '] = new TrieNode();
                curr = curr->children[c-' '];
                curr->map[sentence] += count;
            }
            return;
        }
        
        unordered_map<string, int> search(string sentence){
            TrieNode* curr = root;
            for(auto c:sentence){
                if(curr->children[c - ' '] == NULL) return unordered_map<string,int> ();
                curr = curr->children[c - ' '];
            }
            return curr->map;
        }
    }
    
    
    string input;
    AutoCompleteSystem(vector<string> sentences, vector<int> count){
        root = new Trie();
        for(int i=0;i<sentences.size();i++){
            root.insert(sentences[i], count[i]);
        }
        input = "";
    }
    
    
    vector<string> List(char c){
        if(c == '#'){
            insert(input,1);
            input = "";
            return vector<string> ();
        }
        input += c;
    
        auto comp = [](pair<string,int> a, pair<string,int> b){
            if(a.second == b.second) return a.first>b.first;
            return a.second>b.second;
        };
        priority_queue<pair<string,int>,vector<pair<string,int>>, decltype(comp)> pq(comp);
        
        unordered_map<string,int> map = root->search(input);
        
        for(auto it = map.begin();it!=map.end();it++){
            pq.push(it->first);
            if(pq.size()>3) pq.pop();
        }
        vector<int> ret;
        while(!pq.empty()){
            ret.push_back(pq.top());
            pq.pop();
        }
        
        return ret;
        
    }
    
};