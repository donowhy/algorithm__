import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static Integer[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new Integer[N];

        for(int i=0; i<N; i++){
            int tmp = Integer.parseInt(br.readLine());
            arr[i] = tmp;
        }

        Arrays.sort(arr);

        System.out.println(find_twoPoint());
    }
    static int find_twoPoint(){
        int start_idx = 0;
        int end_idx = 1;
        int min = (int) Math.pow(10, 10);

        while(start_idx <= end_idx){

            int tmp = Math.abs(arr[start_idx] - arr[end_idx]);

            if(tmp < M){
                end_idx++;
            }

            else if (tmp >= M){
                min = Math.min(min, tmp);
                start_idx += 1;
            }

            if (end_idx == N){
                break;
            }

        }

        return min;
    }
}
