import sys

input = sys.stdin.readline

n = int(input())
li = list(map(int, input().split(' ')))
p_sum = [0]*n
p_sum[0] = li[0]
for i in range(1, n):
    p_sum[i] = p_sum[i-1]+li[i]


def getpsum(a, b):
    return p_sum[b]-p_sum[a-1] if a > 0 else p_sum[b]


ans = 0
for i in range(n-1, 1, -1):
    start = 1
    end = i-1
    maxidx = 0
    while start <= end:
        mid = (start+end)//2
        head = getpsum(0, mid-1)
        center = getpsum(mid, i-1)
        bae = getpsum(i, n - 1)
        if head < bae < center:
            maxidx = max(maxidx, mid)
            start = mid+1
        else:
            end = mid-1
    ans += maxidx
print(ans)