// Approach : Brute Force Using Arrays
// Time : 
// Space : O(n)
class PhoneDirectory {
    boolean[] availableSlots;
    public PhoneDirectory(int maxNumbers) {
        this.availableSlots = new boolean[maxNumbers];
        Arrays.fill(availableSlots,true);// By default all the slots are available - True
    }
    
    public int get() {//O(n)
        for(int i=0;i<availableSlots.length;i++){
            if(availableSlots[i]){
                availableSlots[i]=false;
                return i;
            }
        }
        // when all slots are occupied
        return -1;
    }
    
    public boolean check(int number) {//O(1)
        if(number >=0 && number < availableSlots.length){
            return availableSlots[number];
        }
        return false;
        
    }
    
    public void release(int number) {//O(1)
        // make it available
        if(number >=0 && number < availableSlots.length){
            availableSlots[number]=true;
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

// Approach : Optimized Approach 
// with the initial intuition of using Arrays where we can achieve O(1) for check/release number functions already but get number function only takes O(n) call. Hence we go for optimized approach. When thinking in that direction Since the HashSet alone can not still achieve the get() function therefore we went to queue structure which will give O(1) for get call
// Time : O(1)
// Space : O(n)
class PhoneDirectory {
    HashSet<Integer> set;
    Queue<Integer> q;
    public PhoneDirectory(int maxNumbers) {
        this.set = new HashSet<>();
        this.q = new LinkedList<>();
        for(int i=0;i<maxNumbers;i++){// O(n)
            set.add(i);
            q.add(i);
        }
    }
    
    public int get() {//O(1)
        while(!q.isEmpty()){
            int num = q.poll();
            set.remove(num);
            return num;
        }
        // when all slots are occupied
        return -1;
    }
    
    public boolean check(int number) {//O(1)
        // check if number is available or not
        return set.contains(number);
    }
    
    public void release(int number) {//O(1)
        // make it available to use
        if(!set.contains(number)){
            // if the number is not present in set - in Use
            q.add(number);
            set.add(number);
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