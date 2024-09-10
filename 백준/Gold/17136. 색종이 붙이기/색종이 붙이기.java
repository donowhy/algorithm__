import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    private static int[][] map = new int[10][10];
    private static int[] paperCount = {0, 5, 5, 5, 5, 5}; // 각 크기의 색종이 남은 수
    private static int minPaper = Integer.MAX_VALUE; // 최소 색종이 개수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        for (int i = 0; i < 10; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        // 백트래킹 시작
        backtrack(0, 0, 0); // 시작 위치 (0,0)에서 시작, 사용한 종이 개수 0
        System.out.println(minPaper == Integer.MAX_VALUE ? -1 : minPaper);
    }

    // 백트래킹 함수
    private static void backtrack(int x, int y, int usedPaper) {
        if (x >= 10) { // 모든 줄을 다 덮었으면
            minPaper = Math.min(minPaper, usedPaper);
            return;
        }

        if (y >= 10) { // 가로 끝에 도달하면 다음 줄로 이동
            backtrack(x + 1, 0, usedPaper);
            return;
        }

        if (map[x][y] == 0) { // 이미 덮인 부분이면 넘어감
            backtrack(x, y + 1, usedPaper);
            return;
        }

        // 색종이 붙이기 시도
        for (int size = 5; size >= 1; size--) {
            if (paperCount[size] > 0 && canAttach(x, y, size)) {
                attachPaper(x, y, size, 0); // 색종이 붙이기
                paperCount[size]--; // 색종이 사용
                backtrack(x, y + 1, usedPaper + 1); // 다음 위치로 이동
                attachPaper(x, y, size, 1); // 색종이 떼기 (원상복구)
                paperCount[size]++; // 색종이 복구
            }
        }
    }

    // 색종이를 붙일 수 있는지 확인
    private static boolean canAttach(int x, int y, int size) {
        if (x + size > 10 || y + size > 10) return false; // 색종이가 범위를 넘어가면 불가능
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[x + i][y + j] != 1) {
                    return false; // 붙일 위치에 1이 아닌 곳이 있으면 불가능
                }
            }
        }
        return true;
    }

    // 색종이를 붙이거나 떼는 함수
    private static void attachPaper(int x, int y, int size, int state) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[x + i][y + j] = state; // 0이면 색종이 덮음, 1이면 색종이 뗌
            }
        }
    }
}
