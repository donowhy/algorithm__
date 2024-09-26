import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static int n;
    private static int[] population;
    private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    private static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // 인구수 입력
        population = new int[n + 1];
        String[] popStr = br.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            population[i] = Integer.parseInt(popStr[i - 1]);
        }

        // 그래프 초기화
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // 인접 리스트 입력
        for (int i = 1; i <= n; i++) {
            String[] line = br.readLine().split(" ");
            int num = Integer.parseInt(line[0]);
            for (int j = 1; j <= num; j++) {
                int adj = Integer.parseInt(line[j]);
                graph.get(i).add(adj);
                graph.get(adj).add(i); // 양방향 연결
            }
        }

        // 모든 가능한 부분 집합을 생성하여 검사
        for (int i = 1; i < (1 << n) - 1; i++) { // 1부터 2^n - 2까지 부분 집합 생성
            checkSubset(i);
        }

        // 결과 출력
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    private static void checkSubset(int subset) {
        ArrayList<Integer> groupA = new ArrayList<>();
        ArrayList<Integer> groupB = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if ((subset & (1 << i)) != 0) {
                groupA.add(i + 1);
            } else {
                groupB.add(i + 1);
            }
        }

        if (isConnected(groupA) && isConnected(groupB)) {
            int popA = 0;
            int popB = 0;
            for (int a : groupA) popA += population[a];
            for (int b : groupB) popB += population[b];
            result = Math.min(result, Math.abs(popA - popB));
        }
    }

    private static boolean isConnected(ArrayList<Integer> group) {
        if (group.isEmpty()) return false;

        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(group.get(0));
        visited[group.get(0)] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int next : graph.get(current)) {
                if (!visited[next] && group.contains(next)) {
                    visited[next] = true;
                    queue.offer(next);
                    count++;
                }
            }
        }

        return count == group.size();
    }
}
