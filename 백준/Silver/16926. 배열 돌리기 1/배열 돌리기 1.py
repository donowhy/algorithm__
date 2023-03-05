N, M, R = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(N)]
move = [[1, 0], [0, 1], [-1, 0], [0, -1]]  # 하우상좌

def rotate():
    carr = [[] for _ in range(N)]
    for i in range(N):#배열 복사
        carr[i]=arr[i][:]

    sr = 0; sc = 0; er = N - 1; ec = M - 1
    for depth in range(min(M, N)//2):
        r = sr
        c = sc
        for d in move:
            while True:
                nr = r + d[0]
                nc = c + d[1]
                if sr <= nr <= er and sc <= nc <= ec:
                    arr[nr][nc] = carr[r][c]
                    r = nr
                    c = nc
                else:
                    break
        sr+=1; sc+=1; er-=1; ec-=1


for r in range(R):  # 전체 회전 횟수
    rotate()

for i in range(N):
    print(*arr[i])