//Required leetcode premium to run. Could you please review?

class AutocompleteSystem {
	unordered_map<string,int>m;
	string in="";
    public AutocompleteSystem(vector<string>sentences,vector<int>times){		
		//Put inside hashmap
		for(int i=0;i<times.size();i++)
			m[sentences[i]].push_back(times[i]);		
        }
    }
    public List<String> input(char c){
		vector<string>result;
		//If we reach the end of the string, add it into the map,reset string to ""  and return empty list
		if(c=='#')
		{
			m[in]++;
			in="";
			return {};
		}
		in+=c;
		//Could you please help me with declaring custom comparator in c++?
		priority_queue<int,vector<int>,greater<int>>pq;
		else
		{
			//push into 
			for(auto s:m)
			{
				if(c==s[0])
					q.push({s,m[s]});
				if(q.size()>3)
					q.pop();
			}
			while(!q.empty())
				result.push_back(q.first);
		}
		return result;
    }