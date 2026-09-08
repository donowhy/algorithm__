import java.util.*;

class Solution {
    
    public class Pointer {
        int priority, order;
        
        public Pointer(int priority, int order) {
            this.priority = priority;
            this.order = order;
        }
    }
    
    public int solution(int[] priorities, int location) {
        
        ArrayDeque<Pointer> pointerList = new ArrayDeque<>();
        PriorityQueue<Integer> pq =
            new PriorityQueue<>((a, b) -> b.compareTo(a));
        
        for (int i = 0; i < priorities.length; i++) {
            pointerList.offer(new Pointer(priorities[i], i));
            pq.offer(priorities[i]);
        }
        
        int pollCnt = 0;
        
        while (true) {
            
            Pointer pointer = pointerList.poll();
            
            if (pointer.priority >= pq.peek()) {
                
                pq.poll();
                pollCnt++;
                
                if (pointer.order == location) {
                    return pollCnt;
                }
                
            } else {
                pointerList.offer(pointer);
            }
        }
    }
}