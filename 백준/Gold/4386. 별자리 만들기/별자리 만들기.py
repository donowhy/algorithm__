import math
from operator import itemgetter

def find(parent, i):
    if parent[i] == i:
        return i
    return find(parent, parent[i])

def union(parent, rank, x, y):
    xroot = find(parent, x)
    yroot = find(parent, y)
    if rank[xroot] < rank[yroot]:
        parent[xroot] = yroot
    elif rank[xroot] > rank[yroot]:
        parent[yroot] = xroot
    else :
        parent[yroot] = xroot
        rank[xroot] += 1

def kruskal(graph):
    result =[]
    i, e = 0, 0
    graph.sort(key=itemgetter(2))
    parent = []
    rank = []
    for node in range(n):
        parent.append(node)
        rank.append(0)
    while e < n -1 :
        u,v,w =  graph[i]
        i = i + 1
        x = find(parent, u)
        y = find(parent ,v)
        if x != y:
            e = e + 1     
            result.append([u,v,w])
            union(parent, rank, x, y)         
    return sum(w for u,v,w in result)

n = int(input())
stars = []
edges = []
for _ in range(n):
    x, y = map(float,input().split())
    stars.append((x,y))

for i in range(n-1):
    for j in range(i+1, n):
        edges.append((i,j,math.sqrt((stars[i][0]-stars[j][0])**2 + (stars[i][1]-stars[j][1])**2)))

print("%.2f" % kruskal(edges))
