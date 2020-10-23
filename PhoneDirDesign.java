// Time Complexity : O(1) 
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

//take a hashset and populate it with all the numbers upto maxNumbers given
//for the get function, return -1 if the hashset is empty else iterate for next number and remove that from set , return it
//for check, return if the number is contained in the set
//for release, just add number to set (if it is present, it woulnt duplicate otherwise it would just add)

import java.util.*;

class PhoneDirectory{
    HashSet<Integer> hashSet;

    public PhoneDirectory(int maxNumbers){
        hashSet=new HashSet<>();

        for(int i=0;i<maxNumbers;i++){
            hashSet.add(i);
        }
    }
    public int get(){
        if(hashSet.isEmpty()){
            return -1;
        }
        int temp = hashSet.iterator().next();
        hashSet.remove(temp);

        return temp;
    }
    public boolean check(int num){
        return hashSet.contains(num);
    }
    public void release(int num){
        hashSet.add(num);
    }
}

public class PhoneDirDesign{

    public static void main(String[] args){

        PhoneDirectory directory = new PhoneDirectory(3);

        System.out.println(directory.get());
        System.out.println(directory.get());
        System.out.println(directory.check(2));
        System.out.println(directory.get());
        System.out.println(directory.check(2));
        directory.release(2);
        System.out.println(directory.check(2));
    }

}