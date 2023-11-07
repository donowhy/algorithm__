import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int connected = Integer.parseInt(st.nextToken());
                if (connected == 1) {
                    graph.get(i).add(j);
                }
            }
        }

        String[] s = br.readLine().split(" ");
        int[] sequence = new int[s.length];

        for (int i = 0; i < s.length; i++) {
            sequence[i] = Integer.parseInt(s[i]);
        }

        for (int i = 0; i < sequence.length - 1; i++) {
            visited = new boolean[n + 1]; // 방문 배열 초기화
            
            int start = sequence[i];
            int target = sequence[i + 1];
            if (!dfs(start, target)) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    private static boolean dfs(int current, int target) {
        if (current == target) {
            return true;
        }
        visited[current] = true;

        for (Integer next : graph.get(current)) {
            if (!visited[next] && dfs(next, target)) {
                return true;
            }
        }

        return false;
    }
}
