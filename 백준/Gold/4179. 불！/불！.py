#불!
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
            if 0 <= nx < w and 0 <= ny < h and building[nx][ny] != '#' and fire_spread[nx][ny] == -1:
                fire_spread[nx][ny] = fire_spread[x][y] + 1
                fire.append((nx, ny))

    while move:
        x, y = move.popleft()
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if nx < 0 or nx >= w or ny < 0 or ny >= h:
                return move_time[x][y] + 1
            if 0 <= nx < w and 0 <= ny < h:
                if building[nx][ny] != '#' and move_time[nx][ny] == -1 and (fire_spread[nx][ny] == -1 or fire_spread[nx][ny] > move_time[x][y] + 1):
                    move_time[nx][ny] = move_time[x][y] + 1
                    move.append((nx, ny))
    return "IMPOSSIBLE"


w, h = map(int, input().split())
building = [list(input().strip()) for _ in range(w)]
fire_spread = [[-1]*h for _ in range(w)]
move_time = [[-1]*h for _ in range(w)]
fire = deque()
move = deque()

for i in range(w):
    for j in range(h):
        if building[i][j] == 'F':
            fire.append((i, j))
            fire_spread[i][j] = 0
        elif building[i][j] == 'J':
            move.append((i, j))
            move_time[i][j] = 0

print(bfs())