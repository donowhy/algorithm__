import sys
import heapq as hq
input = sys.stdin.readline
tc = int(input().rstrip())

for i in range(tc):
    N = int(input().rstrip())
    num_lst = []
    for num in range((N-1)//10+1):
        tmp = list(map(int,input().rstrip().split()))
        num_lst.extend(tmp)
    left = []
    right = []
    ans = []
    for j in range(len(num_lst)):
        if len(left) <= len(right):
            hq.heappush(left,(-num_lst[j],num_lst[j]))
        else:
            hq.heappush(right, (num_lst[j], num_lst[j]))

        if right and left[0][1] > right[0][1]:
            mn = hq.heappop(right)[1]
            mx = hq.heappop(left)[1]
            hq.heappush(left,(-mn,mn))
            hq.heappush(right,(mx,mx))
        if j % 2 == 0:
            ans.append(left[0][1])

    print(N//2+1)
    for i in range(len(ans)):
        print(ans[i], end = ' ')
        if i % 10 == 9 or i == len(ans) -1:
            print()