import java.util.*;

class PhoneDirectory {
    
    HashSet<Integer> set = new HashSet<>();
    
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        for(int i = 0; i < maxNumbers; i++){
            set.add(i);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        
        Iterator value = set.iterator(); 
        if(value.hasNext()){
            Integer received = (Integer)value.next();
            set.remove(received);
            return received;
        }
        
        return -1;

    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        if(this.set.contains(number)){
            return true;
        }else{
            return false;
        }
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        set.add(number);
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */