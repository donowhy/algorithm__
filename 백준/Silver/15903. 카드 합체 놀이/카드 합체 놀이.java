import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static Long N, M;
    static PriorityQueue<Long> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++){
            pq.add(Long.parseLong(st.nextToken()));
        }

        //System.out.println(pq);

        for(int i=0; i<M; i++){
            Long x = pq.poll();
            Long y = pq.poll();

            //System.out.println(x + " " + y);

            Long temp = x + y;

            x = temp;
            y = temp;

            pq.add(x);
            pq.add(y);
        }

        Long cnt = 0L;

        //System.out.println(pq + " pq");

        for (Long i : pq) {
            //System.out.println(i);
            cnt += i;
        }

        System.out.println(cnt);

    }
}
