N = int(input())
M = []
K = list(map(int,input().split()))

for i in range(N):
    M.insert(i- K[i], i + 1)

for j in M:
    print(j, end=' ')
