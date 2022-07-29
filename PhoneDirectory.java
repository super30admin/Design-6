//Time O(1)
//Space O(n)
class PhoneDirectory {
    Queue<Integer> q;
    HashSet<Integer> hash;
    public PhoneDirectory(int maxNumbers) {
        q = new LinkedList<>();
        hash = new HashSet<>();
        for(int i=0;i<maxNumbers;i++)
        {q.add(i);
         hash.add(i);
        }
    }
    
    public int get() {
        if(q.size()==0)
            return -1;
        int val = q.poll();
        hash.remove(val);
        return val;
    }
    
    public boolean check(int number) {
        return hash.contains(number);
    }
    
    public void release(int number) {
        if(!hash.contains(number))
        { 
          q.add(number);
          hash.add(number);
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