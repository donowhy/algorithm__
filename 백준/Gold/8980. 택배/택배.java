import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {

    private static int n, m, k;

    private static class Things implements Comparable<Things> {
        int send, receive, counting;

        public Things(int send, int receive, int counting) {
            this.send = send;
            this.receive = receive;
            this.counting = counting;
        }

        @Override
        public int compareTo(Things t) {
            if (this.send != t.send) return Integer.compare(this.send, t.send);
            return Integer.compare(this.receive, t.receive);
        }
    }

    private static PriorityQueue<Things> pq = new PriorityQueue<>();
    private static Truck truck = new Truck();

    private static class Truck {
        int town;
        int capa;
        ArrayList<Things> things = new ArrayList<>();

        public Truck() {
        }

        public Truck(int town, int capa) {
            this.town = town;
            this.capa = capa;
        }
    }

    private static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");

        // 마을 수
        n = Integer.parseInt(s[0]);
        // 트럭 용량
        m = Integer.parseInt(s[1]);

        s = br.readLine().split(" ");
        k = Integer.parseInt(s[0]);

        for (int i = 0; i < k; i++) {
            s = br.readLine().split(" ");
            int send = Integer.parseInt(s[0]);
            int receive = Integer.parseInt(s[1]);
            int counting = Integer.parseInt(s[2]);

            pq.offer(new Things(send, receive, counting));
        }

        for (int town = 1; town <= n; town++) {
            // 현재 마을에 도착했을 때, 도착해야 하는 물건을 내림
            for (int i = truck.things.size() - 1; i >= 0; i--) {
                Things t = truck.things.get(i);
                if (t.receive == town) {
                    truck.capa -= t.counting;
                    truck.things.remove(i);
                }
            }

            // 현재 마을에서 보낼 물건을 확인
            while (!pq.isEmpty() && pq.peek().send == town) {
                Things things = pq.poll();

                int availableSpace = Math.min(m - truck.capa, things.counting);

                if (availableSpace > 0) {
                    truck.capa += availableSpace;
                    result += availableSpace;
                    truck.things.add(new Things(things.send, things.receive, availableSpace));
                }
            }
        }
        System.out.println(result);
    }
}
