import java.io.*;
import java.util.*;

public class Main {
    private static int[] arr;
    private static Integer[] dp;
    private static int max;

    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        arr = new int[n];
        dp = new Integer[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = arr[0];
        max = arr[0]; //max 값 초기화

        recur(n-1);
        System.out.println(max);
    }
    public  static int recur(int n){
        if(dp[n] == null){
            dp[n] = Math.max(recur(n-1) + arr[n], arr[n]);
            max = Math.max(dp[n], max);
        }
        return dp[n];
    }

}
