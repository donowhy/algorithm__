import java.util.*;

class Star {
    double x, y;

    Star(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

class Edge implements Comparable<Edge> {
    int u, v;
    double w;

    Edge(int u, int v, double w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    @Override
    public int compareTo(Edge o) {
        return Double.compare(this.w, o.w);
    }
}

class UnionFind {
    private int[] parent;
    private int[] rank;

    UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        if (rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot;
        } else if (rank[xRoot] > rank[yRoot]) {
            parent[yRoot] = xRoot;
        } else {
            parent[yRoot] = xRoot;
            rank[xRoot]++;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Star> stars = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            stars.add(new Star(scanner.nextDouble(), scanner.nextDouble()));
        }

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                double dist = Math.sqrt(Math.pow(stars.get(i).x - stars.get(j).x, 2) +
                        Math.pow(stars.get(i).y - stars.get(j).y, 2));
                edges.add(new Edge(i, j, dist));
            }
        }
        Collections.sort(edges);
        UnionFind uf = new UnionFind(n);
        double res = 0.0;
        for (Edge e : edges) {
            if (uf.find(e.u) != uf.find(e.v)) {
                uf.union(e.u, e.v);
                res += e.w;
            }
        }
        System.out.printf("%.2f\n", res);
    }
}
