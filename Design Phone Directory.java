// Time Complexity : O(1) for all functions
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach

class PhoneDirectory{
    HashSet<Integer> assignedSet;
    Queue<Integer> q;
    
    public PhoneDirectory(int maxNumbers){
        //intialize
        assignedSet = new HashSet<>();
        que = new LinkedList<>();
        
        //start for loop to go through the numbers
        for(int i = 0; i < maxNumbers; i++){
            //add to the queue
            que.add(i);
        }
    }
    
    public int get(){
        //check if queue isn't empty
        if(!que.isEmpty()){
            //pull 
            int res = q.poll();
            //add to the set
            set.add(res);
            //return the result
            return res;
        }
        return -1;
    }
    public boolean check(int number){
        //check if the set contains and return 
        return !assignedSet.contains(number);
    }
    
    public void release(int number){
        //if it contains then release
        if(assignedSet.contains(number)){
            //release it
            assignedSet.remove(number);
            //add to the queue
            que.add(number);
            
        }
    }
}