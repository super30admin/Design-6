// Time Complexity : O(1)
// Space Complexity :O(n)
class PhoneDirectory {
    Queue<Integer> q;
    HashSet<Integer> hs;
    int max;

    public PhoneDirectory(int maxNumbers) {
        q=new LinkedList<>();
        hs=new HashSet<>();
        this.max = maxNumbers;
        for(int i=0;i<max;i++){
            q.add(i);
            hs.add(i);
        }
    }
    
    public int get() {
        if(!q.isEmpty()){
            int num = q.poll();
            hs.remove(num);
            return num;
        }
        return -1;
    }
    
    public boolean check(int number) {
        return hs.contains(number);
    }
    
    public void release(int number) {
        if(hs.contains(number)) return;
        hs.add(number);
        q.add(number);
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */