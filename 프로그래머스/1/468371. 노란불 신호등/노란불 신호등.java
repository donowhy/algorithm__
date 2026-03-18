class Solution {
    public int solution(int[][] signals) {
        int n = signals.length;
        int[] cycle = new int[n];
        
        // 1. 각 신호등의 전체 주기 계산 및 모든 주기의 최소공배수(최대 시간) 구하기
        long maxTime = 1;
        for (int i = 0; i < n; i++) {
            cycle[i] = signals[i][0] + signals[i][1] + signals[i][2];
            maxTime = lcm(maxTime, cycle[i]);
        }
        
        // 2. 1초부터 최대 주기(최소공배수)까지 시뮬레이션
        for (long t = 1; t <= maxTime; t++) {
            boolean allYellow = true;
            
            for (int i = 0; i < n; i++) {
                int g = signals[i][0];
                int y = signals[i][1];
                
                // 현재 시간이 주기 내에서 어느 위치인지 계산
                int rem = (int)((t - 1) % cycle[i]);
                
                // 노란불이 켜져 있는 구간인지 확인
                if (!(rem >= g && rem < g + y)) {
                    allYellow = false; // 하나라도 노란불이 아니면 실패
                    break;
                }
            }
            
            // 모든 신호등이 노란불이라면 그 시간이 정답!
            if (allYellow) {
                return (int)t;
            }
        }
        
        // 최소공배수까지 확인했는데도 없으면 불가능한 경우
        return -1;
    }
    
    // 최대공약수 구하기 (유클리드 호제법)
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    // 최소공배수 구하기
    private long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }
}