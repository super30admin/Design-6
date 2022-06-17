/* 
    Time Complexity                              :  AutocompleteSystem constructor --> 
                                                            O(M*N) where M is the average length of the sentences and N is the total number of sentences.
                                                    input --> 
                                                            O(M*N) + O(N) as explained in the method
    Space Complexity                             :  O(M*N) in the trie + O(M*N) in getAllSentences, the ans vector stores all the strings and max it can store will be M*N. 
    Did this code successfully run on Leetcode   :  Yes
    Any problem you faced while coding this      :  No
*/

#include <bits/stdc++.h> 
using namespace std;  

// https://leetcode.com/problems/design-search-autocomplete-system/

class TrieNode {
public:
    string sentence;
    bool isWord;
    vector<TrieNode*> children;
    TrieNode() {
        children.resize(27,nullptr);
        isWord = false;
    }
};


class Trie {
public: 
    TrieNode *root;
    Trie() {
        root = new TrieNode();
    }
    
    int getIdx(char ch) {
        int idx;
        if(ch == ' ') {
            idx = 26;
        } else {
            idx = ch - 'a';
        }
        return idx;
    }
    
    void addSentence(string sentence) {
        TrieNode *rt = root;
        string str = "";
        for(auto ch : sentence) {
            str += ch;
            int idx = getIdx(ch);
            if(rt->children[idx] == nullptr) {
                rt->children[idx] = new TrieNode();
            }
            rt = rt->children[idx];
        }
        rt->sentence = sentence;
        rt->isWord = true;
    }
    
    bool search(string sentence) {
        TrieNode *rt = root;
        for(auto ch : sentence) {
            int idx = getIdx(ch);
            if(rt->children[idx] == nullptr) {
                return false;
            }
            rt = rt->children[idx];
        }
        return rt->isWord;
    }
    
   bool prefixSearch(string prefix) {
        TrieNode *rt = root;
        for(auto ch : prefix) {
            int idx = getIdx(ch);
            if(rt->children[idx] == nullptr) {
                return false;
            }
            rt = rt->children[idx];
        }
        return true;
    }
    
    void dfs(TrieNode *rt, vector<string>& sentences) {
        if(!rt) return;
        if(rt->isWord == true) {
            sentences.push_back(rt->sentence);
        }
        
        for(int i=0;i<27;i++) {
            dfs(rt->children[i], sentences);
        }
        
        return;
    }
    
    vector<string> getAllSentences(string prefix) {
        if(!prefixSearch(prefix)) return {};
        
        TrieNode *rt = root;
        vector<string> ans;
        for(auto ch : prefix) {
            int idx = getIdx(ch);
            rt = rt->children[idx];
        }
        dfs(rt,ans);
        return ans;
    }
    
    
};

class AutocompleteSystem {
private:
    unordered_map<string,int> freq;
    string curSen;
    Trie *trie;
public:
    AutocompleteSystem(vector<string>& sentences, vector<int>& times) {
        trie = new Trie();
        curSen = "";
        for(int i=0;i<sentences.size();i++) {
            freq[sentences[i]] = times[i];
            trie->addSentence(sentences[i]);
        }
    }
    
    vector<string> input(char c) {
        vector<string> vec;
        if(c == '#') {
            freq[curSen]++;
            trie->addSentence(curSen);
            curSen = "";
            return vec;
        } else {
            curSen += c;
            // O(M*N) where M is the average length of the sentences and N is the total number of sentences.
            vec = trie->getAllSentences(curSen);
            // O(N)
            return organize(vec);
        }
    }
    
    // O(N) since the max length of inp = total number of sentences (N)
    vector<string> organize(vector<string>& inp) {
        
        auto cmp = [](const pair<string, int>& A, const pair<string, int>& B) {
                return 
                    A.second > B.second || 
                    (A.second == B.second && lexicographical_compare(A.first.begin(), A.first.end(), B.first.begin(), B.first.end()));

        };
        
        priority_queue<pair<string,int>, vector<pair<string,int>>, decltype(cmp)> pq(cmp);
        for(auto sentence : inp) {
            pq.push({sentence,freq[sentence]});
            if(pq.size() > 3) pq.pop();
        }
        
        vector<string> a;
        while(!pq.empty()) {
            a.push_back(pq.top().first);
            pq.pop();
        }
        
        reverse(begin(a),end(a));
        
        return a;
    }
};

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem* obj = new AutocompleteSystem(sentences, times);
 * vector<string> param_1 = obj->input(c);
 */