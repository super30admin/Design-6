// "static void main" must be defined in a public class.
//approach : 
/*
used queue to ahave available numbers 
used set to store given numbers.
TC: O(1) for get, release, check
sc: O(n) for Q+Set*/
public class Main {
    static class PhoneDirectory{
        
        Queue<Integer> q;
        Set<Integer> number_set;
        
        public PhoneDirectory(int totalnumbers)
        {
            q = new LinkedList<>();
            number_set = new HashSet<>();
            for(int i =1; i<totalnumbers; i++)
            {
                q.add(i);
            }
        }
        
        //assign the number
        private int get()
        {
            if(!q.isEmpty())
            {
                int temp = q.poll();
                number_set.add(temp);
                return temp;
            }  
            else 
                return -1;
        }
        
        //search for available
        private boolean check(int number)
        {
            return !number_set.contains(number);
        }
        
        //release
        private void release(int number)
        {
            number_set.remove(number);
            q.add(number);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        PhoneDirectory pd = new PhoneDirectory(6);
        
        //assign number
        int n1=0 , n2=0, n3 =0, n4 =0;
        n1 = pd.get();
        System.out.println("assigned  " +n1);
        
        boolean flag = pd.check(2);
        System.out.println("flag "+flag);
        
        n2 = pd.get();
        System.out.println("assigned  "+n2);
       
        n3 = pd.get();
        System.out.println("assigned  "+ n3);
        
        pd.release(0);
        System.out.println(" released");
        
        Queue<Integer> pq = pd.q;
        
        while(!pq.isEmpty())
        {
            System.out.println("Q.poll : "+pq.poll());
        }
        
    }
}

