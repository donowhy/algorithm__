import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static ArrayList<String> list = new ArrayList<>();
    static String[] result;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        result = new String[N];
        for (int i = 0; i < N; i++) {
            int parseInt = Integer.parseInt(br.readLine());
            list.clear();  // 이전 테스트 케이스의 전화번호를 제거합니다.
            for (int j = 0; j < parseInt; j++) {
                list.add(br.readLine());
            }
            Collections.sort(list);

            boolean isPrefix = false;
            for (int k = 0; k < list.size() - 1; k++) {
                if (list.get(k + 1).startsWith(list.get(k))) {
                    isPrefix = true;
                    break;
                }
            }

            result[i] = isPrefix ? "NO" : "YES";
            System.out.println(result[i]);
        }
    }
}
