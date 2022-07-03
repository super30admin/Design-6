TC: O(n) + log(n)- length of the sentences list and priorityqueue
SC: O(n) - for the map to store the n array elements. priority queue space is assumed as constant as it is always 3 size.

class solution{
HashMap<String,Integer>map;
StringBuilder search;
public Autocomplete(int[] sentences,int [] times){
search = new StringBuilder();
map = new HashMap<>();
for(int i=0;i<sentences.length();i++){
String str1 = sentences[i];
map.put(str1,map.getOrDefault(str1,0)+times[i]);
}

public List<String> input(char c){

if(c == '#')
{
String str2 = search.toString();
map.put(str2,map.getOrDefault(str2,0)+1);
search = new StringBuilder();
}
search.append(c);
PriorityQueue<String>pq = new PriorityQueue<>((a,b) ->
{int cnt1 = map.get(a);
int cnt2 = map.get(b);
if(cnt1==cnt2) return  b.compareTo(a);
return cnt1-cnt2;//min heap
});
for(String s : map.keySet()){
String se = search.toString();
if(s.startWith(se)){
pq.add(s);
if(pq.size() > 3){
pq.poll();
}
}
List<String>result = new ArrayList<>();
while(!pq.isEmpty()){
result.add(pq.poll());
}
Collections.reverse(result);//bcz the result array has most recently used at tha last
return result;
}
}



