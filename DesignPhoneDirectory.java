// Time Complexity : get() - O(1), check - O(1), release - O(1)
// Space Complexity : O(N) - for queue and array
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
// Create an array and a queue initially add maxNumbers elements in queue
// In get function check if queue is empty, empty means slots occupied and return -1
// Otherwise remove top of queue and make the corresponding array true
// In check funtion if array value at that index is true means its occupied otherwise not
// In release function if the slot is occupied remove it and add in queue
class PhoneDirectory {
    boolean [] dir;
    int size;
    int index;
    Queue<Integer> q;
        
    public PhoneDirectory(int maxNumbers) {
        dir = new boolean[maxNumbers];
        size = maxNumbers;
        index = 0;
        q = new LinkedList<>();
        for(int i = 0; i < size; i++)
            q.add(i);
    }
    
    public int get() {
        if(q.size() == 0)
            return -1;
        else{
            int x = q.poll();
            dir[x] = true;
            return x;
        }
    }
    
    public boolean check(int number) {
        return !dir[number];
    }
    
    public void release(int number) {
        if(dir[number]){
            q.add(number);
            dir[number] = false;
        }
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */