import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split(" ");

            if (s[0].equals("push")) {
                dq.addLast(Integer.parseInt(s[1])); // 뒤에 추가
            } else if (s[0].equals("front")) {
                if (dq.isEmpty()) {
                    System.out.println(-1);
                } else {
                    System.out.println(dq.peekFirst()); // 앞 확인
                }
            } else if (s[0].equals("back")) {
                if (dq.isEmpty()) {
                    System.out.println(-1);
                } else {
                    System.out.println(dq.peekLast()); // 뒤 확인
                }
            } else if (s[0].equals("size")) {
                System.out.println(dq.size());
            } else if (s[0].equals("empty")) {
                System.out.println(dq.isEmpty() ? 1 : 0);
            } else if (s[0].equals("pop")) {
                if (dq.isEmpty()) {
                    System.out.println(-1);
                } else {
                    System.out.println(dq.pollFirst()); // 앞에서 제거
                }
            }
        }
    }
}
