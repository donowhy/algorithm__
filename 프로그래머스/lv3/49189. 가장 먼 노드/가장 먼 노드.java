import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;

        boolean[] visited = new boolean[n + 1];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // 그래프 생성
        for (int i = 0; i < edge.length; i++) {
            int node1 = edge[i][0];
            int node2 = edge[i][1];
            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(1, 0));
        visited[1] = true;

        int maxDepth = 0;
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            int currentDepth = currentNode.depth;

            if (currentDepth > maxDepth) {
                maxDepth = currentDepth;
                answer = 1;
            } else if (currentDepth == maxDepth) {
                answer++;
            }

            for (int adjacent : graph.get(currentNode.node)) {
                if (!visited[adjacent]) {
                    visited[adjacent] = true;
                    queue.add(new Node(adjacent, currentDepth + 1));
                }
            }
        }
        
        return answer;
    }
    
    static class Node {
        int node;
        int depth;

        Node(int node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
}
