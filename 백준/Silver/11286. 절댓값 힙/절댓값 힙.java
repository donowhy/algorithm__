import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main{
    public static class ABS implements Comparable<ABS>{
        int x;
        int absx;

        public ABS(int x, int absx) {
            this.x = x;
            this.absx = absx;
        }

        @Override
        public int compareTo(ABS o) {
            if(this.absx != o.absx) {
                return Integer.compare(this.absx, o.absx);
            }
            return Integer.compare(this.x, o.x);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<ABS> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int param = Integer.parseInt(br.readLine());

            if(param != 0) {
                pq.add(new ABS(param, Math.abs(param)));
            }
            else {
                if(pq.isEmpty()){
                    System.out.println(0);
                    continue;
                }
                ABS value = pq.poll();
                System.out.println(value.x);
            }
        }
    }
}
