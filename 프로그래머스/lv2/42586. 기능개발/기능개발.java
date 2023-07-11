import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Deque<Integer> dq = new LinkedList<>();

        for (int i = 0; i < progresses.length; i++) {
            int k = (100 - progresses[i]) / speeds[i];
            
            if((100 - progresses[i]) % speeds[i] > 0){
                k += 1;
            }
        
            dq.add(k);
        }

        ArrayList<Integer> list = new ArrayList<>();

        while (!dq.isEmpty()) {
            int cnt = 0;
            int temp = dq.poll();
            cnt += 1;

            while (!dq.isEmpty() && temp >= dq.peek()) {
                dq.poll();
                cnt += 1;
            }
            list.add(cnt);
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        
        return result;
    }
}
