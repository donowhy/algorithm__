from itertools import combinations
from collections import deque

n = int(input())
populations = [0] + list(map(int, input().split()))
graph = [[] for _ in range(n+1)]
for i in range(1, n+1):
    tmp = list(map(int, input().split()))
    for j in tmp[1:]:
        graph[i].append(j)

result = float('inf')

def bfs(group):
    queue = deque([group[0]])
    visited = [0] * (n+1)
    visited[group[0]] = 1
    count, sum_people = 1, populations[group[0]]
    while queue:
        a = queue.popleft()
        for i in graph[a]:
            if i in group and visited[i] == 0:
                queue.append(i)
                visited[i] = 1
                count += 1
                sum_people += populations[i]
    if count == len(group):
        return sum_people
    return False

for i in range(1, n//2 + 1):
    comb = list(combinations(range(1, n+1), i))
    for group in comb:
        group1 = list(group)
        group2 = [i for i in range(1, n+1) if i not in group1]
        pop1 = bfs(group1)
        pop2 = bfs(group2)
        if pop1 and pop2:
            result = min(result, abs(pop1 - pop2))

if result == float('inf'):
    print(-1)
else:
    print(result)
