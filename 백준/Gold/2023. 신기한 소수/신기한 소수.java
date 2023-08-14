import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static int N;
    static int[] list = {2, 3, 5, 7};
    static ArrayList<Integer> arr = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Boolean[] visited = new Boolean[N];
        for (Integer i : list) {
            dfs(i);
        }
    }

    static void dfs(Integer i){
        String s = String.valueOf(i);

        if(s.length() == N){
            System.out.println(s);
            return;
        }

        for (int j=0; j<10; j++) {
            Integer value = i * 10 + j;
            if(isPrime(value)){
                dfs(value);
            }

        }
    }

    static Boolean isPrime (Integer value){
        for(int i=2; i<value/2 + 1; i++){
            if(value % i == 0){
                return false;
            }
        }
        return true;
    }
}
