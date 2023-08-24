N = int(input())
a = list(sorted(map(int,input().split())))
b = list(sorted(map(int,input().split()),reverse=True))
r = 0
for i in range(N):
    r += a[i] * b[i]

print(r)