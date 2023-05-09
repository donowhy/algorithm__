import sys

T = int(sys.stdin.readline())
for _ in range(T):
    n = int(sys.stdin.readline())
    coord = [tuple(map(int, sys.stdin.readline().split())) for _ in range(n+2)]
    dist = [[0 if i==j else float('inf') for j in range(n+2)] for i in range(n+2)]

    for i in range(n+2):
        for j in range(i+1, n+2):
            if abs(coord[i][0]-coord[j][0]) + abs(coord[i][1]-coord[j][1]) <= 1000:
                dist[i][j] = 1
                dist[j][i] = 1

    for k in range(n+2):
        for i in range(n+2):
            for j in range(n+2):
                if dist[i][j] > dist[i][k] + dist[k][j]:
                    dist[i][j] = dist[i][k] + dist[k][j]

    print("happy" if dist[0][-1] != float('inf') else "sad")
