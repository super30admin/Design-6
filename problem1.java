//time complexity: O(1)
//space complexity: O(N)
//ran on leetcode :yes
//implement a Phone Directory class that can provide, check, and release phone numbers using a HashSet to keep track of available numbers
class PhoneDirectory {

    Set<Integer> set;

   

    public PhoneDirectory(int maxNumbers) {

        set= new HashSet<Integer>();

        for(int i=0; i<maxNumbers; i++)set.add(i);

    }

    



    public int get() {

        if(set.size()==0)return -1;

        int num = set.iterator().next();

        set.remove(num);

        return num;

    }

    

    
    public boolean check(int number) {

        return set.contains(number);

    }

    

    public void release(int number) {

        set.add(number);

    }

}
