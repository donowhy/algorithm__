import sys
import heapq as hq

n = int(sys.stdin.readline())
meeting = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]

meeting.sort(key=lambda x: (x[0], x[1]))

rooms = []
hq.heappush(rooms, meeting[0][1])

for i in range(1, n):
    if meeting[i][0] >= rooms[0]:
        hq.heappop(rooms)
    hq.heappush(rooms, meeting[i][1])

print(len(rooms))
