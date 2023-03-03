h, w = map(int,input().split())

lst = [list(input()) for _ in range(h)]
arr =[[-1] * w for _ in range(h)]
res =[]
for i in range(h):
    for j in range(w):
        if lst[i][j] == 'c':
            res.append([i,j])
            arr[i][j] = 0

while res:
    x, y = res.pop(0)
    arr[x][y] = 0
    while y < w - 1:
        arr[x][y+1] = arr[x][y] + 1
        y += 1


for i in range(len(arr)):
    print(*arr[i])