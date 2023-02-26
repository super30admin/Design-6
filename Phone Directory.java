// Time Complexity = O(1)
// Space Complexity = O(n)

class PhoneDirectory {
    private HashSet <Integer> set;
    private Queue<Integer> q;
    
    public PhoneDirectory(int maxNumbers) {
        this.q = new LinkedList<>();
        this.set = new HashSet<>();
        for(int i = 0; i < maxNumbers; i++){
            q.add(i);
            set.add(i);
        }
    }
    
    public int get() {
        if(q.size() == 0) return -1;
        int newNumber = q.poll();
        set.remove(newNumber);
        return newNumber;
    }
    
    public boolean check(int number) {
        return set.contains(number);
    }
    
    public void release(int number) {
        if(set.contains(number)) return;
        q.add(number);
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