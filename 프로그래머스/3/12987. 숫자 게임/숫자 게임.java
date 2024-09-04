import java.util.Arrays;

class Solution {
    public int solution(int[] A, int[] B) {
        // A팀과 B팀의 카드를 정렬
        Arrays.sort(A);
        Arrays.sort(B);

        int n = A.length;
        int aPointer = 0;
        int bPointer = 0;
        int score = 0;

        // 두 포인터를 사용하여 각 팀의 카드 비교
        while (aPointer < n && bPointer < n) {
            if (B[bPointer] > A[aPointer]) {
                // B팀이 승리할 수 있는 경우 점수를 얻고 A팀의 다음 카드로 이동
                score++;
                aPointer++;
                bPointer++;
            } else {
                // B팀이 이길 수 없는 경우, 다음 B팀 카드로 이동
                bPointer++;
            }
        }

        return score;
    }
}
