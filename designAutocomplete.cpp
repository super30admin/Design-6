#include<iostream>
#include<bits/stdc++.h>
#include<vector>
using namespace std;
/*
We build a Trie where each node keeps track of top 3 hot sentences seen so far.
When typing a character, we walk down the Trie and return the node’s top 3 list.
If we hit #, we record the full sentence and update the Trie with it.
*/
class AutocompleteSystem{
    unordered_map<string, int> map;
    string search;

    struct TrieNode{
        TrieNode* children[256];
        vector<string> top3Results;
        TrieNode(){
            fill(begin(children), end(children), nullptr);
        }
    };
    TrieNode* root;

    void insert(const string& word){
        TrieNode* curr = root;
        for(char c : word){
            int idx = c - ' ';
            if(!curr->children[idx]) curr->children[idx] = new TrieNode();
            curr = curr->children[idx];

            vector<string>& list = curr->top3Results;
            if(find(list.begin(), list.end(), word) == list.end())
                list.push_back(word);

            sort(list.begin(), list.end(), [&](const string& a, const string& b){
                if(map[a] == map[b]) return a < b;
                return map[a] > map[b];
            });
            if(list.size() > 3) list.pop_back();
        }
    }

    vector<string> searchPrefix(const string& prefix){
        TrieNode* curr = root;
        for(char c : prefix){
            int idx = c - ' ';
            if(!curr->children[idx]) return {};
            curr = curr->children[idx];
        }
        return curr->top3Results; 
    }
public:
    AutocompleteSystem(vector<string>& sentences, vector<int>& times){
        search = "";
        root = new TrieNode();
        for(size_t i = 0; i < sentences.size(); ++i){
            map[sentences[i]] += times[i];
            insert(sentences[i]);
        }
    }

    vector<string> input(char c){
        if(c == '#'){
            map[search] += 1;
            insert(search);
            search.clear();
            return {};
        }
        search.push_back(c);
        return searchPrefix(search);
    }

};
