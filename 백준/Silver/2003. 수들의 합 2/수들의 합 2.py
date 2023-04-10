import sys

n, m = map(int, sys.stdin.readline().split())

arr = list(map(int, sys.stdin.readline().split()))

# 구간 초기화
start, end = 0, 1

# m이 되는 경우의 수
count = 0

while (start <= end and end <= n):
    total = sum(arr[start:end])
    if (total < m):
        end += 1

    elif (total > m):
        start += 1

    else:
        count += 1
        end += 1

print(count)