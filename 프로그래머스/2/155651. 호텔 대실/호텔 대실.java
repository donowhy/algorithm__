import java.util.*;

class Solution {
    public class Info implements Comparable<Info>{
        int s, e;
        
        public Info (int s, int e) {
            this.s = s;
            this.e = e;
        }
        
        @Override
        public int compareTo (Info o) {
            return Integer.compare(this.e, o.e);
        }
    }
    
    public int convert(String t) {
        String[] ts = t.split(":");
        
        int h = Integer.parseInt(ts[0]) * 60;
        int m = Integer.parseInt(ts[1]);
        
        return h + m;
    }
    
    public int solution(String[][] book_time) {
        int answer = 0;
        
        PriorityQueue<Info> pq = new PriorityQueue<>();
        
        Arrays.sort(book_time, (a, b) -> {
            return Integer.compare(convert(a[0]), convert(b[0]));
        });
        
        for(int i=0; i<book_time.length; i++) {
            if(!pq.isEmpty()) {
                Info newIn = new Info(convert(book_time[i][0]), convert(book_time[i][1]));
                
                Info in = pq.peek();
                if(in.e + 10 <= newIn.s) {
                    pq.poll();
                } else {
                    answer += 1;
                }
                pq.offer(newIn);
                
            }else {
                pq.offer(new Info(convert(book_time[i][0]), convert(book_time[i][1])));
                answer += 1;
            }
        }
        
        return answer;
    }
}