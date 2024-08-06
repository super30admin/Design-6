/*
    LC - 379. Design Phone Directory
    TC -
        O(1) for get, check , release
    SC - O(n)
    Approach -
        Using queue and hashset
*/

class PhoneDirectory {

    HashSet<Integer> set;
    Queue<Integer> q;

    public PhoneDirectory(int maxNumbers) {
        q = new LinkedList<>();
        set = new HashSet<>();
        for(int i=0; i<maxNumbers; i++) {
            q.offer(i);
            set.add(i);
        }
    }
    
    public int get() {
        if(q.isEmpty()) {
            return -1;
        }
        int number = q.poll();
        set.remove(number);
        return number;
    }
    
    public boolean check(int number) {
        return set.contains(number);
    }
    
    public void release(int number) {
        if(!set.contains(number)) {
            q.add(number);
            set.add(number);
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