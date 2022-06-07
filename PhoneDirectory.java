import java.io.*;
import java.util.*;

class PhoneDirectory{

    //Time Complexity : 0(1) we don't account for constructor as it is called only once
    //Space Complexity: 0(n) where n is the max number
    //Did it successfully run on leetcode: No, I don't have leetcode premium
    //Did you face any problem while coding: No

    //In brief explain your code

    HashSet<Integer> set;   //we are using a hashset to query if a no is available or not
    Queue<Integer> q;   //we are using a queue to store all the no's and also return the next available no.

    public PhoneDirectory(int maxNumbers){
        set = new HashSet<>();
        q = new LinkedList<>();
        for(int i = 0; i < maxNumbers; i++){
            set.add(i); //storing all the available no's at first till max no's
            q.add(i);
        }
    }

    public int get(){
        if(!q.isEmpty()){
            return -1;      //we are returning -1 since there are no available no's
        }
        int polled = q.poll();  //this function is requesting for the next available no. so we poll the next available no from queue and return it
        set.remove(polled); //we remove the no. from hashset as it is no longer available
        return polled;
    }

    public boolean check(int number){
        return set.contains(number);    //if set contains the no means it's available and if it does no, then it's not available
    }

    public void release(int number){
        if(set.contains(number)){   //if set contains no. means the no. is already available
            return;
        }
        q.add(number);  //if not, we add that no to our queue stating it is no available and ready to use
        set.add(number);    //we add that no. to hashset meaning it is available and not used
    }

}