import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static int n;
    static ArrayList<ArrayList<Node>> arr = new ArrayList<>();
    static boolean[] visited;
    static int maxIndex = 0;
    static int sum = 0;

    static class Node {
        int send;
        int cost;

        public Node(int send, int cost) {
            this.send = send;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        visited = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            arr.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            String[] strings = br.readLine().split(" ");
            for (int j = 1; j < strings.length - 1; j += 2) {
                int send = Integer.parseInt(strings[j]);
                int cost = Integer.parseInt(strings[j + 1]);
                arr.get(Integer.parseInt(strings[0])).add(new Node(send, cost));
                arr.get(send).add(new Node(Integer.parseInt(strings[0]), cost));

            }

        }
        dfs(1, 0);

        sum = 0;
        visited = new boolean[n+1];
        dfs(maxIndex, 0);

        System.out.println(sum);
    }

    static void dfs(int node, int hap){
        if(hap > sum){
            sum = hap;
            maxIndex = node;
        }
        ArrayList<Node> nodes = arr.get(node);
        visited[node] = true;
        for (Node ns : nodes) {
            if(!visited[ns.send]){
                dfs(ns.send, hap + ns.cost);
                visited[ns.send] = true;
            }
        }
    }
}
