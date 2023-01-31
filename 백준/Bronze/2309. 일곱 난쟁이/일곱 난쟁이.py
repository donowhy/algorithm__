from itertools import combinations

height = [int(input()) for _ in range(9)]

for combi in combinations(height, 7):
    if sum(combi) == 100:
        combi_srt = sorted(combi)
        for prt in combi_srt:
            print(prt)
        break