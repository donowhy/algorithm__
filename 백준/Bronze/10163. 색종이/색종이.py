import sys
input = sys.stdin.readline

s = [[0]*1001 for i in range(1001)]
n = int(input())

for i in range(n):
    x, y, w, h = map(int, input().split())
    for j in range(x, x+w):
        for k in range(y, y+h):
            s[j][k] = i+1
for i in range(n): 
    cnt = 0
    for m in s:
        cnt += m.count(i+1)
        # print(cnt)
    print(cnt)