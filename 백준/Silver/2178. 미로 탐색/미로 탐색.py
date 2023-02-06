from collections import deque

dy = (0, 1, 0, -1)
dx = (1, 0, -1, 0)

N, M = map(int, input().split())
board = [input() for _ in range(N)]


def is_valid_coord(y, x):
    return 0 <= y < N and 0 <= x < M


def bfs():
    pass
    chk = [[False] * M for _ in range(N)]
    chk[0][0] = True
    dq = deque()
    dq.append((0, 0, 1))  # 마지막 1은 몇 칸을 지나왔는지, 시작점도 포함하니까 1로 시작
    while dq:
        y, x, d = dq.popleft()
        if y == N - 1 and x == M - 1:
            return d

        nd = d + 1
        for k in range(4):
            ny = y + dy[k]
            nx = x + dx[k]
            if is_valid_coord(ny, nx) and board[ny][nx] == '1' and not chk[ny][nx]:  # 문자열로 저장했기 때문에 문자열 1
                chk[ny][nx] = True
                dq.append((ny, nx, nd))


print(bfs())
