import sys
input = sys.stdin.readline

N = int(input())
heights = list(map(int, input().split()))

stack = []
res = [0] * N

for i in range(N):
    while stack and heights[stack[-1]] < heights[i]:
        stack.pop()
    if stack:
        res[i] = stack[-1] + 1
    stack.append(i)

print(*res)
