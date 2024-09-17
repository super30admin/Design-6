class PhoneDirectory {

    HashSet<Integer> set;
    public PhoneDirectory(int maxNumbers) {
        set = new HashSet<>();
        for(int i=0;i<maxNumbers;i++)//adding all elemets to set
            set.add(i); 
    }

    public int get() {
        int num=-1;
        if(set.size()!=0){
        num=set.iterator().next();
        set.remove(num);

        }
        return num;

    }

    public boolean check(int number) {
         return (set.contains(number));
    }

    public void release(int number) {
        if(!set.contains(number)){
            set.add(number);
        }
    }
}
