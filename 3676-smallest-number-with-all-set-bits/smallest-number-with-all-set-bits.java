class Solution {
    long getNext(int n) {
        long p = 1;
        while (p <= n) p <<= 1; // equivalent to p *= 2;
        return p;
    }

    int smallestNumber(int n) {
        // Next power of 2 > n
        long nxt = getNext(n);
        return (int)(nxt - 1);
    }
}