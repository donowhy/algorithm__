s = [[0]*101 for i in range(101)] # 빈 격자판을 만든다.
n = int(input())
# 테스트 케이스 수만큼 색종이별로 테스트 케이스에 해당하는 숫자(i+1)로 채운다.
for i in range(n):
    x, y, w, h = map(int, input().split())
    for j in range(x, x+w):
        for k in range(y, y+h):
            s[j][k] = i+1
for i in range(n): # 테스트 케이스 수만큼 돌며 각 색종이 면적 출력.
    cnt = 0
    for m in s:
        cnt += m.count(i+1)
        # print(cnt)
    print(cnt)