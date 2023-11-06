import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int n, l;
    static ArrayList<Dump> dumps = new ArrayList<>();

    static class Dump implements Comparable<Dump> {
        int start;
        int end;

        public Dump(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Dump o) {
            if(this.start != o.start) {
                return this.start - o.start;
            } else {
                return this.end - o.end;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            dumps.add(new Dump(start, end));
        }

        Collections.sort(dumps);

        int count = 0;
        int coveredUntil = 0;

        for(Dump dump : dumps){
            // 현재 웅덩이가 이전에 덮은 구간보다 앞에 있을 경우, 이전 구간의 끝을 현재 시작 지점으로 조정
            if (coveredUntil < dump.start) {
                coveredUntil = dump.start;
            }

            // 현재 웅덩이를 덮기 위해 필요한 널빤지의 개수
            while (coveredUntil < dump.end) {
                coveredUntil += l;
                count++;
            }
        }

        System.out.println(count);
    }
}
