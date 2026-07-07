import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        // 1. PriorityQueue 생성 (기본적으로 오름차순 정렬됨)
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        // 2. 모든 스코빌 지수를 큐에 넣음
        for(int i = 0; i < scoville.length; i++) {
            pq.offer(scoville[i]);
        }
        
        int result = 0;
        
        // 3. 가장 안 매운 음식의 스코빌 지수가 K보다 작은 동안 반복
        while(pq.peek() < K) {
            // 더 이상 섞을 음식이 없다면 (큐에 1개 남았는데 K보다 작다면)
            if(pq.size() < 2) {
                return -1;
            }
            
            // 4. 가장 안 매운 음식 2개를 꺼내서 섞음
            int a = pq.poll(); // 제일 안 매운 음식
            int b = pq.poll(); // 두 번째로 안 매운 음식
            
            int c = a + (b * 2);
            
            // 5. 섞은 음식을 다시 큐에 넣고 섞은 횟수 증가
            pq.offer(c);
            result++;
        }
        
        return result;
    }
}