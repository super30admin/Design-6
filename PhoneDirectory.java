import java.util.*;

//Time Complexity : O(N)
//Space Complexity : O(N)
//Did this code successfully run on Leetcode : Yes
//Any problem you faced while coding this : No

public class PhoneDirectory  {
    HashSet<Integer> set;
    Queue<Integer> queue;

    int maxNumbers;

    public PhoneDirectory(int maxNumbers) {
        this.maxNumbers = maxNumbers;
        set = new HashSet<>(maxNumbers);
        queue = new LinkedList<>();
        this.addNumbers();
    }

    private void addNumbers(){
        for(int i =0; i < this.maxNumbers;){
            set.add(i);
            queue.add(i);
            i++;
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(!queue.isEmpty()){
        Integer number = queue.poll();
        if(number != -1){
            set.remove(number);
            return number;
        }
        }
        return -1;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return set.contains(number);
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if(!set.contains(number)){
            set.add(number);
            queue.add(number);
        }
    }

    public static void main(String args[]){
        int maxNumbers = 3;
        PhoneDirectory directory = new PhoneDirectory(maxNumbers);
        System.out.println(directory.get());
        System.out.println(directory.get());
        System.out.println(directory.check(2));
        System.out.println(directory.get());
        System.out.println(directory.check(2));
        directory.release(2);
        System.out.println(directory.check(2));
        
    }
}