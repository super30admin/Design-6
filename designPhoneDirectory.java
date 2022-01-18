//TC and SC is not needed for design problems
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None

public class designPhoneDirectory {
    class Node {
        int val;
        Node next;
        Node(int val) {
            this.val = val;
        }
    }
    
    Node[] nodes;
    Node curr;
    
    public PhoneDirectory(int maxNumbers) {
        nodes = new Node[maxNumbers];
        
        nodes[0] = new Node(0);
        for (int i = 1; i < maxNumbers; i++) {
            nodes[i] = new Node(i);
            nodes[i-1].next = nodes[i];
        }
        
        curr = nodes[0];
    }

    public int get() {
        if (curr == null) return -1;
        
        int res = curr.val;
        curr = curr.next;         
        
        nodes[res] = null;      
        return res;
    }
    
 
    public boolean check(int number) {
        return nodes[number] != null;
    }
    
    public void release(int number) {
        if (nodes[number] != null) return;
        
        nodes[number] = new Node(number);
        nodes[number].next = curr;         
        curr = nodes[number];              
    }
}
