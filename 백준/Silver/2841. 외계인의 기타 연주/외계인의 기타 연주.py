import sys

input = sys.stdin.readline

N, M = map(int, input().split())

stk = [[] for _ in range(7)]
ans = 0

for i in range(N):
    a, b = map(int, input().split())
    while stk[a] and stk[a][-1] > b:
        stk[a].pop()
        ans += 1

    if stk[a] and stk[a][-1] == b:
        continue

    stk[a].append(b)
    ans += 1

print(ans)