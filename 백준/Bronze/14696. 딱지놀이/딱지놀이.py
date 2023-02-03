N = int(input())

for _ in range(N):
    temp_a = list(map(int, input().split()))[1:]  # 입력을 받되 맨 앞의 개수는 제외하고 저장한다.
    temp_b = list(map(int, input().split()))[1:]  # 입력을 받되 맨 앞의 개수는 제외하고 저장한다.

    for i in range(4, 0, -1):  # 4부터 반대로 돈다.
        if temp_a.count(i) > temp_b.count(i):  # 만약 해당 모양의 개수가 A가 더 많다면
            print("A")
            break
        elif temp_a.count(i) < temp_b.count(i):  # 만약 해당 모양의 개수가 B가 더 많다면
            print("B")
            break
        if i == 1:  # 만약 마지막까지 왔는데 break 되지 않았다면
            print("D")  # D를 출력한다.