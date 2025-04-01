// https://leetcode.com/problems/design-search-autocomplete-system/

// Time Complexity
// insert(): O(m log 3)
// search(): O(1)
// input(): O(m log 3)/ O(1)

// Space Complexity
// insert(): O(m)
// search(): O(1)
// input(): O(1)

class AutocompleteSystem {

    struct TrieNode{
        unordered_map<char, TrieNode*> leaves;
        vector<int> suggestions;
    };
        vector<string> sent;
        vector<int> freq;
    
        TrieNode* root = NULL;
        TrieNode* curr = NULL;
    
        string prefix = "";
    
    public:
        AutocompleteSystem(vector<string>& sentences, vector<int>& times) {
            root = new TrieNode();
            curr = root;
            sent = sentences;
            freq = times;
            for(int i=0; i<sentences.size(); i++){
                insert(sentences[i], i);
            }
        }
    
        void insert(string str, int idx){
            TrieNode* temp = root;
            for(char ch:str){
                if(temp->leaves.count(ch) == 0){
                    temp->leaves[ch] = new TrieNode;
                }
                temp = temp->leaves[ch];
                
                if(find(temp->suggestions.begin(), temp->suggestions.end(), idx) == temp->suggestions.end()){
                    temp->suggestions.emplace_back(idx);
                }
                sort(temp->suggestions.begin(), temp->suggestions.end(), [this](int idx1, int idx2)->bool{
                    if(freq[idx1] == freq[idx2]) return sent[idx1] < sent[idx2];
                    return freq[idx1] > freq[idx2];
                });
                if(temp->suggestions.size() > 3){
                    temp->suggestions.pop_back();
                }
            }
        }
        
        vector<string> search(char ch){
            vector<string> ans;
    
            if(curr && curr->leaves.count(ch)){
                curr = curr->leaves[ch];
                for(auto i:curr->suggestions){
                    ans.emplace_back(sent[i]);
                }
            }
            else curr = NULL;
            return ans;
        }
        vector<string> input(char c) {
            if(c == '#'){
                auto it = find(sent.begin(), sent.end(), prefix);
                if(it == sent.end()){
                    sent.emplace_back(prefix);
                    freq.emplace_back(1);
                    insert(prefix,sent.size()-1);
                }
                else{
                    int idx = it - sent.begin();
                    freq[idx]++;
                    insert(prefix,idx);
                }
                curr = root;
                prefix.clear();
    
                return vector<string>{};
            }
            prefix += c;
            return search(c);
        }
    };
    
 