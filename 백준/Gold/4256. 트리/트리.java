import java.io.*;
import java.util.*;

public class Main {
    static int[] preorder, inorder;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            preorder = new int[n];
            inorder = new int[n];
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                preorder[i] = Integer.parseInt(st.nextToken());
            }
            
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                inorder[i] = Integer.parseInt(st.nextToken());
            }
            
            postOrder(0, n, 0);
            sb.append('\n');
        }
        
        System.out.print(sb);
    }
    
    static void postOrder(int inStart, int inEnd, int preIndex) {
        if (inStart == inEnd) return;
        
        int rootIdx = inStart;
        while (inorder[rootIdx] != preorder[preIndex]) {
            rootIdx++;
        }
        
        postOrder(inStart, rootIdx, preIndex + 1);
        postOrder(rootIdx + 1, inEnd, preIndex + rootIdx - inStart + 1);
        
        sb.append(preorder[preIndex]).append(' ');
    }
}
