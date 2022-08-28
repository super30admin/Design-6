//0(1)
//0(n)
class PhoneDirectory {
    Queue<Integer> q = new LinkedList<>();
    HashSet<Integer> set = new HashSet<>();
    public PhoneDirectory(int maxNumbers) {
        for(int i=0;i<maxNumbers;i++)
        {
            q.add(i);
        }
    }

    public int get() {
        if(q.isEmpty())
        {
            return -1;
        }
        int result=q.poll();
        set.add(result);
        return result;

    }

    public boolean check(int number) {
        if(set.contains(number))
        {
            return false;
        }
        return true;
    }

    public void release(int number) {
        if(set.contains(number))
        {
            set.remove(number);
            q.add(number);
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