import java.util.*;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int zeroCount = 0;
        int matchCount = 0;

        for (int i = 0; i < 6; i++) {
            if (lottos[i] == 0) {
                zeroCount++;
                continue;
            }
            for (int j = 0; j < 6; j++) {
                if (lottos[i] == win_nums[j]) {
                    matchCount++;
                    break;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = Math.min(7 - (matchCount + zeroCount), 6); // 최고 순위
        answer[1] = Math.min(7 - matchCount, 6); // 최저 순위

        return answer;
    }
}
