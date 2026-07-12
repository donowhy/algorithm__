import java.util.*;

class Solution {

    public int[] solution(String[] gems) {
        int totalTypeCount = new HashSet<>(Arrays.asList(gems)).size();

        Map<String, Integer> countMap = new HashMap<>();

        int left = 0;

        int answerStart = 0;
        int answerEnd = gems.length - 1;
        int minLength = gems.length;

        for (int right = 0; right < gems.length; right++) {

            // 오른쪽 보석 추가
            String rightGem = gems[right];
            countMap.put(
                rightGem,
                countMap.getOrDefault(rightGem, 0) + 1
            );

            // 모든 종류의 보석을 포함하고 있다면
            while (countMap.size() == totalTypeCount) {

                int currentLength = right - left + 1;

                // 더 짧은 구간 발견
                if (currentLength < minLength) {
                    minLength = currentLength;
                    answerStart = left;
                    answerEnd = right;
                }

                // 왼쪽 보석 제거
                String leftGem = gems[left];
                countMap.put(leftGem, countMap.get(leftGem) - 1);

                // 개수가 0이면 Map에서 제거
                if (countMap.get(leftGem) == 0) {
                    countMap.remove(leftGem);
                }

                left++;
            }
        }

        // 문제의 인덱스는 1부터 시작
        return new int[]{
            answerStart + 1,
            answerEnd + 1
        };
    }
}