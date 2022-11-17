// Problem2 Design Autocomplete System (https://leetcode.com/problems/design-search-autocomplete-system/)

// input
// Time Complexity : 
// Avg O(1) 
// When a new entry, O(n), n is the length of sentence
// Initial setup, O(mn), m is total sentences, n is avg length of each sentence
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

class TrieNode {
    constructor() {
        // Maps character and trienode
        this.children = new Map();
        // An array which contains the search output for sentence till char 
        this.pq = [];
    }
    insert(root, freqMap, sentence) {
        let curr = root;
        // Loop through each character of sentence
        for (let i = 0; i < sentence.length; i++) {
            let ch = sentence[i];
            // If it is not present in trie, add trienode for the ch
            if (!curr.children.has(ch)) {
                curr.children.set(ch, new TrieNode());
            }
            // Set curr as the trienode
            curr = curr.children.get(ch);
            // If the result does not contain sentence, add
            if (!curr.pq.includes(sentence))
                curr.pq.push(sentence);
            // Sort by occurence frequency. If same, sort lexicofraphically
            curr.pq.sort((a, b) => {
                if (freqMap.get(b) === freqMap.get(a)) {
                    return a.localeCompare(b);
                }
                return freqMap.get(b) - freqMap.get(a);
            })
            // If size inc by 3, remove from result
            if (curr.pq.length > 3)
                curr.pq.pop();
        }
    }
    search(root, sentence) {
        let curr = root;
        // Loop through each character of sentence and check if it is present in trie
        for (let i = 0; i < sentence.length; i++) {
            let ch = sentence[i];
            // If any ch is not present, return empty
            if (!curr.children.has(ch)) {
                return [];
            }
            curr = curr.children.get(ch);
        }
        // If all ch are present, the curr.pq contains result
        return curr.pq;
    }
}

/**
* @param {string[]} sentences
* @param {number[]} times
*/
class AutocompleteSystem {
    constructor(sentences, times) {
        this.root = new TrieNode();
        this.freqMap = new Map();
        this.stringTillNow = "";
        for (let i = 0; i < sentences.length; i++) {
            // FreqMap maintains sentence and its requency
            this.freqMap.set(sentences[i], times[i]);
            // Insert all sentences in trie
            this.root.insert(this.root, this.freqMap, sentences[i])
        }
    }
};

/** 
* @param {character} c
* @return {string[]}
*/
AutocompleteSystem.prototype.input = function (c) {
    if (c === "#") {
        // Add to freqMap if not present else update
        let str = this.stringTillNow;
        if (!this.freqMap.has(str)) {
            this.freqMap.set(str, 0);
        }
        // Update sentence frequency
        this.freqMap.set(str, this.freqMap.get(str) + 1)
        // Reset string till now.
        this.stringTillNow = "";
        // Update in trie
        this.root.insert(this.root, this.freqMap, str);
        return [];
    }
    // Update string formed
    this.stringTillNow += c;
    // Returns the 1st 3 matches
    return this.root.search(this.root, this.stringTillNow);
};

/** 
* Your AutocompleteSystem object will be instantiated and called as such:
* var obj = new AutocompleteSystem(sentences, times)
* var param_1 = obj.input(c)
*/