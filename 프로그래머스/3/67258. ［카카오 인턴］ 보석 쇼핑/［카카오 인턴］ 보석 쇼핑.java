import java.util.*;

class Solution {

    public int[] solution(String[] gems) {

        Set<String> gemTypes = new HashSet<>(Arrays.asList(gems));
        int requiredTypeCount = gemTypes.size();

        Map<String, Integer> countMap = new HashMap<>();

        int left = 0;
        int right = 0;

        int bestStart = 0;
        int bestEnd = gems.length - 1;
        int minLength = Integer.MAX_VALUE;

        while (right < gems.length) {

            // 오른쪽 보석 추가
            countMap.put(
                gems[right],
                countMap.getOrDefault(gems[right], 0) + 1
            );

            right++;

            // 모든 종류가 포함되었다면 왼쪽 축소
            while (countMap.size() == requiredTypeCount) {

                int currentLength = right - left;

                if (currentLength < minLength) {
                    minLength = currentLength;
                    bestStart = left;
                    bestEnd = right - 1;
                }

                String leftGem = gems[left];
                int count = countMap.get(leftGem);

                if (count == 1) {
                    countMap.remove(leftGem);
                } else {
                    countMap.put(leftGem, count - 1);
                }

                left++;
            }
        }

        return new int[]{
            bestStart + 1,
            bestEnd + 1
        };
    }
}