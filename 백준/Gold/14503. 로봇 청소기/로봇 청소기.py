#dr: 북 동 남 서
di = [-1, 0, 1, 0]
dj = [0, 1, 0, -1]
def solve(ci, cj, dr):
    cnt = 0  #청소한 장소 수
    while 1: # 청소기가 더 이상 움직이지 못 할때 종료
        #[1] 현재위치 청소
        arr[ci][cj] = 2 #1번 위치
        cnt += 1

        # [2] 왼쪽방향으로 순서대로 탐색해서 미청소 영역이 있으면 그 쪽으로 이동
        flag = 1
        while flag == 1:
            # 왼쪽부터 네 방향으로 탐색 후 미청소 영역이 이 ㅆ는 경우
            for nd in ((dr+3)%4, (dr+2)%4, (dr+1)%4, (dr)): #왼쪽부터 순서대로 네 방향
                ni, nj = ci + di[nd], cj + dj[nd]
                if arr[ni][nj] == 0: #미청소 영역
                    ci,cj,dr = ni,nj,nd
                    flag = 0
                    break

            else: #for - else문 , 네 방향 모두 미청소 영역 없음 --> 후진
                bi,bj = ci-di[dr], cj-dj[dr] #후진
                if arr[bi][bj] == 1:    #벽이라면 종료
                    return cnt
                else:
                    ci, cj = bi, bj

N, M = map(int,input().split())
si, sj, dr = map(int,input().split())
arr = [list(map(int,input().split())) for _ in range(N)]

ans = solve(si, sj, dr)
print(ans)