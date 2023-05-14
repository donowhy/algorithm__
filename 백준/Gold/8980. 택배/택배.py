N, C = map(int, input().split())
M = int(input())
box_info = sorted([list(map(int, input().split())) for _ in range(M)], key=lambda x: (x[1], x[0]))
box = [0]*(N+1)
res = 0

for i in range(M):
    temp = C - max(box[box_info[i][0]:box_info[i][1]])
    temp_box = min(temp, box_info[i][2])
    res += temp_box
    for j in range(box_info[i][0], box_info[i][1]):
        box[j] += temp_box

print(res)