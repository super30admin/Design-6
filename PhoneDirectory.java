class PhoneDirectory {
    Set<Integer> dir;
    int capacity;

    public PhoneDirectory(int maxNumbers) {
        dir = new HashSet<>();
        this.capacity = maxNumbers;
    }

    public int get() {

        return getNumber(capacity - 1);

    }

    public boolean check(int number) {
        if (!dir.contains(number)) {
            return true;
        }
        return false;
    }

    public void release(int number) {
        if (dir.contains(number)) {
            dir.remove(number);
        }
    }

    private int getNumber(int r) {
        for (int i = 0; i <= r; i++) {
            if (!dir.contains(i)) {
                dir.add(i);
                return i;
            }
        }
        return -1;
    }
}