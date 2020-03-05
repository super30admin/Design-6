//TC :get(number),check(number),release(number) - O(n)
//SC : O(n)
class PhoneDirectory {

    HashSet<Integer> set;
    Queue<Integer> q ;
   /** Initialize your data structure here
       @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
   public PhoneDirectory(int maxNumbers) {
        set  = new HashSet<>();
       q = new LinkedList<>();
       for(int i=0;i<maxNumbers;i++){
           q.add(i);
           set.add(i);
       }
   }
   
   /** Provide a number which is not assigned to anyone.
       @return - Return an available number. Return -1 if none is available. */
   public int get() {
        //Get from top of queue and remove the availbale number from hashset
       if(!q.isEmpty()){
           int value = q.poll();
           set.remove(value);
           return value;
       }
       return -1;
   }
   
   /** Check if a number is available or not. */
   public boolean check(int number) {
        if(set.contains(number))
               return true;
       return false;
   }
   
   /** Recycle or release a number. */
   public void release(int number) {
        //Ensure number does not already exist in hashset
       if(!set.contains(number)){
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