/*
TC --> O(logn)
SC --> O(n)
*/
class PhoneDirectory {
    PriorityQueue<Integer> pq;
    HashSet<Integer> avail;
    HashSet<Integer> taken;
    public PhoneDirectory(int maxNumbers) {
        pq = new PriorityQueue<>();
        avail = new HashSet<>();
        taken = new HashSet<>();

        for(int i=0;i<maxNumbers;i++){
            pq.add(i);
            avail.add(i);
        }
    }
    
    public int get() {. //logn
        if(pq.isEmpty()){
            return -1;
        }
        else{
            int val = pq.poll();
            avail.remove(val);
            taken.add(val);
            return val;

        }
    }
    
    public boolean check(int number) { //O(1)
        if(avail.contains(number)){
            return true;
        }
        return false;
    }
    
    public void release(int number) { // O(logn)
        if(taken.contains(number)){
            taken.remove(number);
            pq.add(number);
            avail.add(number);
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