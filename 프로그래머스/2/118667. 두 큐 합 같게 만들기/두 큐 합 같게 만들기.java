import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Long> q1 = new LinkedList<>();
        Queue<Long> q2 = new LinkedList<>();
        
        long sum1 = 0;
        long sum2 = 0;
        long totalSum = 0;
        
        for (int num : queue1) {
            q1.offer((long) num);
            sum1 += num;
        }
        
        for (int num : queue2) {
            q2.offer((long) num);
            sum2 += num;
        }
        
        totalSum = sum1 + sum2;
        
        // 합이 홀수이면 두 큐의 합을 같게 만들 수 없음
        if (totalSum % 2 != 0) return -1;
        
        long half = totalSum / 2;
        int count = 0;
        int size = queue1.length + queue2.length; // 최대 이동 가능 횟수
        
        // q1과 q2를 합친 큐처럼 다루기 위한 포인터 설정
        int index1 = 0;
        int index2 = 0;
        
        while (count < size * 2) { // 이동 횟수가 큐의 크기의 두 배를 넘지 않도록 설정
            if (sum1 == half) {
                return count; // 합이 같아지는 시점 반환
            }
            
            if (sum1 > half) {
                // q1에서 q2로 이동
                long num = q1.poll();
                q2.offer(num);
                sum1 -= num;
                sum2 += num;
            } else {
                // q2에서 q1로 이동
                long num = q2.poll();
                q1.offer(num);
                sum2 -= num;
                sum1 += num;
            }
            count++;
        }
        
        return -1; // 불가능한 경우
    }
}
