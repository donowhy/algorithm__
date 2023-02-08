import sys

input = sys.stdin.readline

N, M = map(int,input().split())

lst = []
cnt = 0

for _ in range(N):
    lsten = input().rstrip()
    lst.append(lsten)

for _ in range(M):
    see = input().rstrip()
    lst.append(see)

lst.sort()

for i in range(len(lst)-1):
    if lst[i] == lst[i+1]:
        cnt += 1
print(cnt)

for i in range(len(lst)-1):
    if lst[i] == lst[i+1]:
        print(lst[i])