import java.util.*;

class Solution {
    
    class CustomDate {
        int year;
        int month;
        int day;
        public CustomDate(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }
    }
    
    private Integer transStringToInteger(String nums){
        return Integer.valueOf(nums);
    }
    
    private boolean calc(String[] tempa, String termType, CustomDate nowDate, HashMap<String, Integer> term) {
        CustomDate privacyDate = new CustomDate(
            transStringToInteger(tempa[0]), 
            transStringToInteger(tempa[1]), 
            transStringToInteger(tempa[2])
        );

        int totalDaysPrivacy = (privacyDate.year * 12 + privacyDate.month - 1) * 28 + privacyDate.day;
        int totalDaysNow = (nowDate.year * 12 + nowDate.month - 1) * 28 + nowDate.day;

        int termDays = term.get(termType) * 28;

        return totalDaysNow - totalDaysPrivacy < termDays;
    }


    
    public int[] solution(String today, String[] terms, String[] privacies) {
        String[] todayParts = today.split("\\.");
        CustomDate nowDate = new CustomDate(transStringToInteger(todayParts[0]), 
                                            transStringToInteger(todayParts[1]), 
                                            transStringToInteger(todayParts[2]));

        HashMap<String, Integer> term = new HashMap<>();
        for (String t : terms) {
            String[] tempa = t.split(" ");
            term.put(tempa[0], transStringToInteger(tempa[1]));
        }

        ArrayList<Integer> answerList = new ArrayList<>();
        for (int i = 0; i < privacies.length; i++) {
            String[] parts = privacies[i].split(" ");
            String[] privacyDateParts = parts[0].split("\\.");
            if (!calc(privacyDateParts, parts[1], nowDate, term)) {
                answerList.add(i + 1); 
            }
        }

        return answerList.stream().mapToInt(i -> i).toArray();
    }
}
