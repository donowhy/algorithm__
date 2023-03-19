import java.io.*; //buffered, IOException ...
import java.util.*; //Array, Scanner 등

// parseInt, 구문, StringTokenizer, "; " !!!

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<n; i++ ) nums[i] = Integer.parseInt(st.nextToken());

        int x = Integer.parseInt(br.readLine());
        int stt = 0, end = n-1, cnt = 0;
        Arrays.sort(nums);
        while(stt != end) {
            int sum = nums[stt] + nums[end];
            if(sum == x) {
                cnt++;
                stt++;
            }   //while 문 안 if 문
            else if(sum > x) {end --;}
            else if (sum < x) {stt ++;}
        } //while 문 끝
        System.out.println(cnt);
        br.close();
    } //static method
}
