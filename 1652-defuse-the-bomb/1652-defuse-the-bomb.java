class Solution {
    public int[] decrypt(int[] code, int k) {
        int len = code.length;

        // Case 1: k == 0
        if (k == 0) {
            return new int[len];
        }

        // Case 2: k > 0
        else if (k > 0) {
            int left = 0, right = k;
            int sum = 0;

            // Compute the initial window sum
            for (int i = 0; i <= k; i++) {
                sum += code[i];
            }

            int[] res = new int[len];

            // Slide the window across the array
            while (left < len) {
                res[left] = sum - code[left];
                right = (right + 1) % len; // Move `right` forward circularly
                sum += code[right];       // Add the new element to the sum
                sum -= code[left];        // Remove the old element from the sum
                left++;                   // Move `left` forward
            }

            return res;
        }

        // Case 3: k < 0
        else {
            int right = len - 1, left = len - 1 + k;
            int sum = 0;

            // Compute the initial window sum
            for (int i = right; i >= left; i--) {
                sum += code[i];
            }

            int[] res = new int[len];

            // Slide the window across the array in reverse
            while (right >= 0) {
                res[right] = sum - code[right];
                left = ((left - 1) + len) % len; // Move `left` backward circularly
                sum += code[left];              // Add the new element to the sum
                sum -= code[right];             // Remove the old element from the sum
                right--;                        // Move `right` backward
            }

            return res;
        }
    }
}
