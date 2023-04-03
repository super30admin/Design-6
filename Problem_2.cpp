642. Design Search Autocomplete System

struct compareWord{
    bool operator()(const pair<int,string> &a, const pair<int, string> &b){
        return a.first > b.first || a.first == b.first && a.second < b.second;
    }
};

struct TrieNode {
    map<char,TrieNode*> children;
    int freq;
    
    TrieNode(): freq(0) {}
};

class AutocompleteSystem {
    private:
    TrieNode* rootNode, *ptrNode = NULL;
    string prefix = "";
    priority_queue<pair<int,string>, vector<pair<int,string>>, compareWord> ansQ;
    
void insertIntoTrie(TrieNode* root, string word, int freq){
    for(char c: word){
        if(root->children.find(c)==root->children.end()){
            root->children[c] = new TrieNode();
        }
        
        root = root->children[c]; 
    }
    
    root->freq += freq;
}

void dfs(TrieNode* ptr, string cur){
    
    if(ptr->freq){
        if(ansQ.size()==3 && ansQ.top().first < ptr->freq){
            ansQ.pop();
            ansQ.push({ptr->freq, cur});
        }
        
        if(ansQ.size()<3){
            ansQ.push({ptr->freq, cur});
        }
    }
    
    for(auto pr: ptr->children){
       dfs(pr.second, cur+pr.first); 
    }
}

vector<string> search (TrieNode* ptr, char c){
    if(ptr->children.find(c)==ptr->children.end()){
        ptrNode = NULL;
        return {};
    }
    
    ptrNode = ptr->children[c];
    
    string cur = prefix;
    
    dfs(ptrNode, cur);
    
    vector<string>ans;
    
    while(!ansQ.empty()){
        ans.push_back(ansQ.top().second);
        ansQ.pop();
    }
    
    reverse(ans.begin(), ans.end());
    return ans;
}
    
public:
    AutocompleteSystem(vector<string>& sentences, vector<int>& times) {
        rootNode = new TrieNode();
        ptrNode = rootNode;
        
        for(int i=0;i<times.size();i++){
            insertIntoTrie(rootNode, sentences[i], times[i]);
        }
    }
    
    vector<string> input(char c) {
        if(c == '#'){
            insertIntoTrie(rootNode, prefix, 1);
            ptrNode = rootNode;
            prefix = "";
            return {};
        }
        
        prefix += c;
        
        if(!ptrNode){
            return {};
        }
        
        return search(ptrNode, c);
    }
};
