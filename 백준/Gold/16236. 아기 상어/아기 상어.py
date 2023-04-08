import copy
 
def findTopLeft(queue, n):
  tmp = []
  for x, y in queue:
    tmp.append(x*n + y)
  tmp.sort()
  X = tmp[0] // n
  Y = tmp[0] % n
  return X, Y
 
def isAvailable(x, y, arr, size, n, history):
  if 0 <= x < n and 0 <= y < n:
    if history[x][y] != 1:
      if arr[x][y] <= size:
        return True
  return False
 
def calc(n, start, arr, size):
  queue = [start]
  a, b = start
  history = [[0] * n for _ in range(n)]
  history[a][b] = 1
  for length in range(1, n*n):
    nextQueue = []
    eatableQueue = []
    for x, y in queue:
      for p, q in [(0, 1), (0, -1), (1, 0), (-1, 0)]:
        if isAvailable(x+p, y+q, arr, size, n, history):
          nextQueue.append((x+p, y+q))
          history[x+p][y+q] = 1
          if 0 < arr[x+p][y+q] < size:
            eatableQueue.append((x+p, y+q))
    if len(eatableQueue) != 0:
      x, y = findTopLeft(eatableQueue, n)
      arr[a][b] = 0
      arr[x][y] = 9
      return length, x, y
    if len(nextQueue) == 0:
      return 0, 0, 0
    queue = copy.deepcopy(nextQueue)
 
def solve():
  n = int(input())
  arr = []
  for i in range(n):
    line = [int(x) for x in input().split()]
    for j in range(n):
      if line[j] == 9:
        start = (i, j)
        break
    arr.append(line)
  rst = 0
  size = 2
  cnt = 0
  while True:
    ret, a, b = calc(n, start, arr, size)
    if ret == 0:
      print(rst)
      return
    cnt += 1
    if cnt == size:
      size += 1
      cnt = 0
    rst += ret
    start = (a, b)
 
solve()