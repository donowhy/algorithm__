r, c = map(int, input().split())
#자르는 위치 저장
row = [0, r]    # [0, 10]
column = [0, c] #  [0, 8]

for _ in range(int(input())):   # 자르는 횟수
    rr, linenumber = map(int, input().split())
    if rr == 1:             # 세로가 1 가로가 0-> 세로는 r에 가로는 c
        row.append(linenumber)
    else :
        column.append(linenumber)

row.sort()     # [0, 4, 10]
column.sort()  # [0, 2, 3, 8]
               # 빼서 최대 길이 구하기

subtracted_r = []  #[4, 6]
subtracted_c = []  #[2, 1, 5]

for i in range(len(row)-1):    # 0 1
    subtracted_r.append(row[i + 1] - row[i])   # row[1]-row[0]   row[2]-row[1]
for i in range(len(column) -1): # 0 1 2
    subtracted_c.append(column[i+1]- column[i]) #col[1]-col[0]  col[2]-col[1]  col[3]-col[2]

print(max(subtracted_r) * max(subtracted_c))