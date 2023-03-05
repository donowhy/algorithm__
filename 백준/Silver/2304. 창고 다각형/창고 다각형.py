t = int(input())

brd = [[0] * 1001 for _ in range(1001)]
height = []
for tc in range(t):
    l, h = map(int,input().split())
    height.append([l,h])

height.sort()
temp = []
for i in range(len(height)):
    temp.append(height[i][1])

col = height[-1][0] - height[0][0]
row = max(temp)

width = (col+1) * row

arr = [[height[0][0],height[0][1]]]
for i in range(temp.index(max(temp))):
    if arr[0][1] < height[i+1][1]: # 4, 6
        x, y = arr.pop()
        width = width - (height[i + 1][0] - x) * (max(temp) - y)
        arr.append([height[i+1][0],height[i+1][1]])

    else:
        continue

arr.clear()
arr.append([height[-1][0],height[-1][1]])

for i in range(len(height)-1, temp.index(max(temp)), -1):
    if arr[0][1] < height[i-1][1]: # 8, 6
        x, y = arr.pop()
        width = width - abs((height[i - 1][0] - (x)) * (max(temp) - y))
        arr.append([height[i-1][0],height[i-1][1]])

    else:
        continue

print(width)