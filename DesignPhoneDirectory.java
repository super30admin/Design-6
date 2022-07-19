// Time Complexity : O(N)
// Space Complexity : O(N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
public class DesignPhoneDirectory {
    class PhoneDirectory {
        Queue<Integer> q;
        Set<Integer> set;

        public PhoneDirectory(int maxNumbers) {
            q = new LinkedList<>();
            set = new HashSet<>();
            for(int i=0; i< maxNumbers; i++) {
                q.add(i);
                set.add(i);
            }
        }


        public int get() {
            if(q.isEmpty()) return -1;
            int re = q.poll();
            set.remove(re);
            return re;
        }

        public boolean check(int number) {
            return set.contains(number);
        }

        public void release(int number) {
            if(!set.contains(number)) {
                q.add(number);
                set.add(number);
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
}
