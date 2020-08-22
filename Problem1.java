//Time Complexity : O(1)
//Space Complexity: O(n)

class PhoneDirectory{
    HashSet<Integer> set;

    PhoneDirectory(int n){
        set = new HashSet<>();
        for(int i=0; i<n; i++)
            set.add(i);
    }

    private int get(){
        int num = -1;

        for(int i: set){
            num = i;
            break;
        }
        set.remove(num);
        return num;
    }

    private boolean check(int num){
        return set.contains(num);
    }

    private void release(int num){
        set.add(num);
    }
}