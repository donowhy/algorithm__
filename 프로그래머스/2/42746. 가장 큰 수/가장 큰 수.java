import java.util.*;

class Solution {

    public String solution(int[] numbers) {
        // 숫자를 문자열로 변환한 리스트 생성
        String[] strNumbers = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            strNumbers[i] = String.valueOf(numbers[i]);
        }

        // 사용자 정의 Comparator를 사용해 정렬
        Arrays.sort(strNumbers, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // o1 + o2와 o2 + o1을 비교해 더 큰 순서대로 정렬
                return (o2 + o1).compareTo(o1 + o2);
            }
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
