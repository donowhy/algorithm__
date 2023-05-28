import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, tc, goal;
    static ArrayList<ArrayList<Integer>> arr;
    static int[] time, edgeCount, res;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());
        for (int T=0; T<tc; T++ ){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            time = new int[N+1];
            res = new int[N+1];
            edgeCount = new int[N+1];
            arr = new ArrayList<ArrayList<Integer>>();

            for(int i=0; i<N+1; i++){
                arr.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());
            for(int i=1; i<N+1; i++){
                time[i] = Integer.parseInt(st.nextToken());
            }

            for(int i=0; i<M; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                arr.get(a).add(b);
                edgeCount[b] ++;
            }

            goal = Integer.parseInt(br.readLine());

            Deque<Integer> q = new LinkedList<>();

            for(int i=1; i<= N; i++){
                if (edgeCount[i]==0){
                    q.offer(i);
                    res[i] = time[i];
                }
            }
            while(!q.isEmpty()) {
                int nodeNUm = q.poll();
                for (int nextNode : arr.get(nodeNUm)){
                    res[nextNode] = Math.max(res[nextNode], res[nodeNUm] + time[nextNode]);
                    edgeCount[nextNode]--;
                    if (edgeCount[nextNode] == 0){
                        q.offer(nextNode);
                    }
                }
            }
            System.out.println(res[goal]);
        }
    }
}
