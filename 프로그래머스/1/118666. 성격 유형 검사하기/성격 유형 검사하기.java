import java.util.HashMap;

class Solution {
    public String solution(String[] survey, int[] choices) {
        HashMap<Integer, HashMap<String, Integer>> map = new HashMap<>();

        // 각 지표의 성격 유형에 대한 HashMap 초기화
        map.put(1, new HashMap<String, Integer>());
        map.put(2, new HashMap<String, Integer>());
        map.put(3, new HashMap<String, Integer>());
        map.put(4, new HashMap<String, Integer>());

        // 각 성격 유형에 대한 점수 초기화
        String[][] types = {{"R", "T"}, {"C", "F"}, {"J", "M"}, {"A", "N"}};
        for (int i = 0; i < types.length; i++) {
            for (String type : types[i]) {
                map.get(i + 1).put(type, 0);
            }
        }

        // 설문 조사 결과 처리
        for (int i = 0; i < survey.length; i++) {
            String type1 = survey[i].substring(0, 1); // 첫 번째 성격 유형
            String type2 = survey[i].substring(1);    // 두 번째 성격 유형
            int score = choices[i] - 4;               // 선택된 점수를 기준 4에 맞춰 조정

            if (score > 0) {
                // type2 성격 유형에 점수 추가
                map.get(getIndex(type2)).put(type2, map.get(getIndex(type2)).get(type2) + score);
            } else if (score < 0) {
                // type1 성격 유형에 점수 추가
                map.get(getIndex(type1)).put(type1, map.get(getIndex(type1)).get(type1) - score);
            }
        }

        // 결과 문자열 생성
        String answer = "";
        for (int i = 1; i <= 4; i++) {
            HashMap<String, Integer> scoreMap = map.get(i);
            if (scoreMap.get(types[i - 1][0]) >= scoreMap.get(types[i - 1][1])) {
                answer += types[i - 1][0];
            } else {
                answer += types[i - 1][1];
            }
        }

        return answer;
    }

    // 성격 유형에 따른 지표 번호 반환
    private int getIndex(String type) {
        switch (type) {
            case "R": case "T": return 1;
            case "C": case "F": return 2;
            case "J": case "M": return 3;
            case "A": case "N": return 4;
            default: return -1; // 잘못된 성격 유형
        }
    }
}
