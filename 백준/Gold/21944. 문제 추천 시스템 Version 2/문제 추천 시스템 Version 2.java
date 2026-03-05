import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static Map<Integer, int[]> infoMap = new HashMap<>();
    static TreeSet<Problem> allProblems = new TreeSet<>();
    static Map<Integer, TreeSet<Problem>> groupProblems = new HashMap<>();

    static class Problem implements Comparable<Problem> {
        int p, l, g;
        public Problem(int p, int l, int g) {
            this.p = p; this.l = l; this.g = g;
        }

        @Override
        public int compareTo(Problem o) {
            if (this.l != o.l) return Integer.compare(this.l, o.l);
            return Integer.compare(this.p, o.p);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            addProblem(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String op = st.nextToken();

            if (op.equals("add")) {
                addProblem(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            } 
            
            if (op.equals("recommend")) {
                int g = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                System.out.println(x == 1 ? groupProblems.get(g).last().p : groupProblems.get(g).first().p);
            } 
            
            if (op.equals("recommend2")) {
                int x = Integer.parseInt(st.nextToken());
                System.out.println(x == 1 ? allProblems.last().p : allProblems.first().p);
            } 
            
            if (op.equals("recommend3")) {
                int x = Integer.parseInt(st.nextToken());
                int l = Integer.parseInt(st.nextToken());
                if (x == 1) {
                    Problem res = allProblems.ceiling(new Problem(0, l, 0));
                    System.out.println(res == null ? -1 : res.p);
                } else {
                    Problem res = allProblems.lower(new Problem(0, l, 0));
                    System.out.println(res == null ? -1 : res.p);
                }
            } 
            
            if (op.equals("solved")) {
                int p = Integer.parseInt(st.nextToken());
                int[] info = infoMap.get(p);
                Problem target = new Problem(p, info[0], info[1]);
                allProblems.remove(target);
                groupProblems.get(info[1]).remove(target);
                infoMap.remove(p);
            }
        }
    }

    static void addProblem(int p, int l, int g) {
        Problem prob = new Problem(p, l, g);
        allProblems.add(prob);
        groupProblems.computeIfAbsent(g, k -> new TreeSet<>()).add(prob);
        infoMap.put(p, new int[]{l, g});
    }
}