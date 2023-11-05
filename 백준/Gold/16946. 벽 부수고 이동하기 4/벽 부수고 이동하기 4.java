import java.io.*;
import java.util.*;

class Point
{
    int x,y;
    
    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
}
public class Main {
    static int N,M;
    static int [][] map;
    static int [][] group;
    static List<Integer> groupArea;
    static boolean [][] visited;
    static Queue<Point> blank;
    static Queue<Point> wall;
    static int [] dx = {-1,1,0,0};
    static int [] dy = {0,0,-1,1};
    static int groupSize;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        group = new int[N][M];
        groupArea = new ArrayList<>();

        blank = new LinkedList<>();
        wall = new LinkedList<>();

        for (int i = 0; i < N; ++i) {
            String tmp = br.readLine();

            for (int j = 0; j < M; ++j) {
                int next = tmp.charAt(j) - '0';
                map[i][j] = next;
                if (next < 1) blank.add(new Point(i, j));
                else wall.add(new Point(i, j));
            }
        }

        int groupNum = 0;
        visited = new boolean[N][M];

        // 빈칸 그룹화
        while (!blank.isEmpty()) {
            Point cur = blank.poll();
            if (visited[cur.x][cur.y]) continue;
            visited[cur.x][cur.y] = true;
            makeGroup(cur.x, cur.y, groupNum);
            groupNum++;
        }
        groupSize = groupArea.size();

        int [][] answer = new int[N][M];
        //벽 부수고 해당 인접한 그룹들의 값 더하기
        while (!wall.isEmpty()) {
            Point cur = wall.poll();
            answer[cur.x][cur.y] = getTotal(cur.x, cur.y) % 10;
        }

        StringBuilder sb = new StringBuilder();
        for(int [] i : answer)
        {
            for(int j : i) sb.append(j);
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void makeGroup(int i, int j,int groupNum)
    {
        Queue<Point> tmpQ = new LinkedList<>();
        tmpQ.add(new Point(i,j));
        int cnt = 0;
        while(!tmpQ.isEmpty())
        {
            Point cur = tmpQ.poll();

            group[cur.x][cur.y] = groupNum;
            cnt++;

            for(int idx = 0; idx < 4; ++idx)
            {
                int mx = cur.x + dx[idx];
                int my = cur.y + dy[idx];

                if(!isValid(mx,my) || visited[mx][my] || map[mx][my] > 0) continue;

                visited[mx][my] = true;
                tmpQ.add(new Point(mx,my));
            }
        }
        groupArea.add(cnt);
    }


    static int getTotal(int i, int j)
    {
        Queue<Point> tmpQ = new LinkedList<>();
        tmpQ.add(new Point(i,j));
        int total = 1;

        boolean [] visitedGroup = new boolean[groupSize];
        while(!tmpQ.isEmpty())
        {
            Point cur = tmpQ.poll();

            for(int idx = 0; idx < 4; ++idx)
            {
                int mx = cur.x + dx[idx];
                int my = cur.y + dy[idx];

                if(!isValid(mx,my) || map[mx][my] > 0) continue;

                //방문하지 않았던 그룹이면 토탈에 더해줌.
                int groupNum = group[mx][my];
                if(visitedGroup[groupNum]) continue;
                total += groupArea.get(groupNum);
                visitedGroup[groupNum] = true;
            }
        }
        return total;
    }
    static boolean isValid(int x, int y)
    {
        return x >= 0 && y >= 0 && x < N && y < M;
    }
}