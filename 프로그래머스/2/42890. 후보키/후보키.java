import java.util.*;

class Solution {
    public int solution(String[][] relation) {
        int answer = 0;
        int n = relation[0].length;
        List<Integer> candidateKeys = new ArrayList<>();
        
        // 모든 조합을 찾기 위해 DFS 실행
        for (int i = 1; i < (1 << n); i++) {
            if (isUnique(relation, i) && isMinimal(candidateKeys, i)) {
                candidateKeys.add(i);
            }
        }
        
        answer = candidateKeys.size();
        return answer;
    }
    
    private boolean isUnique(String[][] relation, int subset) {
        Set<String> seen = new HashSet<>();
        for (String[] tuple : relation) {
            StringBuilder key = new StringBuilder();
            for (int i = 0; i < tuple.length; i++) {
                if ((subset & (1 << i)) != 0) {
                    key.append(tuple[i]).append(",");
                }
            }
            if (!seen.add(key.toString())) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isMinimal(List<Integer> candidateKeys, int subset) {
        for (int key : candidateKeys) {
            if ((key & subset) == key) {
                return false;
            }
        }
        return true;
    }
}
