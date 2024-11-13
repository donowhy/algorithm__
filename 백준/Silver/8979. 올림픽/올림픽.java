import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    private static class Medal implements Comparable<Medal>{
        int national, gold, silver, dong;

        public Medal(int national, int gold, int silver, int dong) {
            this.national = national;
            this.gold = gold;
            this.silver = silver;
            this.dong = dong;
        }

        @Override
        public int compareTo(Medal medal) {
            if (this.gold != medal.gold) {
                return Integer.compare(medal.gold, this.gold);
            }
            if (this.silver != medal.silver) {
                return Integer.compare(medal.silver, this.silver);
            }
            if (this.dong != medal.dong) {
                return Integer.compare(medal.dong, this.dong);
            }
            return Integer.compare(this.national, medal.national);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int target = Integer.parseInt(s[1]);

        PriorityQueue<Medal> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            String[] values = br.readLine().split(" ");
            int nation = Integer.parseInt(values[0]);
            int gold = Integer.parseInt(values[1]);
            int silver = Integer.parseInt(values[2]);
            int dong = Integer.parseInt(values[3]);
            pq.add(new Medal(nation, gold, silver, dong));
        }

        for (int i = 1; i <= target; i++) {
            Medal medal = pq.poll();
            if(target == i) {
                System.out.println(medal.national);
            }
        }

    }
}
