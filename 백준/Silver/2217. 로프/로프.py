import sys

N = int(sys.stdin.readline().rstrip())

lst = list(int(sys.stdin.readline().rstrip()) for _ in range(N))
lst.sort()

mx = - 1

for i in range(N):
    mx = max(mx, lst[i] * (N-i))

print(mx)
