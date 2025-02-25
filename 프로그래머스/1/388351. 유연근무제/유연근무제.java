class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
     int answer = 0;

        for (int i = 0; i < schedules.length; i++) {
            int schedule = schedules[i];
            int goal = ((schedule / 100 + (schedule % 100 + 10) / 60) * 100) + (schedule % 100 + 10) % 60;
            int startCnt = startday - 1;
            boolean chk = true;

            for (int j = 0; j < timelogs[i].length; j++) {
                int cmpTime = timelogs[i][j];
                if (startCnt % 7 < 5) { // 평일만 고려
                    if (goal < cmpTime) {
                        chk = false;
                        break;
                    }
                }
                startCnt++;
            }

            if (chk) answer++;
        }

        return answer;
    }
}