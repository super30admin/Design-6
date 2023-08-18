// Time Complexity : O(n)
// Space Complexity : O(m)
// Did this code successfully run on Leetcode : Yes

class PhoneDirectory {

    HashSet<Integer>[] set = null;
    int start = 0;
    int max;
    public PhoneDirectory(int maxNumbers) {
        set = new HashSet[maxNumbers + 1];
        for(int i = 0; i < maxNumbers+1; i++){
            set[i] = new HashSet<>();
        }
        max = maxNumbers;
    }
    
    public int get() {
        start = 0;
       while(start < max && set[start] == null){
           start++;
       }
       set[start] = null;
       return start == max ?-1:start;
    }
    
    public boolean check(int number) {
        if(set[number] == null){
            return false;
        }else{
            return true;
        }
    }
    
    public void release(int number) {
        set[number] = new HashSet<>();
    }
}