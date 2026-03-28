import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    private static int n, m, k;
    private static int[][] map;
    private static boolean[][][] visited;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};

    private static class Node {
        int x, y, k, cost;
        public Node(int x, int y, int k, int cost) {
            this.x = x;
            this.y = y;
            this.k = k; 
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m][k + 1];

        for(int i = 0; i < n; i++) {
            String s = br.readLine();
            for(int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        if (n == 1 && m == 1) return 1;

        ArrayDeque<Node> q = new ArrayDeque<>();
        q.offer(new Node(0, 0, 0, 1));
        visited[0][0][0] = true; 

        while(!q.isEmpty()) {
            Node node = q.poll();

            if(node.x == n - 1 && node.y == m - 1) {
                return node.cost;
            }

            for(int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                if(map[nx][ny] == 0) {
                    if(!visited[nx][ny][node.k]) {
                        visited[nx][ny][node.k] = true;
                        q.offer(new Node(nx, ny, node.k, node.cost + 1));
                    }
                } 
                else if(map[nx][ny] == 1 && node.k < k) {
                    if(!visited[nx][ny][node.k + 1]) {
                        if(node.cost % 2 != 0) { 
                            visited[nx][ny][node.k + 1] = true;
                            q.offer(new Node(nx, ny, node.k + 1, node.cost + 1));
                        } else { 
                            q.offer(new Node(node.x, node.y, node.k, node.cost + 1));
                        }
                    }
                }
            }
        }

        return -1;
    }
}