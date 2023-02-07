N, M = map(int,input().split())
board = [input() for _ in range(N)]
ans = 64 #가장 많이 바꾼다고 해도 32이기 때문에 32 이상으로만 값 설정하면 됨

def fill(y, x, bw):
    global ans
    cnt = 0
    for i in range(8):
        for j in range(8):
            if (i + j) % 2:
                if board[y+i][x+j] == bw:
                    cnt += 1
            else:
                if board[y + i][x + j] != bw:
                    cnt += 1
    ans = min(ans, cnt)
    
for i in range(N-7):
    for j in range(M-7):
        fill(i, j, 'B')
        fill(i, j, 'W')  
        
print(ans)