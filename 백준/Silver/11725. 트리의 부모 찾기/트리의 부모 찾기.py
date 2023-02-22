#DFS

import sys
sys.setrecursionlimit(10**6)  #재귀 제한 때문에 늘려줌
n = int(sys.stdin.readline()) 

graph = [[] for i in range(n+1)]  #일차원 리스트에 연결정보만 저장

for i in range(n-1):
    a, b = map(int, sys.stdin.readline().split()) 
    graph[a].append(b)
    graph[b].append(a)

visited = [0]*(n+1)  # 무한 재귀는 막아야지?
visited[1] = 1  #루트 노드를 1로 만들고 시작하기 굳이 안해도 상관 없긴함

def dfs(s):
    for i in graph[s]:  #graph[s]에 연결돼 있는 것들 
        # print(i, 'i')
        if visited[i] == 0:  #처리 안한 곳이면
            visited[i] = s   # 부모 노드를 저장
            # print(visited)
            dfs(i)

dfs(1)

for x in range(2, n+1):
    print(visited[x])