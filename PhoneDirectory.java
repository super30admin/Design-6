//TC : O(n) // For initialization, O(n) and fore check and get operations -- O(1)
//SC : O(n) // using queue and set here to store the free numbers

class PhoneDirectory {
    Set<Integer> set; // Maintaining free slots of phonenumbers
    Queue<Integer> queue;
    public PhoneDirectory(int maxNumbers) {
        queue = new LinkedList<>();
        set = new HashSet<>();
        for(int i = 0; i < maxNumbers; i++ ){
            queue.add(i);
            set.add(i);
        }
    }
    
    public int get() {
        if(queue.isEmpty()) return -1;
        int result = queue.poll();// Assigning result as new phonenumber
        set.remove(result); // remove that assigned number from set
        
        return result;
    }
    
    public boolean check(int number) {
        return set.contains(number);
    }
    
    public void release(int number) {
        if(set.contains(number))    return; // If number is free, it will be already in the set
   //Otherwise add free number to set and queue     
        set.add(number);
        queue.add(number);
    }
}

