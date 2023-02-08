import sys
input = sys.stdin.readline

N = int(input())
lst = list(map(int,input().split()))

lst.sort()

sum = 0
tot = 0
for i in range(len(lst)):
    sum = sum + lst[i]
    tot += sum
print(tot)