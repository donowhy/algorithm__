import java.util.*;

class Solution {
    
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long e = (long) n * times[times.length - 1];
        long s = 0L;
        
        long answer = 0;
        
        while(s <= e) {
            long mid = (s + e) / 2;
            boolean canI = check(mid, n, times);
            
            if(canI) {
                e = mid - 1;
                answer = mid;
            } else {
                s = mid + 1;
            }
        }
        
        
        return answer;
    }
    
    public boolean check (long mid, int n, int[] times) {
        long cnt = 0;
        
        for(int time : times) {
            cnt += mid / time;
        }
        
        if(n <= cnt) return true;
        return false;
    }
}