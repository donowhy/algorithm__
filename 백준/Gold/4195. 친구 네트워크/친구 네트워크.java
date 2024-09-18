import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

    // 부모와 네트워크 크기를 저장하는 배열
    static HashMap<String, String> parent = new HashMap<>();
    static HashMap<String, Integer> networkSize = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for (int t = 0; t < tc; t++) {
            int n = Integer.parseInt(br.readLine());

            // 테스트 케이스마다 초기화
            parent.clear();
            networkSize.clear();

            for (int i = 0; i < n; i++) {
                String[] s = br.readLine().split(" ");

                // 두 사람의 네트워크를 합친다
                union(s[0], s[1]);

                // 두 사람의 루트를 찾아 네트워크 크기를 출력
                System.out.println(networkSize.get(find(s[0])));
            }
        }
    }

    // Find 함수 (경로 압축 적용)
    private static String find(String person) {
        if (parent.get(person).equals(person)) {
            return person;
        } else {
            String root = find(parent.get(person));
            parent.put(person, root);
            return root;
        }
    }

    // Union 함수
    private static void union(String person1, String person2) {
        // 처음 보는 사람일 경우 초기화
        if (!parent.containsKey(person1)) {
            parent.put(person1, person1);
            networkSize.put(person1, 1);
        }
        if (!parent.containsKey(person2)) {
            parent.put(person2, person2);
            networkSize.put(person2, 1);
        }

        // 두 사람의 루트를 찾는다
        String root1 = find(person1);
        String root2 = find(person2);

        // 서로 다른 네트워크에 속해 있다면 합친다
        if (!root1.equals(root2)) {
            parent.put(root2, root1);
            networkSize.put(root1, networkSize.get(root1) + networkSize.get(root2));
        }
    }
}
