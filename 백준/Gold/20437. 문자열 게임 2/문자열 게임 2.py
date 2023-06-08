from collections import defaultdict

tc = int(input())
for _ in range(tc):
    s = input()
    k = int(input())
    count = defaultdict(list)
    shortest = float('inf')
    longest = float('-inf')
    no_ans = True

    for i, char in enumerate(s):
        count[char].append(i)
        if len(count[char]) == k:
            length = count[char][-1] - count[char][0] + 1
            shortest = min(shortest, length)
            longest = max(longest, length)
            count[char].pop(0)
            no_ans = False

    if no_ans:
        print(-1)
    else:
        print(shortest, longest)
