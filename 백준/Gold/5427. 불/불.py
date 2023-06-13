from collections import deque
import sys

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

def bfs():
    while fire: 
        x, y = fire.popleft()
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < h and 0 <= ny < w and building[nx][ny] != '#' and fire_time[nx][ny] == -1:
                fire_time[nx][ny] = fire_time[x][y] + 1
                fire.append((nx, ny))
    
    while person: 
        x, y = person.popleft()
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if nx < 0 or nx >= h or ny < 0 or ny >= w:  # reach the boundary
                return person_time[x][y] + 1
            if 0 <= nx < h and 0 <= ny < w and building[nx][ny] != '#' and person_time[nx][ny] == -1 and (fire_time[nx][ny] == -1 or fire_time[nx][ny] > person_time[x][y] + 1):
                person_time[nx][ny] = person_time[x][y] + 1
                person.append((nx, ny))
    
    return "IMPOSSIBLE"

t = int(sys.stdin.readline())
for _ in range(t):
    w, h = map(int, sys.stdin.readline().split())
    building = [list(sys.stdin.readline().strip()) for _ in range(h)]
    fire = deque()
    person = deque()
    
    fire_time = [[-1]*w for _ in range(h)]
    person_time = [[-1]*w for _ in range(h)]
    
    for i in range(h):
        for j in range(w):
            if building[i][j] == '*':
                fire.append((i, j))
                fire_time[i][j] = 0
            if building[i][j] == '@':
                person.append((i, j))
                person_time[i][j] = 0
    
    print(bfs())
