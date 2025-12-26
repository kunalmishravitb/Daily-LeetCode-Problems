class Solution {
    public int bestClosingTime(String s) {
        int n = s.length();
        int[] prefixN = new int[n + 1];
        int[] suffixY = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            prefixN[i] = prefixN[i - 1];
            if (s.charAt(i - 1) == 'N') prefixN[i]++;
        }

        for (int i = n - 1; i >= 0; i--) {
            suffixY[i] = suffixY[i + 1];
            if (s.charAt(i) == 'Y') suffixY[i]++;
        }

        int best = Integer.MAX_VALUE, ans = 0;
        for (int j = 0; j <= n; j++) {
            int pen = prefixN[j] + suffixY[j];
            if (pen < best) {
                best = pen;
                ans = j;
            }
        }
        return ans;
    }
}
