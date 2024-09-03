import java.util.*;
import java.io.*;

public class Main {
    private static int n;
    private static int d;
    private static int c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for(int i=0; i<tc; i++) {
            String[] s = br.readLine().split(" ");
            n = Integer.parseInt(s[0]);
            d = Integer.parseInt(s[1]);
            c = Integer.parseInt(s[2]);

            ArrayList<Node>[] nodes = new ArrayList[n + 1];

            for(int k=0; k<=n; k++) {
                nodes[k] = new ArrayList<>();
            }

            for(int j=0; j<d; j++) {
                s = br.readLine().split(" ");
                int a = Integer.parseInt(s[0]);
                int b = Integer.parseInt(s[1]);
                int t = Integer.parseInt(s[2]);
                nodes[b].add(new Node(a, t));
            }

            dijk(c, nodes);
        }
    }

    private static void dijk(int start, ArrayList<Node>[] nodes) {
        boolean[] check = new boolean[n + 1];
        int[] ar = new int[n + 1];
        Arrays.fill(ar, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        ar[start] = 0;

        while(!pq.isEmpty()) {
            Node no = pq.poll();
            int now = no.to;

            if(check[now]) continue;
            check[now] = true;

            for(Node nn : nodes[now]) {
                if(ar[nn.to] > ar[now] + nn.cost) {
                    ar[nn.to] = ar[now] + nn.cost;
                    pq.offer(new Node(nn.to, ar[nn.to]));
                }
            }
        }

        int cnt = 0;
        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            if (check[i]) {
                cnt++;
                maxTime = Math.max(maxTime, ar[i]);
            }
        }

        System.out.println(cnt + " " + maxTime);
    }

    private static class Node implements Comparable<Node>{
        int to, cost;
        Node(int t, int c) {
            to = t;
            cost = c;
        }

        @Override
        public int compareTo(Node n) {
            return Integer.compare(this.cost, n.cost);
        }
    }
}
