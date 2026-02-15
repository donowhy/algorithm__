import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int n, k;
    private static int[][] map; 
    private static boolean[] visited;
    private static int minResult = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        visited = new boolean[n];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int k=0; k<n; k++) {          
            for(int i=0; i<n; i++) {      
                for(int j=0; j<n; j++) {  
                    if(i == j) continue;
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        visited[k] = true;
        dfs(k, 1, 0);

        System.out.println(minResult);
    }

    private static void dfs(int current, int count, int time) {
        if (time >= minResult) return;

        if (count == n) {
            minResult = Math.min(minResult, time);
            return;
        }

        for(int next=0; next<n; next++) {
            if (!visited[next]) {
                visited[next] = true;
                dfs(next, count + 1, time + map[current][next]);
                visited[next] = false; 
            }
        }
    }
}