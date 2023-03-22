from collections import deque

n, m, time = map(int, input().split())

brd = [list(map(int, input().split())) for _ in range(n)]
chk = [[[0, 0] for _ in range(m)] for _ in range(n)]

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

q = deque()
q.append((0, 0, 0))
cnt = 0
result = "Fail"
while q:
    cnt += 1
    if time < cnt:
        break
    for _ in range(len(q)):
        x, y, gram = q.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if nx < 0 or nx >= n or ny < 0 or ny >= m:
                continue

            if nx == n - 1 and ny == m - 1:
                q.clear()
                result = cnt
                break

            if brd[nx][ny] == 0 and not chk[nx][ny][gram]:
                q.append((nx, ny, gram))
                chk[nx][ny][gram] = 1

            if brd[nx][ny] == 2 and not chk[nx][ny][gram]:
                q.append((nx, ny, 1))
                chk[nx][ny][gram] = 1

            if brd[nx][ny] == 1 and gram and not chk[nx][ny][gram]:
                q.append((nx, ny, gram))
                chk[nx][ny][gram] = 1
        if result == cnt:
            break

print(result)

'''
1 4 10
0 2 1 0
'''
