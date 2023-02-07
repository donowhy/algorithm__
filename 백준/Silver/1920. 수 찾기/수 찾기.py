import sys

input = sys.stdin.readline

N = int(input())
lst = set(map(int, input().split()))

M = int(input())
arr = list(map(int, input().split()))

for i in arr:
    if i in lst:
        print(1)
    else:
        print(0)