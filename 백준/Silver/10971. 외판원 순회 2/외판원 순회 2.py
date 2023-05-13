# import sys
#
# def dfs(city, visited, cost):
#     global answer
#     if visited == ((1<<n) - 1):
#         if W[city][0] != 0:
#             answer = min(answer, cost + W[city][0])
#         return
#     for next_city in range(n):
#         if W[city][next_city] != 0 and visited & (1<<next_city) == 0:
#             dfs(next_city, visited | (1<<next_city), cost + W[city][next_city])
#
# n = int(sys.stdin.readline())
# W = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
# answer = float('inf')
# dfs(0, 1, 0)
# print(answer)
#
#
# import sys
# from itertools import permutations
#
# n = int(sys.stdin.readline())
# w = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
# p = permutations(range(n))
# answer = sys.maxsize
#
# for i in p:
#     start = i[0]
#     end = i[-1]
#     temp = 0
#     flag = False
#     if w[end][start] == 0:
#         continue
#     for j in range(n-1):
#         if w[i[j]][i[j+1]] == 0:
#             flag = True
#             break
#         else:
#             temp += w[i[j]][i[j+1]]
#     if flag:
#         continue
#     temp += w[end][start]
#     answer = min(answer, temp)
#
# print(answer)


n = int(input())

graph = [list(map(int,input().split())) for _ in range(n)]
answer = 10 ** 8

def dfs(city, visited, cost):
    global answer
    if all(visited):
        if graph[city][0] != 0:
            answer = min(answer, cost+graph[city][0])
        return

    for nc in range(n):
        if graph[city][nc] != 0 and not visited[nc]:
            nv = visited.copy()
            nv[nc] = True
            dfs(nc, nv, cost + graph[city][nc])

dfs(0,[True] + [False] * (n-1), 0)

print(answer)