import sys
N = int(sys.stdin.readline())
arr = list(map(int,sys.stdin.readline().split()))
arr2 = []

arr2 = list(sorted(set(arr)))

dic = {arr2[i]:i for i in range (len(arr2))}

for i in arr:
  print(dic[i],end=' ')