import sys
read = sys.stdin.readline

def get_distance(x, y):
    if x == 1:  # 북
        return y
    if x == 2:  # 남
        return w + h + w - y
    if x == 3:  # 서
        return w + h + w + h - y
    if x == 4:  # 동
        return w + y

# 입력
w, h = map(int, read().split())
n = int(read())

# 풀이
course = []
for _ in range(n + 1):  # (0, 0) 에서 상점까지의 거리
    x, y = map(int, input().split())
    course.append(get_distance(x, y))

answer = 0

for i in range(n):  # 동근이와 상점 사이의 최단거리
    in_course = abs(course[-1] - course[i])
    out_course = 2 * (w + h) - in_course
    answer += min(in_course, out_course)

# 출력
print(answer)