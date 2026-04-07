import java.util.*;

class Solution {
    public int[][] answer;
    public int idx;
    public class Node implements Comparable<Node>{
        int id, x, y;
        Node left, right;
        public Node (int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
        
        @Override
        public int compareTo(Node o) {
            if(this.y != o.y) {
                return Integer.compare(o.y, this.y);
            }
            return Integer.compare(this.x, o.x);
        }
    }
    
    ArrayList<Node> arr = new ArrayList<>();
    public int[][] solution(int[][] nodeinfo) {
        for(int i=0; i<nodeinfo.length; i++) {
            arr.add(new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]));
        }
        answer = new int[2][nodeinfo.length];
        
        Collections.sort(arr);
        
        Node root = arr.get(0);
        
        for(int i=1; i<arr.size(); i++) {
            insertNode(root, arr.get(i));
        }
        idx = 0;
        preOrder(root);
        
        idx = 0;
        postOrder(root);
        
        return answer;
    }
    
    private void insertNode(Node parent, Node child) {
        if(parent.x > child.x) {
            if(parent.left == null) parent.left = child;
            else insertNode(parent.left, child);
        } else {
            if(parent.right == null) parent.right = child;
            else insertNode(parent.right, child);
        }
    }
    
    private void preOrder(Node node) {
        if(node == null) return;
        answer[0][idx++] = node.id;
        preOrder(node.left);
        preOrder(node.right);
    }
    
    private void postOrder(Node node) {
        if(node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        answer[1][idx++] = node.id;
    }
}