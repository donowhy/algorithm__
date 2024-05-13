import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] move = {-1, 1, 2};
    static int N, M;
    static int[] prev; // 이전 위치를 저장하는 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        prev = new int[100001]; // 이전 위치 정보 저장
        Arrays.fill(prev, -1); // 초기값 설정

        bfs();
        reconstructPath();
    }

    private static void bfs() {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(N);
        boolean[] check = new boolean[100001];
        check[N] = true;

        while (!q.isEmpty()) {
            int current = q.poll();

            if (current == M) {
                break; // 목표에 도달하면 중단
            }

            for (int i = 0; i < 3; i++) {
                int next = i == 2 ? current * 2 : current + move[i];
                if (next < 0 || next > 100000 || check[next]) continue;
                check[next] = true;
                q.add(next);
                prev[next] = current; // 현재 위치에서 next로 이동했다는 것을 기록
            }
        }
    }

    private static void reconstructPath() {
        List<Integer> path = new ArrayList<>();
        for (int at = M; at != -1; at = prev[at]) {
            path.add(at);
        }
        Collections.reverse(path); // 정방향으로 경로를 재구성
        System.out.println(path.size() - 1); // 경로의 길이 출력
        for (int p : path) {
            System.out.print(p + " ");
        }
    }
}
