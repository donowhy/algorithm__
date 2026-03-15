import sys
# 입력받기
n, c = map(int, sys.stdin.readline().split())

array = []
for i in range(n):
    array.append(int(input()))

# 오름차순으로 정렬
array.sort()

# 이분 탐색을 위한 함수 정의
def binary_search(array, start, end):
    # start가 end보다 작거나 같을 때까지 반복
    while start <= end:
        # mid 계산
        mid = (start + end) // 2

        # 첫번째 집의 좌표
        current = array[0]
        # 공유기 개수
        count = 1

        # 간격(mid)를 기준으로 공유기 설치할 수 있는 집 찾기
        for i in range(1, len(array)):
            # 집과 첫번째 집의 좌표 차이가 간격(mid) 이상인 경우
            if array[i] >= current + mid:
                count += 1  # 공유기 설치
                current = array[i]  # 새로운 기준이 될 집으로 설정

        # 설치한 공유기 개수가 c보다 크거나 같은 경우
        if count >= c:
            global answer
            start = mid + 1  # mid 값을 증가시켜 더 많은 공유기를 설치할 수 있는지 확인
            answer = mid  # 현재까지 가능한 최대 간격을 저장
        else:  # 설치한 공유기 개수가 c보다 작은 경우
            end = mid - 1  # mid 값을 감소시켜 더 적은 공유기를 설치해야 하는지 확인

# 시작점, 끝점 설정
start = 1
end = array[-1] - array[0]

# 가능한 최대 간격을 찾기 위한 이분 탐색 실행
answer = 0
binary_search(array, start, end)

# 가능한 최대 간격 출력
print(answer)
