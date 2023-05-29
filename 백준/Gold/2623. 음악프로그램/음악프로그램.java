import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int[] edgeCount;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N =Integer.parseInt(st.nextToken());
        M =Integer.parseInt(st.nextToken());
        edgeCount = new int[N+1];

        for(int i=0; i<N+1; i++){
            graph.add(new ArrayList<>());
        }

        for (int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int loop = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            for (int j= 0; j< loop - 1; j++){
                int b = Integer.parseInt(st.nextToken());
                graph.get(a).add(b);
                edgeCount[b]++;
                a = b;
            }
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i=0; i<edgeCount.length; i++){
            if(edgeCount[i] == 0){
                q.add(i);
            }
        }
        List<Integer> list = new ArrayList<>();
        while(!q.isEmpty()){
            int nodeNum = q.poll();
            list.add(nodeNum);
            List<Integer> valu = graph.get(nodeNum);

            for(int i=0; i< graph.get(nodeNum).size(); i++){
                edgeCount[valu.get(i)]--;
                if(edgeCount[valu.get(i)] == 0){
                    q.add(valu.get(i));
                }
            }
        }
        if(list.size() == N+1) {
            for (int i = 1; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        }else{
            System.out.println(0);
        }

    }
}
