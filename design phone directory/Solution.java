// Time Complexity: O(n)
// Space Comlpexity: O(n)

public class PhoneDirectory {
    private HashSet<Integer> set;
    private Queue<Integer> q;
 
    public PhoneDirectory(int maxNumbers) {
    q = new LinkedList<>();
      set = new HashSet<>();
      for(int i=0;i<maxNumbers;i++){
      q.add(i);
        set.add(i);
      }
    }
  
  public int get() {
if(q.isEmpty()) return -1;
    int val=q.poll();
    set.remove(val);
    return val;
  }
  
  public boolean check(int number) {
  return set.contains(number);
  }
  
  public void release(int number) {
    if(!set.contains(number)){
    q.add(number);
    set.add(number);
    }
  
  }
