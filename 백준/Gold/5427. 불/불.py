from collections import deque
import sys
input = sys.stdin.readline

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

def bfs():
    while fire:
        x, y = fire.popleft()
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < h and 0 <= ny < w and building[nx][ny] != '#' and fire_spread[nx][ny] == -1:
                fire_spread[nx][ny] = fire_spread[x][y] + 1
                fire.append((nx, ny))

    while move:
        x, y = move.popleft()
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if nx < 0 or nx >= h or ny < 0 or ny >= w:
                return move_time[x][y] + 1
            if 0 <= nx < h and 0 <= ny < w:
                if building[nx][ny] != '#' and move_time[nx][ny] == -1 and (fire_spread[nx][ny] == -1 or fire_spread[nx][ny] > move_time[x][y] + 1):
                    move_time[nx][ny] = move_time[x][y] + 1
                    move.append((nx, ny))
    return "IMPOSSIBLE"

T = int(input())
for _ in range(T):
    w, h = map(int, input().split())
    building = [list(input().strip()) for _ in range(h)]
    fire_spread = [[-1]*w for _ in range(h)]
    move_time = [[-1]*w for _ in range(h)]
    fire = deque()
    move = deque()

    for i in range(h):
        for j in range(w):
            if building[i][j] == '*':
                fire.append((i, j))
                fire_spread[i][j] = 0
            elif building[i][j] == '@':
                move.append((i, j))
                move_time[i][j] = 0

    print(bfs())
