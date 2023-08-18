
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
// Time Complexity :O(p*nlog3) n = sentences.size()
// Space Complexity : O(n*p) p=sentence[i].size()
// Did this code successfully run on Leetcode : yes
approach : 1  

class AutocompleteSystem {
public:
    unordered_map<string,int>strmap;
    string s;
    
    AutocompleteSystem(vector<string>& sentences, vector<int>& times) {
        for(int i = 0;i<sentences.size();i++)
            strmap.insert({sentences[i],times[i]});

    }
    
    vector<string> input(char c) {
        
        if(c=='#'){
            strmap[s]++;
            s.clear();
            return {};
        }
        
        s.push_back(c);
        
        
        vector<string> ans;
        
        
        auto cmp = [&](const string& a, const string& b) {
            if (this->strmap[a] == this->strmap[b]) {
                return a < b;
            }
            return this->strmap[a] > this->strmap[b];
        };

        priority_queue<string, vector<string>, decltype(cmp)> pq(cmp);
        
        
        // searching for a prefix (s) in map and put in pq;
        
        for(auto &p: strmap)
        {
            if(p.first.find(s)==0){
                pq.push(p.first);
                if(pq.size()>3){
                    pq.pop();
                }
            }
        }
        
        vector<string>result;

        
        while(!pq.empty())
        {
            result.push_back(pq.top());
            pq.pop();
        }
        reverse(result.begin(),result.end());
        
        return result;
    }
};

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem* obj = new AutocompleteSystem(sentences, times);
 * vector<string> param_1 = obj->input(c);
 */


//Approach 2:  using Trie
// Time Complexity :O(p*n) n = sentences.size() but searching time greatly reduced
// Space Complexity : O(n*p) p = sentence[i].size()
// Did this code successfully run on Leetcode : yes

class AutocompleteSystem {
public:
    unordered_map<string,int>strmap;
    string s;
    class TrieNode{
        public:
        unordered_map<char,TrieNode*>children;
        vector<string>pq;
        TrieNode(){
            
        }
        
    };
    
    TrieNode* root;
    
    void insert(string word,int t)
    {
        TrieNode* curr = root;
        
        for(int i = 0;i<word.size();i++)
        {
            char c = word[i];
            if(curr->children.count(c)==0){
                curr->children.insert({c,new TrieNode()});
            }
            curr = curr->children[c];

            vector<string>vec = curr->pq;
            bool flag = false;
            for(int j = 0;j<vec.size();j++)
            {
                if(vec[j]==word){
                    flag = true;
                    break;
                } 
            }
            if(!flag){
                vec.push_back(word);
            } 
            
            sort(vec.begin(),vec.end(),[&](auto const &a, auto const &b){
                if(strmap[a]==strmap[b]){
                    return a<b;
                }
                return strmap[a]>strmap[b];
            });
            
            if(vec.size()>3){
                vec.pop_back();
            }
            curr->pq = vec;

        }
    }
    
    AutocompleteSystem(vector<string>& sentences, vector<int>& times) {
        root = new TrieNode();
        for(int i = 0;i<sentences.size();i++)
        {
            strmap[sentences[i]]+= times[i];
            insert(sentences[i],times[i]);
        }
    }
    
    vector<string> input(char c) {
        if(c=='#')
        {
            strmap[s]++;
            insert(s,1);
            s.clear();
            return {};
        }
        
        s.push_back(c);
        
        TrieNode* curr = root;
        for(int i = 0;i<s.size();i++)
        {
            if(curr->children.count(s[i])==0){
                return {};
            }
            curr = curr->children[s[i]];
        }
        
        return curr->pq;
        
    }
};

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem* obj = new AutocompleteSystem(sentences, times);
 * vector<string> param_1 = obj->input(c);
 */