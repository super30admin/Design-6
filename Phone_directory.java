// Time Complexity : O(1)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : NO (Premium problem>)
// Any problem you faced while coding this : No

class PhoneDirectory{
    HashSet<Integer> set;
    Queue<Integer> q;
    public PhoneDirectory(int maxNumbers){
        q = new LinkedList<>();
        set = new HashSet<>();
        for(int i = 0; i< maxNumber; i++){
            q.add(i);
            set.add(i);
        }
    }

    public int get(){
        if(q.isEmpty()){
            return -1;
        }
        int number = q.poll();
        set.remove(i);
        return number;
    }
    public boolean check(int number){
        if(!q.isEmpty())
        {
           return set.contains(number);
        }
        return false;
    }
    
    // add number
    public void release(int number){
        if(!set.contains(number)){
           q.add(number);
           set.add(number);
        }
    }
 }