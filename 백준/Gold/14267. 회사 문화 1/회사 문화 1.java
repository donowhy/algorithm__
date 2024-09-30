import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static ArrayList<ArrayList<Integer>> arr  = new ArrayList<>();
    private static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        result = new int[n + 1];

        int[] pp = new int[n + 1];
        arr.add(new ArrayList<>());  // 루트 노드를 처리하기 위한 배열 초기화

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            pp[i] = Integer.parseInt(st.nextToken());
            arr.add(new ArrayList<>());
        }

        // 부모-자식 관계 설정
        for (int i = 1; i <= n; i++) {
            if (pp[i] != -1) {
                arr.get(pp[i]).add(i);
            }
        }

        // 명령어 입력
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());

            result[p] += score;
        }

        // DFS로 점수 합산
        dfs(1);  // 루트 노드는 1부터 시작

        // 결과 출력
        for (int i = 1; i <= n; i++) {
            System.out.print(result[i] + " ");
        }
    }

    private static void dfs(int node) {
        for (int child : arr.get(node)) {
            result[child] += result[node];  // 부모 노드의 점수를 자식 노드에 더함
            dfs(child);
        }
    }
}
