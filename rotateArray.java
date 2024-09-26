// Time Complexity - O(n) since we do it in total of 2 passes
// Space Complexity - O(1) No extra space

/**
 * Here we first see dp k%nums.length since if k is more than the length it
 * should be cyclic
 * 
 * We do reverse three times 1) COmplete array 2) k to end of array 3) 1 to k
 */
public class Solution {

    public void rotate(int[] nums, int k) {

        if (nums == null || nums.length < 2) {
            return;
        }

        k = k % nums.length;
        reverse(nums, 0, nums.length - k - 1);
        reverse(nums, nums.length - k, nums.length - 1);
        reverse(nums, 0, nums.length - 1);

    }

    private void reverse(int[] nums, itont i, int j) {
        int tmp = 0;
        while (i < j) {
            tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
            i++;
            j--;
        }
    }
}