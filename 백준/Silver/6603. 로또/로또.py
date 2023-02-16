from itertools import combinations

while True:

    array = list(map(int, input().split()))

    k = array[0]
    S = array[1:]

    for i in combinations(S, 6):
        print(*i)

    if k == 0:
        exit()
    print()