import sys

n = int(sys.stdin.readline().rstrip())
people = list(map(int,sys.stdin.readline().rstrip().split()))
b, c = map(int,sys.stdin.readline().rstrip().split())

cnt = n

for i in people:
    i -= b
    if i > 0:
        cnt += i // c
        if i % c:
            cnt += 1

print(cnt)