import heapq as hq

n = int(input())
arr = []

for _ in range(n):
    nums = list(map(int, input().split()))
    for num in nums:
        hq.heappush(arr, num)
        if len(arr) > n:
            hq.heappop(arr)

print(arr[0])
