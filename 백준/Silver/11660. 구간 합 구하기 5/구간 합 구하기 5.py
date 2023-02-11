import sys
input = sys.stdin.readline

N, M = map(int, input().split())

A = [[0]* (N+1)]
D = [[0] * (N + 1) for _ in range(N+1)]

for i in range(N):
    A_row = [0] + [int(x) for x in input().split()]
    A.append(A_row)

for i in range(1, N+1):
    for j in range(1, N+1):
        D[i][j] = D[i][j-1] + D[i-1][j] - D[i-1][j-1] + A[i][j]

for _ in range(M):
    x1,y1, x2,y2 = map(int, input().split())

    result = D[x2][y2] - D[x1-1][y2] - D[x2][y1-1] + D[x1-1][y1-1]
    print(result)