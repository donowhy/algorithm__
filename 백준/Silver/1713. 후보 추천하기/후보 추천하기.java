import java.io.*;
import java.util.*;

public class Main {
    static class Candidate implements Comparable<Candidate> {
        int id;
        int recommend;
        int time;

        public Candidate(int id, int recommend, int time) {
            this.id = id;
            this.recommend = recommend;
            this.time = time;
        }

        @Override
        public int compareTo(Candidate other) {
            if (this.recommend == other.recommend) {
                return this.time - other.time;
            }
            return this.recommend - other.recommend;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int totalRecommends = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        PriorityQueue<Candidate> pq = new PriorityQueue<>();
        Map<Integer, Candidate> map = new HashMap<>();

        for (int i = 0; i < totalRecommends; i++) {
            int id = Integer.parseInt(st.nextToken());
            if (map.containsKey(id)) {
                Candidate cand = map.get(id);
                pq.remove(cand);
                cand.recommend++;
                pq.offer(cand);
            } else {
                if (pq.size() == N) {
                    Candidate removed = pq.poll();
                    map.remove(removed.id);
                }
                Candidate newCand = new Candidate(id, 1, i);
                pq.offer(newCand);
                map.put(id, newCand);
            }
        }

        List<Integer> result = new ArrayList<>(map.keySet());
        Collections.sort(result);
        result.forEach(id -> System.out.print(id + " "));
    }
}
