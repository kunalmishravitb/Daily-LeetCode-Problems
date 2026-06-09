class Solution {
    public long maxTotalValue(int[] nums, int k) {
        long max = 0;
        long min = Integer.MAX_VALUE;
        for (int i : nums) {
            max = Math.max(max,i);
            min = Math.min(min,i);
        }
        return (max - min) * k;
    }
}
