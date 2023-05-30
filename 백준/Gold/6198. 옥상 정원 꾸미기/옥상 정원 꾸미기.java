
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static int N;
    static long[] lst;
    static ArrayList<Long> arr = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        lst = new long[N];
        for (int i=0; i<N; i++){
            long tmp = Long.parseLong(br.readLine());
            lst[i] = tmp;
        }
        long res = 0;
        for(int i=0; i<N; i++){
            while(!arr.isEmpty() && arr.get(arr.size() - 1) <= lst[i]){
                arr.remove(arr.size() - 1);
            }
            arr.add(lst[i]);
            res += arr.size() - 1;
        }

        System.out.println(res);



    }
}
