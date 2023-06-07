import java.util.*;

class Solution {
    public int solution(int[] absolutes, boolean[] signs) {
        int sum = 0;
        for(int i=0; i<absolutes.length; i++){
            if( signs[i] == true) sum += absolutes[i];
            else sum -= absolutes[i];
        }
        
        
        return sum;

    }
}