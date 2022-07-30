//TC,SC - O(n)
class PhoneDirectory {
    HashSet<Integer> set;
    Queue<Integer> queue;
    public PhoneDirectory(int maxNum) {
        queue = new LinkedList<>();
        set = new HashSet<>();
        for(int i = 0; i < maxNum; i++ ){
            queue.add(i);
            set.add(i);
        }
    }
    
    public int get() {
        if(queue.isEmpty()) return -1;
        int result = queue.poll();
        set.remove(result);
        return result;
    }
    
    public boolean check(int num) {
        return set.contains(num);
    }
    
    public void release(int num) {
        if(set.contains(num))return;    
        set.add(num);
        queue.add(num);
    }
}
