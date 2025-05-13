import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

//TC: O(1)
//SC: O(n)
class PhoneDirectory {
  private Set<Integer> set;
  private Queue<Integer> q;

  public PhoneDirectory(int maxNumbers) {
      this.set=new HashSet<>();
      this.q=new LinkedList<>();
      for(int i=0;i<maxNumbers;i++){
          q.add(i);
      }
  }
  
  public int get() {
      if(q.isEmpty()){
          return -1;
      }else{
          int top=q.poll();
          set.add(top);
          return top;
      }
  }
  
  public boolean check(int number) {
      if(set.contains(number)){
          return false;
      }
      return true;
  }
  
  public void release(int number) {
      if(set.contains(number)){
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