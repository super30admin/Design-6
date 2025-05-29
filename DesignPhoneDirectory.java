class PhoneDirectory {
    Queue<Integer> q;
    HashSet<Integer> unusedSet;

    public PhoneDirectory(int maxNumbers) {
        this.q = new LinkedList<>();
        this.unusedSet = new HashSet<>();

        for(int i=0; i<maxNumbers; i++) {
            q.add(i);
            unusedSet.add(i);
        }
        
    }
    
    public int get() {
        if(q.isEmpty()) return -1;

        int num = q.poll();
        unusedSet.remove(num);
        return num;
        
    }
    
    public boolean check(int number) {
        return unusedSet.contains(number);
        
    }
    
    public void release(int number) {
        if(unusedSet.contains(number)) return; 

        unusedSet.add(number);
        q.add(number);
        
    }
}
