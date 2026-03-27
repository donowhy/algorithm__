import java.util.*;

class Solution {
    int n;
    int maxWins = -1;
    int[] bestDice;
    int[][] gDice;

    public int[] solution(int[][] dice) {
        n = dice.length;
        gDice = dice;
        bestDice = new int[n / 2];
        
        // 1. 주사위 뽐기 (조합)
        boolean[] visited = new boolean[n];
        combination(0, 0, visited);
        
        return bestDice;
    }

    // [핵심 1] 중복 없는 '조합(Combination)' 구현
    private void combination(int depth, int start, boolean[] visited) {
        if (depth == n / 2) {
            calculateWins(visited); // 절반을 뽑았으면 승리 횟수 계산으로 넘어감
            return;
        }
        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(depth + 1, i + 1, visited); // 다음 번호(i+1)부터 탐색
            visited[i] = false;
        }
    }

    // 주사위 조합이 완성되었을 때, 승패를 계산하는 메서드
    private void calculateWins(boolean[] visited) {
        List<Integer> aDice = new ArrayList<>();
        List<Integer> bDice = new ArrayList<>();
        
        // visited를 기준으로 A와 B가 가져갈 주사위 인덱스 분리
        for (int i = 0; i < n; i++) {
            if (visited[i]) aDice.add(i);
            else bDice.add(i);
        }

        List<Integer> aSums = new ArrayList<>();
        List<Integer> bSums = new ArrayList<>();

        // 2. 모든 합 구하기
        getSums(aDice, 0, 0, aSums);
        getSums(bDice, 0, 0, bSums);

        // 3. 이분 탐색을 위한 B 합 배열 정렬
        Collections.sort(bSums);

        int wins = 0;
        // 4. 이분 탐색 (Lower Bound) 적용
        for (int a : aSums) {
            int left = 0;
            int right = bSums.size();
            
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (bSums.get(mid) < a) { // B의 합이 A보다 엄격히 작을 때
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            wins += left; // left 값이 곧 A가 이기는 경우의 수
        }

        // 5. 최다 승리 갱신 및 정답 저장
        if (wins > maxWins) {
            maxWins = wins;
            for (int i = 0; i < n / 2; i++) {
                bestDice[i] = aDice.get(i) + 1; // 문제에서 요구하는 1-based 인덱스로 변환
            }
        }
    }

    // [핵심 2] 재귀를 이용해 주사위 합의 모든 경우의 수(DFS) 구하기
    private void getSums(List<Integer> diceIndices, int depth, int currentSum, List<Integer> sums) {
        if (depth == n / 2) {
            sums.add(currentSum);
            return;
        }
        for (int i = 0; i < 6; i++) {
            // 현재 주사위의 눈금을 더해서 다음 깊이로 넘김 (변수 오염 방지)
            getSums(diceIndices, depth + 1, currentSum + gDice[diceIndices.get(depth)][i], sums);
        }
    }
}