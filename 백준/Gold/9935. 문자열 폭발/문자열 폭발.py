import sys

# 입력값 처리
S = sys.stdin.readline().rstrip()
explosion_string = sys.stdin.readline().rstrip()

# stack으로 문자열 폭발 구현
stack = []
ex_len = len(explosion_string)

for i in range(len(S)):
    stack.append(S[i])
    if ''.join(stack[-ex_len:]) == explosion_string:
        for _ in range(ex_len):
            stack.pop()

# 결과 출력
if stack:
    print(''.join(stack))
else:
    print('FRULA')