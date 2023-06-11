import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int[] edgeCount;
    static ArrayList<Integer> res = new ArrayList<>();
    static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edgeCount = new int[N+1];

        for(int i=0; i<N+1; i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            edgeCount[b] += 1;
        }

        for(int i=0; i < edgeCount.length; i++){
            if(edgeCount[i] ==0){
                pq.add(i);
            }
        }

        while(!pq.isEmpty()){
            int Node = pq.poll();
            res.add(Node);
            List<Integer> list = graph.get(Node);

            for(int i=0; i<graph.get(Node).size(); i++){
                edgeCount[list.get(i)]--;
                if(edgeCount[list.get(i)] == 0){
                    pq.offer(list.get(i));
                }
            }
        }
        for(int i=1; i<res.size(); i++){
            System.out.print(res.get(i) + " ");
        }

    }
}
