import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int tc;
    static int N, cnt;
    static int[] arr;
    static boolean[] visited;
    static boolean[] finished;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());
        for (int i=0; i<tc; i++){
            N = Integer.parseInt(br.readLine());
            cnt = 0;
            arr = new int[N+1];
            visited = new boolean[N+1];
            finished = new boolean[N+1];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j=1; j<=N; j++){
                arr[j] = Integer.parseInt(st.nextToken());
            }

            for(int j=1; j<=N; j++){
                if(!visited[j]) dfs(j);
            }
            System.out.println(N-cnt);

        }
    }
    static void dfs(int now){
        visited[now] = true;
        int next = arr[now];

        if (!visited[next]){
            dfs(next);
        }else{
            if (!finished[next]){
                for(int i=next; i != now; i=arr[i]){
                    cnt++;
                }
                cnt++;
            }
        }
        finished[now] = true;
    }
}
