//****125.379. DESIGN A PHONE DIRECTORY****
//Time complexity:o(n);
//Space complexity:o(n);
//Leetcode runnable: Y;
//Any doubt:N;

class PhoneDirectory {
    HashSet<Integer> set;
    Queue<Integer> q;

    public PhoneDirectory(int maxNumbers) {
        this.set=new HashSet<>();
        this.q=new LinkedList<>();
        
        for(int i=0;i<maxNumbers;i++)
        {
            q.add(i);
            set.add(i);
        }
        
    }
    
    public int get() {
        if(!q.isEmpty())
        {
            int re=q.poll();
            set.remove(re);
            return re;
        }
        return -1;
        
    }
    
    public boolean check(int number) {
        return set.contains(number);
        
    }
    
    public void release(int number) {
        if(set.contains(number))
        {
           set.add(number); 
        }
        else
        {
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
