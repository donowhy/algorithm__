from itertools import combinations
def solution(number, k):
    collected = []  # 수를 저장하는 스택
    for i, num in enumerate(number):
        # collected의 마지막 원소가 num보다 작고, k>0인 동안 반복
        while collected and collected[-1] < num and k > 0:
            collected.pop()  # collected의 마지막 원소 제거
            k -= 1
        if k == 0:
            collected += list(number[i:])  # 남은 문자열을 모두 collected에 추가
            break
        collected.append(num)  # collected에 num 추가
    collected = collected[:-k] if k > 0 else collected  # k가 0보다 크다면 뒤에서 k개를 제거
    answer = ''.join(collected)  # 문자열로 변환
    return answer
