import sys
from collections import defaultdict

# 접시 수, 초밥 가짓수, 연속해서 먹는 접시 수, 쿠폰 번호
n, d, k, c = map(int, sys.stdin.readline().split())
arr = [int(sys.stdin.readline()) for _ in range(n)]
arr.extend(arr)   # 원형이라서 2개를 이어준다.
left = 0
right = 0
dict_ = defaultdict(int)
result = 0

dict_[c] += 1          # 보너스는 무조건 먹기

# 1. 처음에 k 구간만큼 먹기
while right < k:
    dict_[arr[right]] += 1
    right += 1

# 2. 슬라이딩 윈도우 적용
while right < len(arr):
    result = max(result, len(dict_))
    # 1. 맨 왼쪽 초밥 제거
    dict_[arr[left]] -= 1
    if dict_[arr[left]] == 0:  # 삭제된 왼쪽 초밥이 0 이면 dict 삭제
        del dict_[arr[left]]
    # 2. 오른쪽 초밥 추가하기
    dict_[arr[right]] += 1
    # 왼쪽 위치 + 1
    left += 1
    # 오른쪽 위치 + 1
    right += 1

print(result)