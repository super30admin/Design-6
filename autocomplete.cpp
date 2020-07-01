// Time Complexity : O(nkm) n-number of sentences; k-average length of input; m-number of matches in map
// Space Complexity : O(n) - map 
// Did this code succesfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// 1. Maintain a map with sentence and its hotness, a global string as curr input and a min-heap of 3 hottest pairs of string and hotness. 
// 2. If input char is #, then update curr_input's hotness in map, reset curr_input and return empty vector  
// 3. Search all keys of map to find ones which start with curr_input and place them in heap while maintaining heap size

// min-heap compare function; if hotness is same then its good (return 1) when a is smaller than b
// if hotness is not same, then its good (return 1) when a is hotter
struct compare
{
	bool operator() (const pair<string, int>& a, const pair<string, int>& b){
		if(a.second == b.second)
			return (a.first.compare(b.first)<0); // if a is smaller than b lexicowise then good
		else
			return (a.second>b.second);
	}
};

class AutocompleteSystem {
public:
	map<string, int> hot;
	string curr_input;
    AutocompleteSystem(vector<string>& sentences, vector<int>& times) {
    	for(int i=0;i<sentences.size();i++)
    		hot[sentences[i]] = times[i];    	
    }
    
    vector<string> input(char c) {
        vector<string> result;
        priority_queue<pair<string,int>, vector<pair<string, int>>, compare>pq;
        // if end char
        if(c=='#'){
        	hot[curr_input]++;
        	curr_input="";
        	return result;
        }
        else{
        	curr_input+=c;
        	// go thru keys of map to find curr_input
			for(auto itr: hot){
				string sentence = itr.first;
				// sentence starts with curr_input
				if(sentence.find(curr_input) == 0){
					// place only 3 strings in min-heap
					if(pq.size()<3)
						pq.push({sentence,hot[sentence]});
					else{
						// first compare hotness and keep hotter in pq
						auto sentAtTop = pq.top();
						if(hot[sentence] > sentAtTop.second){
							pq.pop();
							pq.push({sentence,hot[sentence]});
						}
						// equal hotness then lexicographical order
						// replace only if sentence has smaller ascii value then sentAtTop
						// a.compare(b) will return <0 if a is smaller than b
						else if (hot[sentence] == sentAtTop.second && sentence.compare(sentAtTop.first)<0){
							pq.pop();
							pq.push({sentence,hot[sentence]});
						}
					}
				}
			}
			// placed the hottest sentences in pq; place them in result
			while(!pq.empty()){
				result.insert(result.begin(), pq.top().first);
                pq.pop();
			}
        }
        return result;
    }
};

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem* obj = new AutocompleteSystem(sentences, times);
 * vector<string> param_1 = obj->input(c);
 */