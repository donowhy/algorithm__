import sys
input = sys.stdin.readline

N = int(input())
arr = []
for i in range(N):
    num = int(input())
    arr.append(num)

arr.sort()

for j in range(len(arr)):
    print(arr[j])
