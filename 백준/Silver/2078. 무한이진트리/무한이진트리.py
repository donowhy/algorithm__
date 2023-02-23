import sys

a, b = map(int, sys.stdin.readline().split())
l = 0 # 왼쪽 이동 횟수
r = 0 # 오른쪽 이동 횟수

# a와 b가 1보다 클 때까지 반복한다.
while a > 1 and b > 1:
    # a가 더 크면 왼쪽으로 이동한 경우로
    # a를 b로 나눈 몫은 왼쪽 이동 횟수와 더하고 나머지는 a로 초기화한다.
    if a > b:
        l += a // b
        a %= b

    # 반대로 b가 더 크면 오른쪽으로 이동한 경우로
    # b를 a로 나눈 몫은 오른쪽 이동 횟수와 더하고 나머지는 b로 초기화한다.
    else:
        r += b // a
        b %= a

# a나 b가 1보다 작아지면 남은 이동 거리를 1씩 증가시킨다.
l += a - 1
r += b - 1
print(l, r)