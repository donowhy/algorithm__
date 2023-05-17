import sys
N = int(input())
lst = list(map(int,input().split()))

start = 0
end = N - 1
hap = 0
mn = sys.maxsize
arr = []

while start < end:
    if end >= 0 and start <= N-1:
        hap = lst[start] + lst[end]
    else:
        break

    if mn >= abs(hap):
        mn = abs(hap)
        arr.append([lst[start], lst[end]])

    if hap > 0:
        end -= 1

    else:
        start += 1


print(*arr[-1])