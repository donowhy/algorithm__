import java.io.*;
import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        Integer[] numbersArr = Arrays.stream(numbers).boxed().toArray(Integer[]::new);
        
        Arrays.sort(numbersArr, new Comparator<Integer>(){
            public int compare(Integer i1, Integer i2) {
                String str1 = i1.toString();
                String str2 = i2.toString();
                return (str2 + str1).compareTo(str1 + str2);
            }
        });
        
        if (numbersArr[0] == 0) { // 배열의 첫 번째 요소가 0이라면 모든 숫자가 0인 것이므로 "0"을 반환
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (int i : numbersArr) {
            sb.append(i);
        }
        
        return sb.toString();
    }
}
