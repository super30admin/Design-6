// Time Complexity : O(N)
// Space Complexity : O(N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result=new int[temperatures.length];
        Stack<Integer> stack=new Stack<>();
        stack.push(0);
        for(int i=1;i<temperatures.length;i++){
       
            while(!stack.isEmpty() && temperatures[i]>temperatures[stack.peek()]){
               int top=stack.peek();
                result[top]=i-top;
                stack.pop();
            }
            
             stack.push(i);
        }
        
        while(!stack.isEmpty()){
            int i=stack.pop();
            result[i]=0;
        }
        
        return result;
    }
}