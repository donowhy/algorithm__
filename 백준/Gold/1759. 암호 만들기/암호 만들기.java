import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    private static int l, c;
    private static String[] arr;
    private static final int NEEDS = 2;  // 최소 자음 개수
    private static final ArrayList<String> result = new ArrayList<>();
    private static final String AEIOU = "aeiou";  // 모음 체크를 위한 문자열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");

        l = Integer.parseInt(s[0]);  // 암호의 길이
        c = Integer.parseInt(s[1]);  // 주어진 알파벳 수

        arr = new String[c];
        s = br.readLine().split(" ");

        for (int i = 0; i < s.length; i++) {
            arr[i] = s[i];
        }

        Arrays.sort(arr);  // 알파벳 사전 순으로 정렬

        dfs("", 0, 0, 0);

        result.forEach(System.out::println);
    }

    private static void dfs(String lng, int start, int vowels, int consonants) {
        if (lng.length() == l) {
            if (vowels >= 1 && consonants >= NEEDS) {
                result.add(lng);
            }
            return;
        }

        for (int i = start; i < arr.length; i++) {
            String next = arr[i];
            if (AEIOU.contains(next)) {
                dfs(lng + next, i + 1, vowels + 1, consonants);
            } else {
                dfs(lng + next, i + 1, vowels, consonants + 1);
            }
        }
    }
}
