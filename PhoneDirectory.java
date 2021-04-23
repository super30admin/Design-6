// "static void main" must be defined in a public class.
public class PhoneDirectory {
    Set<Integer> set;
    int max;
    public PhoneDirectory(int maxNumbers) {
        set = new HashSet<>();
        max = maxNumbers;
        for(int i = 0; i < maxNumbers; i++)
            set.add(i);
    }
    
    // Time Complexity: O(1)
    public int get() {
        if(set.isEmpty())
            return -1;
        int num = set.iterator().next();
        set.remove(num);
        
        return num;
    }
    
    // Time Complexity: O(1)
    public boolean check(int number) {
        return set.contains(number);
    }
    
    // Time Complexity: O(1)
    public void release(int number) {
        if(number >= 0 && number < max)
            set.add(number);
    }
    
    public static void main(String[] args) {
        System.out.println("Hello World!");
        PhoneDirectory pd = new PhoneDirectory(3);
        System.out.println(pd.get());
        System.out.println(pd.get());
        System.out.println(pd.get());
        System.out.println(pd.get());
        System.out.println(pd.check(2));
    }
}