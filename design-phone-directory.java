// TC: O(1)
// SC: O(1)
// LeetCode: Premium couldn't exactly run the prg on it.

class PhoneDirectory{
    HashSet<Integer> set;
    public PhoneDirectory(int maxNumbers){
        set = new HashSet<>();
        for(int i=0;i<maxNumbers; i++){
            set.add(i);
        }
    }

    public int get(){
        //Define an Iterator and check with hascheck function
        if(set.isEmpty()){
            return -1;
        }
        int num = set.iterator.next();
        // Since the number is no longer available.
        set.remove(num);

        return num;
    }
    public boolean check(int number){
        return set.contains(number);
        // above code ^ is Condensed form of the code below
        '''if(set.contains(number)){
            return true;
        }else{
            return false;
        }'''
    }
    public void release(int number){
        // the set contains the numbers that are free or free to assign to people
        // Sanity Check: before assigning the number - lies between maxnumber and 0 assuming telephone numbers cannot be -ve
        if(number == 0 && number < max){
            set.add(number);
        }
    }
}
