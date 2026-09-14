import java.util.*;

class Solution {
    public class Node {
        String word;
        int cost;
        
        public Node (String word, int cost) {
            this.word = word;
            this.cost = cost;
        }
    }
    public int solution(String begin, String target, String[] words) {
        // bfs begin, words
        // 모든 걸 확인하면서 words true false 진행
        // cost가 가장 작은걸로
        
        int answer = findWord(begin, target, words);
    
        return answer;
    }
    
    public int findWord(String begin, String target, String[] words) {
        boolean[] checked = new boolean[words.length];
        ArrayDeque<Node> wordList = new ArrayDeque<>();
        int result = Integer.MAX_VALUE;
            
        wordList.offer(new Node(begin, 0));
        
        while(!wordList.isEmpty()) {
            Node node = wordList.poll();
            
            if(target.equals(node.word)) {
                result = Math.min(node.cost, result);
            }
            
            for(int i=0; i<words.length; i++) {
                if(!checked[i] && checkWordDiffCount(words[i], node.word)){
                    checked[i] = true;
                    wordList.offer(new Node(words[i], node.cost + 1));
                }
            }
        }
        
        return result == Integer.MAX_VALUE ? 0 : result;
    }
    
    public boolean checkWordDiffCount(String word, String nodeWord){
        int diffCnt = 0;
        
        String[] splitWord = word.split("");
        String[] splitNodeWord = nodeWord.split("");
        
        for(int i=0; i<splitWord.length; i++) {
            if(!splitWord[i].equals(splitNodeWord[i])) {
                diffCnt += 1;
            }
        }
        
        return diffCnt > 1 ? false : true;
    }
    
}