import sys

readline = sys.stdin.readline

N = int(input())
la = sorted(list(map(int,input().rstrip().split())))

M = int(input())
lb = list(map(int,input().rstrip().split()))


for i in lb:
    start = 0
    end = N - 1
    flag = 0
    while start < end:
        mid = (start + end) // 2

        if i < la[start] or i > la[end]:
            break

        if la[mid] == i or la[start] == i or la[end] == i:
            flag = 1
            print(1)
            break
        elif la[mid] > i:
            end = mid
        else:
            start = mid + 1


    if flag == 0:
        print(0)
