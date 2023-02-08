import sys
input = sys.stdin.readline

N, M = map(int, input().split())
dic = {}

for _ in range(N):
    a, b = input().split()
    dic[a] = b

for _ in range(M):
    a = input().strip()
    print(dic[a])