import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str = br.readLine().toCharArray();

        Stack<Character> leftStack = new Stack<>();
        Stack<Character> rightStack = new Stack<>();

        for (char c : str) {
            leftStack.push(c);
        }

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String command = br.readLine();
            switch (command.charAt(0)) {
                case 'P':
                    leftStack.push(command.charAt(2));
                    break;
                case 'L':
                    if (!leftStack.isEmpty()) {
                        rightStack.push(leftStack.pop());
                    }
                    break;
                case 'D':
                    if (!rightStack.isEmpty()) {
                        leftStack.push(rightStack.pop());
                    }
                    break;
                case 'B':
                    if (!leftStack.isEmpty()) {
                        leftStack.pop();
                    }
                    break;
            }
        }

        StringBuilder result = new StringBuilder();
        while (!leftStack.isEmpty()) {
            rightStack.push(leftStack.pop());
        }
        while (!rightStack.isEmpty()) {
            result.append(rightStack.pop());
        }
        System.out.println(result);
    }
}
