import sys

n = int(sys.stdin.readline())
tower = list(map(int, sys.stdin.readline().split()))
stack = []
goto = [0] * n

for i in range(n):
    t = tower[i]
    while stack and tower[stack[-1]] < t:
        stack.pop()
        # print(stack,'stack pop')
    if stack:
        goto[i] = stack[-1] + 1
        # print(goto, 'goto')
    stack.append(i)
    # print(stack, 'stack append')

print(*goto)