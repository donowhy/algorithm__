import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] time, result, inDegree;
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        time = new int[N+1];
        result = new int[N+1];
        inDegree = new int[N+1];
        graph = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            while(true) {
                int prev = Integer.parseInt(st.nextToken());
                if(prev == -1) break;
                graph[prev].add(i);
                inDegree[i]++;
            }
        }

        topologicalSort();

        for(int i = 1; i <= N; i++) {
            System.out.println(result[i]);
        }
    }

    static void topologicalSort() {
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i <= N; i++) {
            if(inDegree[i] == 0) {
                q.offer(i);
                result[i] = time[i];
            }
        }

        while(!q.isEmpty()) {
            int now = q.poll();
            for(int next : graph[now]) {
                result[next] = Math.max(result[next], result[now] + time[next]);
                inDegree[next]--;
                if(inDegree[next] == 0) q.offer(next);
            }
        }
    }
}
