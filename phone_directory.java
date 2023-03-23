class PhoneDirectory {
    Set<Integer> set;
    public PhoneDirectory(int maxNumbers) {
        set = new HashSet<Integer>();
        for(int i = 0;i< maxNumbers;i++){
            set.add(i);
        }
    }
    
    public int get() {
        if(set.size() == 0) return -1;
        int num = set.iterator().next();
        set.remove(num);
        return num;    
    }
    
    public boolean check(int number) {
        return set.contains(number);
    }
    
    public void release(int number) {
        set.add(number);
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */