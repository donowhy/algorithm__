import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static boolean[] visit;
    private static int n;
    private static int[] expect;
    private static ArrayList<ArrayList<Integer>> info;
    private static int[] parent;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        info = new ArrayList<>();
        visit = new boolean[n + 1];
        expect = new int[n + 1];
        parent = new int[n + 1];

        for (int i = 0; i < n + 1; i++) {
            info.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            info.get(from).add(to);
            info.get(to).add(from);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            expect[i] = Integer.parseInt(st.nextToken());
        }

        dfs16967(1, 0);
        System.out.println(1);
    }

    private static void dfs16967(int current, int index) {
        visit[current] = true;

        // 현재 값 뒤에 나올 수 있는 값들에 대한 정보를 저장.
        int count = 0;
        for (int nextCandidate : info.get(current)) {
            if (!visit[nextCandidate]) {
                visit[nextCandidate] = true;
                parent[nextCandidate] = current;
                count++;
            }
        }

        index++;
        for (int i = 0; i < count; i++) {
            int next = expect[index];
            if (parent[next] != current) {
                System.out.println(0);
                System.exit(0);
            }
            dfs16967(next, index);
        }
    }
}