class PhoneDirectory {
    Set<Integer> available;
    Queue<Integer> recycled;
    int maxNumbers;
    int nextNumber;

    public PhoneDirectory(int maxNumbers) {
        this.available = new HashSet<>();
        this.recycled = new LinkedList<>();
        this.maxNumbers = maxNumbers;
        this.nextNumber = 0;

        for (int i = 0; i < maxNumbers; i++) {
            available.add(i);
            recycled.offer(i);
        }
    }

    public int get() {
        if (available.isEmpty()) {
            return -1;
        }
        
        int num = recycled.poll();
        available.remove(num);
        return num;
    }

    public boolean check(int number) {
        return available.contains(number);
    }

    public void release(int number) {
        if (!available.contains(number) && number < maxNumbers) {
            available.add(number);
            recycled.offer(number);
        }
    }
}

