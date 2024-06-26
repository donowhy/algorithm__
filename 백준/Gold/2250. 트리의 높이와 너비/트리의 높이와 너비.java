import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
    //트리 정보 클래스
    static class node{
        int left, right, parent;	//왼쪽 자식, 오른쪽 자식, 부모 노드
        node(int left, int right){
            this.left = left;
            this.right = right;
            this.parent = -1;
        }
    }
    static node[] tree;		//트리 정보 저장하는 배열
    static int[] minWidth, maxWidth;		//각 깊이 최대, 최소 값 저장 배열
    static int maxDepth = -1, index = 1;	//최대 깊이
    static int answerD = -1, answerW = -1;	//최대 너비의 해당하는 레벨, 최대 너비
    public static void main(String[] args) throws IOException{
        //입력값 처리하는 BufferedWriter
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //결과값 출력하는 BufferedWriter
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        minWidth = new int[N+1];
        maxWidth = new int[N+1];
        tree = new node[N+1];
        //배열 초기화
        for(int i=0;i<=N;i++){
            minWidth[i] = Integer.MAX_VALUE;
            maxWidth[i] = Integer.MIN_VALUE;
            tree[i] = new node(-1, -1);
        }
        StringTokenizer st;
        //트리 정보 저장
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine()," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            tree[a].left = b;
            tree[a].right = c;
            if(tree[a].left != -1)
                tree[b].parent = a;

            if(tree[a].right != -1)
                tree[c].parent = a;
        }
        //중위 순회 진행하여 각 깊이 최대, 최소 값 구하기
        for(int i=1;i<=N;i++){
            //Root노드일 때 순회 시작
            if(tree[i].parent == -1){
                dfs(i, 1);
                break;
            }
        }
        //각 깊이 탐색하여 최대 너비 구하기
        for(int i=1;i<=maxDepth;i++){
            if(answerW < maxWidth[i] - minWidth[i] + 1){
                answerW = maxWidth[i] - minWidth[i] + 1;
                answerD = i;
            }
        }
        //최대 너비와 그에 해당하는 레벨 BufferedWriter 저장
        bw.write(answerD + " " + answerW);
        bw.flush();		//결과 출력
        bw.close();
        br.close();
    }
    //중위 탐색 진행하는 함수
    static void dfs(int cur, int d){
        node n = tree[cur];
        maxDepth = Math.max(maxDepth, d);
        //Left
        if(n.left != -1)
            dfs(n.left, d+1);
            
        //Root
        minWidth[d] = Math.min(minWidth[d], index);
        maxWidth[d] = Math.max(maxWidth[d], index++);

        //Right
        if(n.right != -1)
            dfs(n.right, d+1);
    }
}