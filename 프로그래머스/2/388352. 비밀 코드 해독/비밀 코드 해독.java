import java.util.*;

class Solution {
    public int solution(int n, int[][] q, int[] ans) {
        int m = q.length;
        long[] qMask = new long[m];
        for (int i = 0; i < m; i++) {
            long mask = 0L;
            for (int v : q[i]) {
                mask |= 1L << (v - 1);
            }
            qMask[i] = mask;
        }
        
        if (n < 5) return 0;
        long answer = 0;
        long comb = (1L << 5) - 1;
        long limit = 1L << n;

        while (comb < limit) {
            boolean ok = true;
            for (int i = 0; i < m; i++) {
                int cnt = Long.bitCount(comb & qMask[i]);
                if (cnt != ans[i]) { ok = false; break; }
            }
            if (ok) answer++;

            long c = comb & -comb;
            long r = comb + c;
            comb = (((r ^ comb) >>> 2) / c) | r;
        }

        return (int) answer;
    }
}
