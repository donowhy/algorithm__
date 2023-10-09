import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        
        int[] answer = new int[prices.length];
        
        for(int i=0; i<prices.length; i++){
            int cnt = 0;
            for(int j=i+1; j<prices.length; j++){
                cnt++;
                if(prices[i] > prices[j]) {
                    break;  // 가격이 떨어진 경우 종료
                }
            }
            answer[i] = cnt;
        }
        
        return answer;  // 배열을 직접 반환
    }
}
