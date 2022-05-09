import java.util.*;

class PhoneDirectory {

  Queue<Integer> q;
  Set<Integer> available;
  public PhoneDirectory(int maxNumbers) {
      q = new LinkedList<>();
      available = new HashSet<>();
      for(int i = 0; i < maxNumbers; i++){
          q.add(i);
          available.add(i);
      }
  }
  
  public int get() {
      if(available.isEmpty()) { return -1; }
      
      int num = q.poll();
      available.remove(num);
      
      return num;
  }
  
  public boolean check(int number) {
      return available.contains(number) ;
  }
  
  public void release(int number) {
      if(available.contains(number)){ return; }
      
      q.add(number);
      available.add(number);
  }
}
