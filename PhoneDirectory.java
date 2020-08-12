import java.util.*;
class PhoneDirectory {
    HashSet<Integer> store;
    Iterator itr;
    public PhoneDirectory(int maxNumbers) {
        store = new HashSet<>();
        for(int i = 0; i < maxNumbers; i++){                                                            
            store.add(i);
        }
        itr = store.iterator();
    }
    
    public int get() {
        if(store.size() != 0){
            int k = (int) store.iterator().next();                                          
            store.remove(k);                                                              
            return k;
        } else {
            return -1;
        }
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return store.contains(number);                                                      
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        store.add(number);                                                                                  
    }
}