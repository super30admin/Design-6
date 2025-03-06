class PhoneDirectory {
    Queue<Integer> q;
    Set<Integer> s;
    public PhoneDirectory(int maxNumbers) {
        s = new HashSet<>();
        q = new LinkedList<>();
        for (int i=0;i<maxNumbers;i++) {
            s.add(i);
            q.add(i);
        }
    }
    
    public int get() {
        if (q.isEmpty()) {
            return -1;
        } else {
            int ele = q.poll();
            s.remove(ele);
            return ele;
        }
    }
    
    public boolean check(int number) {
        if (s.contains(number)) {
            return true;
        } 
        return false;
    }
    
    public void release(int number) {
        if (!s.contains(number)) {
            s.add(number);
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
