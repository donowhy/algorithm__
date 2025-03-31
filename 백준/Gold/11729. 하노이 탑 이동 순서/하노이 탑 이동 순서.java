import java.io.*;

public class Main {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        bw.write((int) (Math.pow(2, N) - 1) + "\n");

        Hanoi(N, 1, 2, 3);

        bw.flush();
        bw.close();
    }

    /*
        N : 원판의 개수 
        start : 출발지 
        mid : 옮기기 위한 장소 
        to : 목적지
     */
    public static void Hanoi(int N, int start, int mid, int to) throws IOException {
        if (N == 1) {
            bw.write(start + " " + to + "\n");
            return;
        }

        Hanoi(N - 1, start, to, mid);  // Step 1
        bw.write(start + " " + to + "\n");  // Step 2
        Hanoi(N - 1, mid, start, to);  // Step 3
    }
}
