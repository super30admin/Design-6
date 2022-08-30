//Time complexity- get- O(1), check- O(1) , release - O(1)
//Space complexity- O(n)
//Successfully ran on leetcode
               

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

class PhoneDirectory{

   Queue <Integer> q;
   HashSet<Integer> set;

   public PhoneDirectory(int maxNumbers){
     q= new LinkedList<>();
     set = new HashSet<>();

     for(int i= 0 ; i < maxNumbers; i++){
        q.add(i);
     }
   }

   public int get(){

    if(q.isEmpty()) return -1;
      int polled = q.poll();
      set.add(polled);
      return polled;
   }

   public boolean check(int n){
      if(set.contains(n)){
       return false;
      }
      else return true;
   }

   public void release(int n){
    if(set.contains(n)){
       set.remove(n);
       q.add(n);
    }
   }
}