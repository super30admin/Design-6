class PhoneDirectory {

    Queue<Integer> q;
    HashSet<Integer> usedSet;

    public PhoneDirectory(int maxNumbers) {
        this.q = new LinkedList<>();
        this.usedSet = new HashSet<>();

        for(int i = 0; i < maxNumbers; i++) {
            q.add(i);
        }
    }
    
    public int get() {
        if(q.isEmpty()) return -1;

        int num = q.poll();
        usedSet.add(num);
        return num;
    }
    
    public boolean check(int number) {
        return !usedSet.contains(number);
    }
    
    public void release(int number) {
        if(!usedSet.contains(number)) return;

        usedSet.remove(number);
        q.add(number);
    }
}
