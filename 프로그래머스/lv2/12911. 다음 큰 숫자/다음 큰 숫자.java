import java.io.*;
import java.util.*;

class Solution {
    public int solution(int n) {
    int countOfOnes = Integer.bitCount(n);
    while (true) {
        n++;
        if (Integer.bitCount(n) == countOfOnes) {
            return n;
        }
    }
}

}