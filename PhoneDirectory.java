package Design6;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class PhoneDirectory {
        HashSet<Integer> numSet = new HashSet<>();
        Queue<Integer> numQ = new LinkedList<>();
        public PhoneDirectory(int maxNumbers) {
            for(int i=0; i<maxNumbers; i++){
                numQ.add(i);
                numSet.add(i);
            }
        }
        public int get() {
            if(!numQ.isEmpty()){
                int num = numQ.poll();
                numSet.remove(num);
                return num;
            }
            return -1;
        }

        public boolean check(int number) {
            return numSet.contains(number);
        }

        public void release(int number) {
            if (!check(number)) {
                numSet.add(number);
                numQ.add(number);
            }
        }
}
