import java.util.*;

class Node {
    int x;
    int y;
    
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    String[][] map;
    int[] dx = {1,-1,0,0};
    int[] dy = {0,0,1,-1};
    boolean[][] vis;
    int n,m;
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        n = storage.length;
        m = storage[0].length();
        map = new String[n+2][m+2];
        for (int i=0; i<n+2; i++) {
            Arrays.fill(map[i],"-1");
        }
        
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                map[i+1][j+1] = String.valueOf(storage[i].charAt(j));
            }
        }
        
        for (int i=0; i<requests.length; i++) {
            String target = requests[i];
            dfs();
            if (target.length() == 2) removeAll(String.valueOf(target.charAt(0)));
            else remove(target);
        }
        
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
                if (map[i][j].equals("-1")) continue;
                answer++;
            }
        }
        
        return answer;
    }
    
    void dfs() {
        vis = new boolean[n+2][m+2];
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0,0));
        vis[0][0] = true;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (int dir=0; dir<4; dir++) {
                int nx = dx[dir] + cur.x;
                int ny = dy[dir] + cur.y;
                if (nx < 0 || ny < 0 || nx >= m+2 || ny >= n+2) continue;
                if (vis[ny][nx] || map[ny][nx] != "-1") continue;
                vis[ny][nx] = true;
                q.add(new Node(nx,ny));
            }
        }
    }
    
    // 크레인 이용
    void removeAll(String target) {
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
                if (map[i][j].equals(target)) map[i][j] = "-1";
            }
        }
    }
    
    // 지게차
    void remove(String target) {
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
                if (map[i][j].equals(target)) {
                    if (check(j,i)) map[i][j] = "-1";   
                }
            }
        }
    }
    
    boolean check(int x, int y) {
        for (int dir=0; dir<4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (vis[ny][nx]) return true;
        }
        return false;
    }
}