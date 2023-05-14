import sys

n = int(sys.stdin.readline())
meeting = []
for _ in range(n):
    start, end = map(int, sys.stdin.readline().split())
    meeting.append((start, end))

meeting.sort(key=lambda x: (x[1], x[0]))

last = 0
cnt = 0
for start, end in meeting:
    if start >= last:
        last = end
        cnt += 1
print(cnt)