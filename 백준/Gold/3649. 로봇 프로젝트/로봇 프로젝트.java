import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        
        // 입력이 계속될 수 있기 때문에 무한 루프 사용
        while ((input = br.readLine()) != null) {
            int x = Integer.parseInt(input) * 10_000_000; // 원하는 블록 길이
            int n = Integer.parseInt(br.readLine()); // 블록의 개수
            int[] blocks = new int[n];
            
            // 블록 길이 입력 받기
            for (int i = 0; i < n; i++) {
                blocks[i] = Integer.parseInt(br.readLine());
            }
            
            // 블록 배열을 정렬
            Arrays.sort(blocks);
            
            // 투 포인터 설정
            int left = 0;
            int right = n - 1;
            boolean found = false;
            
            // 투 포인터로 두 블록의 합 찾기
            while (left < right) {
                int sum = blocks[left] + blocks[right];
                
                if (sum == x) {
                    System.out.println("yes " + blocks[left] + " " + blocks[right]);
                    found = true;
                    break;
                } else if (sum < x) {
                    left++; // 합이 x보다 작으면 왼쪽 포인터 증가
                } else {
                    right--; // 합이 x보다 크면 오른쪽 포인터 감소
                }
            }
            
            // 두 블록을 찾지 못한 경우
            if (!found) {
                System.out.println("danger");
            }
        }
    }
}
