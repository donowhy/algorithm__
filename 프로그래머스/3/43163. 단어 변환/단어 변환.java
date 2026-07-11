import java.util.*;

class Solution {
    public class Count {
        String s;
        int t;
        int dc;
        public Count (String s, int t, int dc) {
            this.s = s;
            this.t = t;
            this.dc = dc;
        }
        
        public String toString() {
            return s + " " + t + " " + dc;
        }
    }
    public int solution(String begin, String target, String[] words) {
        
        if (!Arrays.asList(words).contains(target)) {
            return 0;
        }
        
        int vt = bfs(begin, target, words);    
        
        return vt == 0 ? 0 : vt;
    }
    
    public int bfs(String begin, String target, String[] words) {
        boolean[] visited = new boolean[words.length];
        
        ArrayDeque<Count> q = new ArrayDeque<>();
        
        q.offerFirst(new Count(begin, 0, -1));
        
        while(!q.isEmpty()) {
            Count v = q.pollFirst();
            
            if(checkAnswer(v.s, target)){
                return v.t;
            }
            checkedDifference(words, v, q, visited);           
        }
        
        return 0;
    }
    
    public boolean checkAnswer(String a, String b) {
            String[] aa = a.split("");
            
            String[] bb = b.split("");
            
            for(int k=0; k<aa.length; k++) {
                if(!aa[k].equals(bb[k])) return false;
            }
        return true;
    }
    
    public void checkedDifference (String[] words, Count v, ArrayDeque<Count> c, boolean[] visited) {
        
        for(int i=0; i<words.length; i++) {
            if(visited[i]) continue;
            String[] ss = words[i].split("");
            
            String[] b = v.s.split("");
            
            int differCount = 0;
            
            for(int k=0; k<b.length; k++) {
                if(!b[k].equals(ss[k])) differCount += 1;
            }
            
            if(differCount <= 1) {
                c.offerLast(new Count(words[i], v.t + 1, differCount));
                visited[i] = true;
            }
        }
    }
}
    
