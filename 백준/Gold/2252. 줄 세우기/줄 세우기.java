import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N, M;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] edgeCount = new int[N+1];

        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < N+1; i++){
            graph.add(new ArrayList<Integer>());
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            edgeCount[b]++;

        }

        Queue<Integer> q = new LinkedList<>();

        for(int i=1; i<edgeCount.length; i++){
            if(edgeCount[i] == 0){
                q.offer(i);
            }
        }
        ArrayList<Integer> array = new ArrayList<>();
        while(!q.isEmpty()){
            int nodeNo = q.poll();
            array.add(nodeNo);

            List<Integer> list = graph.get(nodeNo);

            for(int i=0; i < list.size(); i++){
                edgeCount[list.get(i)] -- ;

                if(edgeCount[list.get(i)] == 0){
                    q.offer(list.get(i));
                }
            }
        }
        for(int i=0; i< array.size();i++){
            System.out.print(array.get(i) + " ");
        }
    }
}
