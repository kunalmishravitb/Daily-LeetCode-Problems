class Solution {
    public int[] buildArray(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            nums[i] 
            += (nums[nums[i]]&1023) << 10;
        }
        for(int i = 0; i < nums.length; i++){
            nums[i] >>= 10;
        }
        return nums;

    }
}
