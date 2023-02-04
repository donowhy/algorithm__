import sys
input = sys.stdin.readline

N = int(input())
matrix = [[0]*1001 for _ in range(1001)]
for k in range(1,N+1):
    x,y,w,h = map(int, input().split())
    for i in range(x,x+w):
        for j in range(y,y+h):
            matrix[i][j] = k
cnt_color = [0] * (N+1)
for i in range(1001):
    for j in range(1001):
        if matrix[i][j]:
            cnt_color[matrix[i][j]] += 1

for i in range(1,N+1):
    print(cnt_color[i])