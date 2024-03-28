class PhoneDirectory {
    HashSet<Integer> set;
    Queue<Integer> queue;
    PhoneDirectory(int maxNumbers) {
        this.set = new HashSet<>();
        this.queue = new LinkedList<>();
        for(int i=1; i<maxNumbers;i++) {
            set.add(i);
            queue.add(i);
        }
    }

    int get() {
        if(set.isEmpty()) {
            return -1;
        }
        int value = queue.poll();
        set.remove(value);
        return value;
    }
    boolean check(int number) {
        if(set.contains(number)) {
            return true;
        }
        return false;
    }

    void release(int number) {
        if(set.contains(number)) {
            return;
        }
        set.add(number);
        queue.add(number);
    }
}