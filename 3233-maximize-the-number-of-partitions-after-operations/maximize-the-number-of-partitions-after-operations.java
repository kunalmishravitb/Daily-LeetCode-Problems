class Solution {
    public int maxPartitionsAfterOperations(String s, int k) {
        int n = s.length();
        int[][] L = new int[n][3];
        int[][] R = new int[n][3];

        int num = 0, mask = 0, cnt = 0;
        for (int i = 0; i < n - 1; i++) {
            int bitMask = 1 << (s.charAt(i) - 'a');
            if ((mask & bitMask) == 0) {
                cnt++;
                if (cnt <= k) {
                    mask |= bitMask;
                } else {
                    num++;
                    mask = bitMask;
                    cnt = 1;
                }
            }
            L[i + 1][0] = num;
            L[i + 1][1] = mask;
            L[i + 1][2] = cnt;
        }

        num = mask = cnt = 0;
        for (int i = n - 1; i > 0; i--) {
            int bitMask = 1 << (s.charAt(i) - 'a');
            if ((mask & bitMask) == 0) {
                cnt++;
                if (cnt <= k) {
                    mask |= bitMask;
                } else {
                    num++;
                    mask = bitMask;
                    cnt = 1;
                }
            }
            R[i - 1][0] = num;
            R[i - 1][1] = mask;
            R[i - 1][2] = cnt;
        }

        int maxVal = 0;
        for (int i = 0; i < n; i++) {
            int seg = L[i][0] + R[i][0] + 2;
            int split = L[i][1] | R[i][1];
            int count = Integer.bitCount(split);

            if (L[i][2] == k && R[i][2] == k && count < 26)
                seg += 1;
            else if (Math.min(count + 1, 26) <= k)
                seg -= 1;

            maxVal = Math.max(maxVal, seg);
        }
        return maxVal;
    }
}