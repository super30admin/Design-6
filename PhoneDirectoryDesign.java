class PhoneDirectory {
    HashSet<Integer> set;
    Queue<Integer> q; 

    public PhoneDirectory(int maxNumbers) {
        this.set = new HashSet<>();
        this.q = new LinkedList<>();

        for(int i=0;i<maxNumbers;i++)
        {
            q.add(i);
            set.add(i);
        }
        
    }
    
    public int get() { //o(1)
        if(set.isEmpty()) return -1;
        int re = q.poll();
        set.remove(re);
        return re;
        
    }
    
    public boolean check(int number) { //o(1)
        return set.contains(number);
        
    }
    
    public void release(int number) { //o(1)
        if(!set.contains(number))
        {
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