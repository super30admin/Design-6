// Time Complexity : 0(1)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class PhoneDirectory{
    HashSet<Integer> set;
    Queue<Integer> q;
    public PhoneDirectory(int maxNumbers){
        set = new HashSet<>();
        q = new LinkedList<>();
        for(int i = 0; i < maxNumbers; i++){
            set.add(i); // adding all numbers to set and queue
            q.add(i);
        }
    }
    public int get(){
        if(q.isEmpty()){
            return -1;
        }
        int number = q.poll();
        set.remove(number);
        return number;
    }
    public boolean check(int number){
        if(q.isEmpty()){
            return set.contains(number);
        }
        return false;
    }
    public void release(int number) { // make number available again
        if (!set.contains(number)) { // If the number is currently assigned (i.e., not in the set)
            q.add(number); // Add the number back to the queue
            set.add(number); // Add the number back to the set
        }
    }
}