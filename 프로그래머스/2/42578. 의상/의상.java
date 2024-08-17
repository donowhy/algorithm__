import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        // 의상 카테고리와 각 카테고리의 의상 개수를 저장할 HashMap
        HashMap<String, Integer> categoryCount = new HashMap<>();
        
        // 의상 데이터 처리
        for (String[] c : clothes) {
            String category = c[1];
            categoryCount.put(category, categoryCount.getOrDefault(category, 0) + 1);
        }
        
        // 조합 가능한 의상 스타일의 수를 계산
        int result = 1; // 기본값 1은 아무것도 선택하지 않은 경우를 의미
        for (int count : categoryCount.values()) {
            result *= (count + 1); // 각 카테고리에서 선택하거나 선택하지 않을 수 있음
        }
        
        // 모든 카테고리에서 아무것도 선택하지 않은 경우를 제외
        return result - 1;
    }
}
