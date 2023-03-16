
import java.util.*;
public class Main {
    static int N;

    public String solution (int M){
        String answer = "";
        for(int i = 0; i<M; i++){
            answer += " ";
        }
        for (int i = 0; i<N-M; i ++){
            answer += "*";
        }
        return answer;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        Main T = new Main();
        for(int i =0 ; i <N; i++){
            System.out.println(T.solution(i));
        }
    }
}