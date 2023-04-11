import sys
input = sys.stdin.readline

n, m = map(int, input().split())
knowList = set(input().split()[1:])
parties = []
for _ in range(m):
    parties.append(set(input().split()[1:]))

for _ in range(m):
    for party in parties:
        if party & knowList: #knowList와 party의 비트 연산, 같은 번호가 있다면
            knowList = knowList.union(party)

cnt = 0
for party in parties:
    if party & knowList: #진실을 아는 사람이 파티에 있는 경우, 같이 간 사람들이 파티에 있는 경우
        continue
    cnt += 1

print(cnt)
