N, M = map(int, input().split())
A = list(map(int, input().split()))

start = 0
end = 0

for i in A:
    if start < i:
        start = i
    end += i

while start <= end:
    middle = int((start + end) / 2)
    hap = 0
    cnt = 0
    for i in range(N):
        if hap + A[i] > middle:
            cnt += 1
            hap = 0
        hap += A[i]
    if hap != 0:
        cnt += 1
    if cnt > M:
        start = middle + 1
    else:
        end = middle - 1
print(start)