// Time Complexity : O(L + M*logM)
// Space Complexity : O(N + M)
// Did this code successfully run on Leetcode : yES

#include <vector>
#include <unordered_map>
#include <queue>
#include <algorithm>
#include <string>
  
using namespace std;

class AutocompleteSystem {
private:
    struct TrieNode {
        TrieNode* children[128];
        bool isEnd;
        vector<string> pq;

        TrieNode() {
            fill(children, children + 128, nullptr);
            isEnd = false;
        }
    };

    TrieNode* root;
    TrieNode* curr;
    unordered_map<string, int> hotness;
    string builder;

    void addToTrie(TrieNode* node, const string& s, int t) {
        int i = 0;
        hotness[s] += t;

        while (i < s.length()) {
            if (node->children[s[i]] == nullptr) {
                node->children[s[i]] = new TrieNode();
            }

            node = node->children[s[i]];

            if (std::find(node->pq.begin(), node->pq.end(), s) == node->pq.end()) {
                node->pq.push_back(s);
            }

            std::sort(node->pq.begin(), node->pq.end(), [&](const std::string& a, const std::string& b) {
                return hotness[b] != hotness[a] ? hotness[b] < hotness[a] : a < b;
            });

            if (node->pq.size() > 3) {
                node->pq.pop_back();
            }

            i++;
        }

        node->isEnd = true;
    }

public:
    AutocompleteSystem(std::vector<std::string>& sentences, std::vector<int>& times) {
        root = new TrieNode();
        curr = root;

        for (int i = 0; i < sentences.size(); i++) {
            addToTrie(root, sentences[i], times[i]);
        }
    }

    std::vector<std::string> input(char c) {
        std::vector<std::string> result;

        if (c == '#') {
            addToTrie(root, builder, 1);
            builder.clear();
            curr = root;
            return result;
        } else {
            if (curr->children[c] == nullptr) {
                curr->children[c] = new TrieNode();
            }

            builder += c;
            curr = curr->children[c];

            return curr->pq;
        }
    }
};
