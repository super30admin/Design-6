// Problem1 Design Phone Directory (https://leetcode.com/problems/design-phone-directory/)

// Time Complexity : O(1)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach in three sentences only
/*
 * Here, take a hashset and a queue to keep track of available numbers. Hashset is to check the availability and queue is to give out the very
 * next available number. Remove the element from both if its being used. When a number is released add in both.
 */
class PhoneDirectory{
    HashSet<Integer> set;
    Queue<Integer> q;
    public PhoneDirectory(int maxNumbers){
        this.set = new HashSet<>();
        this.q = new LinkedList<>();
        for(int i = 0; i < maxNumbers; i++){
            set.add(i);
            q.add(i);
        }
    }
    
    public int get(){
        if(q.isEmpty()) return -1;
        int popped = q.poll();
        set.remove(popped);
        return popped;
    }
    
    public boolean check(int number){
        return set.contains(number);
    }
    
    public void release(int number){
        if(set.contains(number)) return;
        q.add(number);
        set.add(number);
    }
    
    
}
class Main {
    public static void main(String[] args) {
        PhoneDirectory directory = new PhoneDirectory(3);

// It can return any available phone number. Here we assume it returns 0.

System.out.println(directory.get());
// Assume it returns 1.
System.out.println(directory.get());

// The number 2 is available, so return true.
System.out.println(directory.check(2));

// It returns 2, the only number that is left.
System.out.println(directory.get());

// The number 2 is no longer available, so return false.
System.out.println(directory.check(2));

// Release number 2 back to the pool.
directory.release(2);

// Number 2 is available again, return true.
System.out.println(directory.check(2));
    }
}