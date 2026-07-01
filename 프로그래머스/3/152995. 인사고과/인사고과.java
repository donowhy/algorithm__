import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int wanhoA = scores[0][0];
        int wanhoB = scores[0][1];
        int wanhoSum = wanhoA + wanhoB;

        Arrays.sort(scores, (a, b) -> {
            if (a[0] != b[0]) {
                return b[0] - a[0]; // 1번 점수 내림차순
            }
            return a[1] - b[1];     // 2번 점수 오름차순
        });

        int maxSecond = 0;
        int rank = 1;

        for (int[] score : scores) {
            int a = score[0];
            int b = score[1];

            // 앞에 있는 사람 중 1번 점수가 더 높고, 2번 점수도 더 높은 사람이 있음
            if (b < maxSecond) {
                // 완호가 탈락이면 -1
                if (a == wanhoA && b == wanhoB) {
                    return -1;
                }
                continue;
            }

            // 인센티브 대상자 중 완호보다 합계가 높으면 등수 밀림
            if (a + b > wanhoSum) {
                rank++;
            }

            maxSecond = Math.max(maxSecond, b);
        }

        return rank;
    }
}