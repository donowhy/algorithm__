import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    static int N, M;
    static int[][] arr;
    static int[] result;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        result = new int[N];
        int[] idx = new int[N];
        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j=0; j<M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr[i]);
        }

//        for (int i=0; i<N; i++){
//            for(int j=0; j<M; j++){
//                System.out.print(arr[i][j] + " ");
//            }
//            System.out.println();
//        }

        for(int i=0; i<N; i++){
            result[i] = arr[i][0];
        }

        int answer = Integer.MAX_VALUE;

        List<Integer> list = Arrays.stream(result).boxed().collect(Collectors.toList());

        while (true){
            int max = Collections.max(list);
            int min = Collections.min(list);

            int i = list.indexOf(min);

            answer = Math.min(max-min, answer);

            if (idx[i] + 1 < M) {
                idx[i]++;
                result[i] = arr[i][idx[i]];
                list.set(i, result[i]);
            } else {
                break;
            }
        }

        System.out.println(answer);

    }
}

