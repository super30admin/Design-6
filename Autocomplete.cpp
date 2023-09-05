//time: O(N)
//space: O(N)


class AutocompleteSystem {
    private:
        struct TrieNode {
            TrieNode* children[256];
            vector<string> topNresults;

            TrieNode() {
                for (int i = 0; i < 256; i++) {
                    children[i] = nullptr;
                }
            }
        };

    unordered_map<string, int> map;
    string search;
    TrieNode* root;

    void insert(string word) {
        TrieNode* curr = root;


        for (char ch : word) {


            if (curr->children[ch] == nullptr) {
                curr->children[ch] = new TrieNode();
            }



            curr = curr->children[ch];


            if (find(curr->topNresults.begin(), curr->topNresults.end(), word) == curr->topNresults.end()) {


                curr->topNresults.push_back(word);
            }

            sort(curr->topNresults.begin(), curr->topNresults.end(), [&](string word1, string word2) {
                    if (map[word1] == map[word2]) {
                        return word1 < word2;
                    }
                    return map[word2] < map[word1]; 
                });

            if (curr->topNresults.size() > 3) {
                curr->topNresults.resize(3);
            }
        }
    }

    vector<string> look(string prefix) {
        TrieNode* curr = root;


        for (char ch : prefix) {
            if (curr->children[ch] == nullptr) {



                return {};

                
            }
            curr = curr->children[ch];
        }
        return curr->topNresults;
    }

public:
     AutocompleteSystem(vector<string>& sentences, vector<int>& times) {
        map.clear();
        search = "";
        root = new TrieNode();
        for (int i = 0; i < sentences.size(); i++) {
            map[sentences[i]] += times[i];
            insert(sentences[i]);
        }
    }

    vector<string> input(char c) {
        if (c == '#') {
            map[search]++;
            insert(search);
            search = "";
            return {};
        }
        search += c;
        return look(search);
    }
};

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem* obj = new AutocompleteSystem(sentences, times);
 * vector<string> param_1 = obj->input(c);
 */