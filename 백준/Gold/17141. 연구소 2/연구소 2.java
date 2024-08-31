import java.util.*;
import java.io.*;
public class Main {
    static int N, M, result=Integer.MAX_VALUE, cnt=0;
    static int[][] map;
    static boolean[] visit;
    static List<Node> list;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    list.add(new Node(i, j));
                }
                if (map[i][j] == 0)
                    cnt++;
            }
        }

        cnt += list.size() - M;
        visit = new boolean[list.size()];
        if (cnt == 0) {
            result = 0;
        } else {
            DFS(0, 0);
        }
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);

    }
    public static void DFS(int depth, int idx) {
        if (depth == M) {
            BFS(copy(), cnt);
            return;
        }
        for (int i = idx; i < list.size(); i++) {
            visit[i] = true;
            DFS(depth+1, i+1);
            visit[i] = false;
        }
    }
    public static int[][] copy() {
        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copy[i][j] = map[i][j] == 2 ? 0 : map[i][j];
            }
        }
        for (int i = 0; i < list.size(); i++)
            if (visit[i])
                copy[list.get(i).x][list.get(i).y] = 2;
        return copy;
    }
    public static void BFS(int[][] map, int cnt) {
        Queue<Node> q = new LinkedList<>();
        for(int i = 0; i < list.size(); i++)
            if (visit[i])
                q.add(list.get(i));

        int dist = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            for (int k = 0; k < size; k++) {
                Node n = q.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = n.x + dx[i];
                    int ny = n.y + dy[i];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                        continue;
                    if (map[nx][ny] != 0)
                        continue;
                    map[nx][ny] = 2;
                    q.add(new Node(nx, ny));
                    cnt--;
                }
            }
            dist++;
            if (dist >= result)
                break;
            if (cnt == 0) {
                result = dist;
                return;
            }
        }
    }
}
class Node {
    int x, y;
    Node (int x, int y) {
        this.x = x;
        this.y = y;
    }
}