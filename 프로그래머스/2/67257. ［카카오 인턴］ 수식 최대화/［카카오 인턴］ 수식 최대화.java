import java.util.*;

class Solution {

    private long max;
    private List<Long> expressionNums;
    private List<Character> expressionOps;
    private char[] ops;
    private char[] priorities;

    private boolean[] visited;

    public long solution(String expression) {
        init(expression);
        dfs(0);
        return max;
    }

    private void init(String expression) {
        max = 0;
        expressionNums = new ArrayList<>();
        expressionOps = new ArrayList<>();
        ops = new char[]{'+', '-', '*'};
        priorities = new char[3];
        visited = new boolean[3];
        int idx = 0;
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*') {
                expressionOps.add(ch);
                expressionNums.add(Long.parseLong(expression.substring(idx, i)));
                idx = i + 1;
            }
        }
        expressionNums.add(Long.parseLong(expression.substring(idx)));
    }

    private void dfs(int depth) {
        if (depth == 3) {
            max = Math.max(Math.abs(calcExpression()), max);
            return;
        }

        for (int i = 0; i < ops.length; i++) {
            if (visited[i]) {
                continue;
            }

            priorities[depth] = ops[i];
            visited[i] = true;
            dfs(depth + 1);
            visited[i] = false;
        }
    }

    private long calcExpression() {
        List<Long> tmpExpressionNums = new ArrayList<>(expressionNums);
        List<Character> tmpExpressionOps = new ArrayList<>(expressionOps);
        while (tmpExpressionOps.size() > 0) {
            int maxPriority = 0;
            for (int i = 0; i < tmpExpressionOps.size(); i++) {
                maxPriority = Math.max(getPriority(tmpExpressionOps.get(i)), maxPriority);
            }
            for (int i = 0; i < tmpExpressionOps.size(); i++) {
                if (getPriority(tmpExpressionOps.get(i)) == maxPriority) {
                    tmpExpressionNums.add(i,
                        calc(tmpExpressionNums.remove(i), tmpExpressionNums.remove(i),
                            tmpExpressionOps.remove(i)));
                    break;
                }
            }
        }
        return tmpExpressionNums.get(0);
    }

    private long calc(long num1, long num2, char op) {
        long ret;
        if (op == '+') {
            ret = num1 + num2;
        } else if (op == '-') {
            ret = num1 - num2;
        } else {
            ret = num1 * num2;
        }
        return ret;
    }

    private int getPriority(char op) {
        int priority;
        if (op == priorities[0]) {
            priority = 2;
        } else if (op == priorities[1]) {
            priority = 1;
        } else {
            priority = 0;
        }
        return priority;
    }
}
