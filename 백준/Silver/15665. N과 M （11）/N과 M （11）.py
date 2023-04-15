n, m = map(int, input().split())
nums = sorted(set(map(int, input().split())))

result = []

def dfs(combi):
    if len(combi) == m:
        result.append(tuple(combi))
        return
    for num in nums:
        dfs(combi + [num])

dfs([])
result = sorted(set(result))

for combi in result:
    print(*combi)
