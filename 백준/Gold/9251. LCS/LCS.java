import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String x = br.readLine();
        String y = br.readLine();

        int a = x.length();
        int b = y.length();

        ArrayList<Integer> prev = new ArrayList<>();
        ArrayList<Integer> curr = new ArrayList<>();

        for(int j=0; j < b + 1; j++){
            prev.add(0);
            curr.add(0);
        }

        for(int i=1; i < a + 1; i++){
            for (int j=1; j < b + 1; j++){
                if(y.charAt(j-1) == x.charAt(i-1)){
                    curr.set(j, prev.get(j-1) + 1);
                } else {
                    curr.set(j, Math.max(prev.get(j), curr.get(j-1)));
                }
            }
            for(int j=0; j < b + 1; j++){
                prev.set(j, curr.get(j));
            }
        }
        System.out.println(prev.get(b).toString());
    }
}
