// Time Complexity : O(nk) -> Time required to build a Trie 
// Space Complexity : O(nk) -> Space required for trie
//                  Where n : number of strings
//                        k : Avg length of strings
// Did this code successfully run on Leetcode : yes

// Three line explanation of solution in plain english
/* Build a trie, store the top three searches at each node level.
 * Maintain a global map to store search strings and its frequecy.
 * On call to input(), navigate the trie and return the strings stored at last node.
 */

class AutocompleteSystem {
public:
    
    class TrieNode {
        public :
            map<char, TrieNode*> children;
            vector<string> strings;
    };
    
    string search;
    map<string, int> map;
    TrieNode* root; 
        
    void insert(string& sentence)
    {
        TrieNode* curr = root;
        
        for (auto& c : sentence)
        {
            if (curr->children.find(c) == curr->children.end())
            {
                curr->children.insert({c, new TrieNode()});
            }
            
            curr = curr->children.find(c)->second;
            
            if (find(curr->strings.begin(), curr->strings.end(), sentence) == curr->strings.end())
            {
                curr->strings.push_back(sentence);
            }
             
            sort(curr->strings.begin(), curr->strings.end(), [this](const auto& a, const auto& b) {
                auto ele1 = map.find(a);
                auto ele2 = map.find(b);
                if (ele1->second == ele2->second)
                {
                    return ele1->first < ele2->first;               
                }
                return ele1->second > ele2->second;
            });
                 
            if (curr->strings.size() > 3)
            {
                curr->strings.erase(curr->strings.begin() + 3);        
            }    
        }
    }
    
    const vector<string> searchString(string& sentence)
    {
        TrieNode* curr = root;
        
        for (const auto& c : sentence)
        {
            auto ele = curr->children.find(c);
            if (ele == curr->children.end())
            {
                return {};
            }
            
            curr = ele->second;
        }
        
        return curr->strings;
    }
    
    AutocompleteSystem(vector<string>& sentences, vector<int>& times) 
    {
        search = "";
        root = new TrieNode();
        
        for (int i = 0; i < sentences.size(); i++)
        {
            if (map.find(sentences[i]) == map.end())
            {
                map.insert({sentences[i], times[i]});
            }
            else
            {
                map.find(sentences[i])->second += times[i];
            }
            insert(sentences[i]);
        }
    }
    
    vector<string> input(char c) 
    {
        if (c == '#')
        {
            if (map.find(search) == map.end())
            {
                map.insert({search, 1});
            }
            else
            {
                map.find(search)->second++; 
            }
            
            insert(search);
            search.clear();
            return {};
        }
        
        search += c;
        return searchString(search);
    }
};

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem* obj = new AutocompleteSystem(sentences, times);
 * vector<string> param_1 = obj->input(c);
 */