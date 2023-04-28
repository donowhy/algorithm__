import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static boolean[] prime;  // 소수를 판별하기 위한 boolean 배열 선언
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int M = Integer.parseInt(st.nextToken());  // 구간의 시작
        int N = Integer.parseInt(st.nextToken());  // 구간의 끝

        prime = new boolean[N + 1];  // 구간의 끝을 포함하는 배열 생성
        get_prime();  // 소수 판별

        StringBuilder sb = new StringBuilder();  // 출력 결과를 한번에 출력하기 위한 StringBuilder 생성

        for(int i = M; i <= N; i++) {
            if(!prime[i]) sb.append(i).append('\n');  // 해당 수가 소수이면 StringBuilder에 추가
        }
        System.out.println(sb);  // 출력
    }

    public static void get_prime() {
        prime[0] = prime[1] = true;  // 0과 1은 소수가 아님

        for(int i = 2; i <= Math.sqrt(prime.length); i++) {  // 2부터 구간의 끝의 제곱근까지 반복
            if(prime[i]) continue;  // 이미 소수가 아니라면 다음으로 건너뜀
            for(int j = i * i; j < prime.length; j += i) {  // 해당 수의 배수는 소수가 아님
                prime[j] = true;
            }
        }
    }
}
