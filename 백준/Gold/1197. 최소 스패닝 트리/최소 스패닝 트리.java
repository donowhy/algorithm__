import java.io.*;
import java.util.*;



public class Main {

    static int Vertex, Edge;
    static int start = 1;
    static ArrayList<node>[] arr;
    static PriorityQueue<node> pq = new PriorityQueue<>();
    static boolean[] visited;
    static long answer =0;

    public static class node implements Comparable<node>{
        int vertex;
        int weight;

        public node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(node o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {

        Scanner sc =new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine()," ");
        Vertex = Integer.parseInt(st.nextToken());
        Edge = Integer.parseInt(st.nextToken());

        visited	 = new boolean[Vertex+1]; // 각 정점은 1부터 시작한다.

        arr = new ArrayList[Vertex+1];
        for ( int i=0; i<=Vertex; i++) {
            arr[i] = new ArrayList<>();
        }

        // 정점간 연결
        for( int i=0; i<Edge; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            arr[from].add(new node(to,weight));
            arr[to].add(new node(from,weight));
        }


        // 시작점은 아무곳이나 상관 없지만, 1번 부터 시작하자.
        prim();

        System.out.println(answer);
    }
    private static void prim() {

        // 1번부터 시작해서 연결되어 있는 곳 모두 탐색하자.
        pq.add(new node(start,0));
        int count =0;
        while(!pq.isEmpty()) {

            // 꺼내고
            node cur = pq.poll();

            // 그굿이 방문한 곳이라면 패스 !  ** 이부분이 꼭 있어야한다. why? 큐를 꺼내는과정에서 예전에 넣어놨던 친구들이 나올 수 가 있잖아 !
            if( visited[cur.vertex]) continue;

            // 방문처리
            visited[cur.vertex] = true;

            // 방문했으니까, 가중치 더해주기
            answer += cur.weight;

            for ( node next : arr[cur.vertex]) {
                if ( !visited[next.vertex]) {
                    pq.add(next);
                }
            }
            if ( ++ count == Vertex )break;



        }

    }


}