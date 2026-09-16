import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, 
                          String[] seller, int[] amount) {

        HashMap<String, String> master = new HashMap<>();

        for (int i = 0; i < enroll.length; i++) {
            master.put(enroll[i], referral[i]);
        }

        HashMap<String, Integer> result = new HashMap<>();

        for (int i = 0; i < seller.length; i++) {

            String now = seller[i];
            int money = amount[i] * 100;

            while (!now.equals("-") && money > 0) {

                int remain = money / 10;

                int myMoney = money - remain;

                result.put(
                    now,
                    result.getOrDefault(now, 0) + myMoney
                );

                now = master.get(now);

                money = remain;
            }
        }

        int[] answer = new int[enroll.length];

        for (int i = 0; i < enroll.length; i++) {
            answer[i] = result.getOrDefault(enroll[i], 0);
        }

        return answer;
    }
}