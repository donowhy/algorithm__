import java.io.*;
import java.util.*;

public class Main {
    static boolean[] bool;
    static ArrayList<Integer> arrayList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        bool = new boolean[N + 1];
        Arrays.fill(bool, true);
        bool[0] = false;
        bool[1] = false;

        for (int i = 2; i < (int) Math.pow(N,0.5) + 1; i++) {
            if (!bool[i]){
                continue;
            }
            for( int j = (int) Math.pow(i,2); j < N + 1; j += i){
                bool[j] = false;
            }
        }

        arrayList = new ArrayList<>();
        for (int i = 0; i < N + 1; i++){
            if (bool[i]) {
                arrayList.add(i);
            }
        }
        int start = 0;
        int end = 0;
        int hap = 0;
        int cnt = 0;

        while (true) {

            if(hap == N){
                cnt += 1;
            }
            if (hap > N) {
                hap -= arrayList.get(start);
                start += 1;
            }
            else if(end == arrayList.size()){
                break;
            }
            else if(hap <= N){
                hap += arrayList.get(end);
                end += 1;
            }
        }
        System.out.println(cnt);
    }
}
