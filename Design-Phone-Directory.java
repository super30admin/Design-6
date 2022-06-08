class PhoneDirectory {
    Queue<Integer> q;
    Set<Integer> set;
    public PhoneDirectory(int maxNumbers) {
        q = new LinkedList<>();
        set = new HashSet();
        for(int i=0; i<maxNumbers; i++){
            q.add(i);
            set.add(i);
        }
    }

    public int get() {
        if(q.isEmpty()) return -1;
        int polled = q.poll();
        set.remove(polled);
        return polled;
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
