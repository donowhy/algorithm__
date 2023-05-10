import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static class Edge {
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    static int N, M, a, b, c, k, v;
    static ArrayList<Edge>[] graph;
    static boolean[] visited;
    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader( new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N];


        for(int i=0; i<N; i++){
            graph[i] = new ArrayList<Edge>();
        }

        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            graph[a-1].add(new Edge(b-1,c));
            graph[b-1].add(new Edge(a-1,c));
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());

            Deque<Integer> q = new LinkedList<>();
            visited = new boolean[N];
            visited[v-1] = true;
            int res = 0;
            q.add(v-1);

            while( !q.isEmpty()){
                int a = q.poll();
                for (Edge zz: graph[a]) {
                    if(!visited[zz.to] && k <= zz.weight){
                        q.add(zz.to);
                        visited[zz.to] = true;
                        res += 1;
                    }
                }
            }
            System.out.println(res);
        }


    }

}
