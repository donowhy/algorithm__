import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int[] inDegree; // 각 노드의 진입 차수
    static int[] buildTime; // 각 건물을 짓는 데 필요한 시간
    static int[] result; // 각 건물을 짓는 데 걸리는 최소 시간

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        inDegree = new int[n + 1];
        buildTime = new int[n + 1];
        result = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            buildTime[i] = Integer.parseInt(st.nextToken()); // 건물을 짓는 데 필요한 시간
            while (st.hasMoreTokens()) {
                int prerequisite = Integer.parseInt(st.nextToken());
                if (prerequisite == -1) break; // -1이면 종료
                graph.get(prerequisite).add(i); // 선행 관계 추가
                inDegree[i]++; // 진입 차수 증가
            }
        }

        // 위상 정렬
        topologicalSort();

        // 결과 출력
        for (int i = 1; i <= n; i++) {
            System.out.println(result[i]);
        }
    }

    static void topologicalSort() {
        Queue<Integer> q = new LinkedList<>();

        // 진입 차수가 0인 노드를 큐에 삽입
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
                result[i] = buildTime[i];
            }
        }

        // 큐가 빌 때까지 계속해서
        while (!q.isEmpty()) {
            int current = q.poll();

            // 현재 노드에서 이어지는 노드들 처리
            for (int next : graph.get(current)) {
                result[next] = Math.max(result[next], result[current] + buildTime[next]);
                inDegree[next]--;

                if (inDegree[next] == 0) {
                    q.offer(next);
                }
            }
        }
    }
}
