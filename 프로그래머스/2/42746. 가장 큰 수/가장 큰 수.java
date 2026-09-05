import java.util.*;

class Solution {

    public String solution(int[] numbers) {
        // 숫자를 문자열로 변환한 리스트 생성
        String[] strNumbers = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            strNumbers[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(strNumbers, (a, b) -> {
            return (b + a).compareTo(a + b);
        });

        // 정렬된 결과를 하나의 문자열로 합침
        StringBuilder answer = new StringBuilder();
        for (String num : strNumbers) {
            answer.append(num);
        }

        // 첫 문자가 '0'이면 "0"을 반환 (모든 수가 0인 경우)
        if (answer.charAt(0) == '0') {
            return "0";
        }

        return answer.toString();
    }
}
