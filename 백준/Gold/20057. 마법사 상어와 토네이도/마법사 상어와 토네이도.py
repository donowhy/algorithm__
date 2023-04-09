N = int(input())

s_x, s_y = N // 2, N // 2

graph = [list(map(int, input().split())) for _ in range(N)]

direct = [
    [[0, 0, 2, 0, 0], [0, 10, 7, 1, 0], [5, -1, 0, 0, 0, ], [0, 10, 7, 1, 0], [0, 0, 2, 0, 0]],
    [[0, 0, 0, 0, 0], [0, 1, 0, 1, 0], [2, 7, 0, 7, 2], [0, 10, -1, 10, 0], [0, 0, 5, 0, 0]],
    [[0, 0, 2, 0, 0], [0, 1, 7, 10, 0], [0, 0, 0, -1, 5], [0, 1, 7, 10, 0], [0, 0, 2, 0, 0]],
    [[0, 0, 5, 0, 0], [0, 10, -1, 10, 0], [2, 7, 0, 7, 2], [0, 1, 0, 1, 0], [0, 0, 0, 0, 0]],
]
# 0 오른쪽 1 아랫쪽 2왼쪽 3윗쪽
# 알파는 -1로 표현.

dx = [0, 1, 0, -1]
dy = [-1, 0, 1, 0]

snail_move = []

for i in range(1, N):
    for _ in range(2):
        snail_move.append(i)

snail_move.append(N - 1)

d = 0
sand_out = 0

nx = s_x
ny = s_y

# print(snail_move)
for move in snail_move:
    now = 0
    while now < move:
        ax = 0
        ay = 0
        nx += dx[d]
        ny += dy[d]
        sand = graph[nx][ny]
        temp = sand
        blow = direct[d]
        # print(d)
        for x in range(-2, 3, 1):
            for y in range(-2, 3, 1):
                if blow[x + 2][y + 2] == -1:  # 알파가 있는 지역은 가장 마지막에 계산해서 넘김
                    ax = nx + x
                    ay = ny + y
                    continue
                # 격자를 벗어나는 경우
                if nx + x < 0 or nx + x >= N or ny + y < 0 or ny + y >= N:
                    # print('out')
                    sand_out += (sand * blow[x + 2][y + 2]) // 100
                    temp -= (sand * blow[x + 2][y + 2]) // 100
                else:
                    # 격자 내에 있는 경우
                    graph[nx + x][ny + y] += (sand * blow[x + 2][y + 2]) // 100
                    temp -= (sand * blow[x + 2][y + 2]) // 100
        # 알파 위치의 격자를 확인해준다.
        # 모두 흩뿌리고 남은 것이 알파로 이동.
        if 0 <= ax < N and 0 <= ay < N:
            graph[ax][ay] += temp
        else:
            sand_out += temp

        now += 1
        # print(sand_out)

    d = (d + 1) % 4
    # print(graph)
print(sand_out)