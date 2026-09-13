import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {

        int s = 1;
        int e = 100_000;

        while (s < e) {
            int mid = (s + e) / 2;

            if (calculated(mid, diffs, times, limit)) {
                e = mid;
            } else {
                s = mid + 1;
            }
        }

        return s;
    }

    public boolean calculated(int lvl, int[] diffs, int[] times, long limit) {

        long totalTime = 0;

        for (int i = 0; i < diffs.length; i++) {

            int diff = diffs[i];

            if (lvl >= diff) {
                totalTime += times[i];
            } else {
                long passedTime;

                if (i != 0) {
                    passedTime =
                        (long)(diff - lvl)
                        * (times[i] + times[i - 1])
                        + times[i];
                } else {
                    passedTime =
                        (long)(diff - lvl) * times[i]
                        + times[i];
                }

                totalTime += passedTime;
            }

            if (totalTime > limit) {
                return false;
            }
        }

        return true;
    }
}