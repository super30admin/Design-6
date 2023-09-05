// Time Complexity :O(1) for check and release and get
// Space Complexity :O(1) for check and release and get
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this :no


// Your code here along with comments explaining your approach

class PhoneDirectory {
    //hashset to store used numbers
    HashSet<Integer> used;
    //queue to store available numbers
    Queue<Integer> avl;

    public PhoneDirectory(int maxNumbers) {
        used = new HashSet<>();
        avl = new LinkedList<>();
        //Put all the available numbers in queue
        for(int i=0; i<maxNumbers; i++){
            avl.add(i);
        }
    }
    
    public int get() {
        if(avl.isEmpty()) return -1;
        int temp = avl.remove();
        used.add(temp);
        return temp;
    }
    
    public boolean check(int number) {
        return !used.contains(number);
    }
    
    public void release(int number) {
        if(used.contains(number)){
            avl.add(number);
            used.remove(number);
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