package Design-6;
// TC: O(1) for all operations
// SC: O(2n) n being maxNumbers
public class Problem1 {
    HashSet<Integer> set;
    Queue<Integer> q;
    public PhoneDirectory(int maxNumbers) {
        this.set = new HashSet<>();
        this.q = new LinkedList<>();
        for(int i=0;i<maxNumbers;i++){
            set.add(i);
            q.add(i);
        }
    }
    
    public int get() {
        if(q.isEmpty()) return -1;
        int returnVal = q.poll();
        set.remove(returnVal);
        return returnVal;
    }
    
    public boolean check(int number) {
        return set.contains(number);
    }
    
    public void release(int number) {
        if(!set.contains(number)){
            set.add(number);
            q.add(number);
        }
    }
}
