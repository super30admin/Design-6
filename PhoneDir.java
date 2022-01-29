
//T.C=O(n)
//S.c = o(n)
class PhoneDirectory {

    
    Queue<Integer> q;
    HashSet<Integer> set;
    public PhoneDirectory(int maxNumbers) {
        
        set = new HashSet<>();
        q = new LinkedList<>();
        for(int i=0;i<maxNumbers;i++)
        {
            q.add(i);
            set.add(i);
        }
        
        
    }
    
    public int get() {
        if(q.isEmpty()) return -1;
        
        int number = q.poll();
        set.remove(number);
        return number;
        
    }
    
    public boolean check(int number) {
        if(set.contains(number))
        {
            return true;
        }
        return false;
    }
    
    public void release(int number) {
        if(set.contains(number)) return;
        set.add(number);
        q.add(number);
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */