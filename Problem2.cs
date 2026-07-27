// Time Complexity : O(1) for Input, O(L * log3) where L is the length of the word and log3 is the time taken to sort the list
// Space Complexity : O(M*L) where M is the total number of unique sentences and L is the average length of the sentences
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

/*
1. I build a trie where I store the top 3 frequently occurring sentences starting with each prefix.
2. For each character typed, I return the top 3 frequently occurring sentences for the stream of characters entered so far.
3. If the user types a '#', I add the sentence to the frequency map and insert it into the trie and set the input stream of characters to an empty string
*/

public class AutocompleteSystem {

    public class TrieNode
    {
        public List<string> startsWith;
        public Dictionary<char, TrieNode> next;

        public TrieNode()
        {
            this.startsWith = new();
            this.next = new();
        }
    }

    public void Insert(string word)
    {
        TrieNode temp = root;
        for(int i = 0; i < word.Length; i++)
        {
            char ch = word[i];
            if(!temp.next.ContainsKey(ch))
            {
                temp.next[ch] = new TrieNode();
            }

            temp = temp.next[ch];
            
            if(!temp.startsWith.Contains(word))
            {
                temp.startsWith.Add(word);
            }
            
            temp.startsWith.Sort(Comparer<string>.Create((a, b) =>
            {
                if (frequencyMap[a] > frequencyMap[b])
                {
                    return -1; // a comes before b
                }
                else if (frequencyMap[a] < frequencyMap[b])
                {
                    return 1;  // b comes before a
                }
                else
                {
                    return a.CompareTo(b); // lexicographically smaller first
                }
            }));

            if(temp.startsWith.Count>3)
            {
                temp.startsWith.RemoveAt(temp.startsWith.Count -1);
            }
        }
    }   

    public List<string> StartsWith(string word)
    {
        TrieNode temp = root;
        
        for(int i = 0; i < word.Length; i++)
        {
            char ch = word[i];
            if(!temp.next.ContainsKey(ch))
            {
                return new List<string>();
            }
            temp = temp.next[ch];
        }
        
        return temp.startsWith;
    }


    Dictionary<string, int> frequencyMap;
    StringBuilder input;
    TrieNode root;

    public AutocompleteSystem(string[] sentences, int[] times) {
        frequencyMap = new();
        input = new();
        root = new();
        
        for(int i = 0; i < sentences.Length; i++)
        {
            string sentence = sentences[i];
            frequencyMap[sentence] = frequencyMap.GetValueOrDefault(sentence, 0) + times[i]; 
            Insert(sentence);
        }
    }
    
    public IList<string> Input(char c) {
        List<string> result = new();

        if(c == '#')
        {
            string sentence = input.ToString();
            frequencyMap[sentence] = frequencyMap.GetValueOrDefault(sentence, 0) + 1;

            Insert(sentence);

            input = new StringBuilder();
            return result;
        }

        input.Append(c);
        
        List<string> startsWith = StartsWith(input.ToString());

        return startsWith;
        
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * IList<string> param_1 = obj.Input(c);
 */