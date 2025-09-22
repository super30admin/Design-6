//Time Complexity: get() → O(1) check() → O(1) release() → O(1)
//Space Complexity: O(n) for queue and set storing up to maxNumbers

// Maintain a queue of available numbers and a hash set of unused numbers for O(1) checks.
// get(): if queue non-empty, poll a number, remove it from the set, and return it; else return -1.
// check(number): return set.contains(number); release(number): if currently used (not in set), add it back to the set and enqueue it.

class PhoneDirectory {

    Queue<Integer> q;
    HashSet<Integer> unusedSet;

    public PhoneDirectory(int maxNumbers) {
        this.q = new LinkedList<>();
        this.unusedSet = new HashSet<>();

        for(int i = 0; i < maxNumbers; i++) {
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
