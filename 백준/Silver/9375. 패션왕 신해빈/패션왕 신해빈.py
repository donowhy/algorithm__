import sys
from collections import Counter

input = sys.stdin.readline

T = int(input())
for _ in range(T):
    clothes = []

    n = int(input())
    for _ in range(n):
        k = input().split()[1]
        clothes.append(k)
    clothes = Counter(clothes)

    count = 1
    for key in clothes.keys():
        count *= clothes[key] + 1
    print(count - 1)