class PhoneDirectory {
    private HashSet<Integer> set;
    private Queue<Integer> q;

    public PhoneDirectory(int maxNumbers) {
        this.set = new HashSet<>();
        this.q = new LinkedList<>();
        for(int i=0;i< maxNumbers;i++){
            set.add(i);
            q.add(i);
        }
    }
    
    public int get() {
        if(q.isEmpty()) return -1;
        int num = q.poll();
        set.remove(num);
        return num;
        
    }
    
    public boolean check(int number) {
        return set.contains(number);
        
    }
    
    public void release(int number) {
        if(!set.contains(number )){
            set.add(number);
            q.add(number);
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