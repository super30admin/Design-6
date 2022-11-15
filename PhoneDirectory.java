// Space Complexity : O(2N) -> O(N)
class PhoneDirectory {

    Queue<Integer> q;
    HashSet<Integer> set;

    // Time Complexity -> O(n)
    public PhoneDirectory(int maxNumbers) {
        q = new LinkedList<>();
        set = new HashSet<>();
        for (int i=0; i < maxNumbers; i++) {
            set.add(i);
            q.add(i);
        }
    }

    // Time Complexity -> O(1)
    public int get() {
        if (q.isEmpty()) return -1;
        int polled = q.poll();
        set.remove(polled);
        return polled;
    }

    // Time Complexity -> O(1)
    public boolean check(int number) {
        if (set.contains(number)) {
            return true;
        }
        return false;
    }

    // Time Complexity -> O(1)
    public void release(int number) {
        if(!set.contains(number)) {
            q.add(number);
            set.add(number);
        }
    }
}
