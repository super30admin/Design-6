class Solution {// time of O(n) and space of constant O(1)
    public void nextPermutation(int[] nums) {
        //Base case
        if(nums.length <2)
            return;
        
        int i = nums.length - 2;
        // finding the first min that should be on right side
        while(i >=0 && nums[i+1]<=nums[i]){
            i--;
        }
        //finding the max that can be swapped with the min above
        if(i>=0){
            int j = nums.length -1;
            while(j>=0 && nums[j]<= nums[i]){
                j--;
            }
            swap(i,j,nums);
        }
        reverse(nums, i+1);
    }
    private void reverse(int[] nums , int start){
        int s = start , e = nums.length - 1;
        while(s<e){
            swap(s,e,nums);
            s++;
            e--;
        }
    }
    private void swap(int x ,int y ,int[] nums){
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}