import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static ArrayList<Integer>[] adjList; 
    static boolean[] visited;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N];
        for(int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList[a].add(b);
            adjList[b].add(a);
        }

        for(int i = 0; i < N && answer == 0; i++) {
            visited = new boolean[N];
            dfs(i, 0);
        }

        System.out.println(answer);
    }

    static void dfs(int node, int depth) {
        if(depth == 4) {
            answer = 1;
            return;
        }

        visited[node] = true;
        for(int next : adjList[node]) {
            if(!visited[next]) {
                dfs(next, depth + 1);
            }
        }
        visited[node] = false; // backtracking
    }
}
