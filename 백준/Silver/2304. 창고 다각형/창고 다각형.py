n = int(input())

lst = []
result = 0
for i in range(n) :
    a,b = map(int,input().split())
    lst.append([a,b])
#기둥을 x축 순으로 정렬한다. 
lst.sort()

#가장 높은 기둥의 번호를 알아낸다. 
i = 0
for l in lst :
    if l[1] >result :
        result = l[1]
        idx = i
    i += 1

#초기 높이는 첫번째 기둥의 높이 
height = lst[0][1]

#최대 높이전까지 돌면서 다음 기둥이 현재보다 높으면 
#result에 현재의 면적을 계산해서 더해주고 높이를 다음 기둥으로 갱신한다.
for i in range(idx) :
    if height < lst[i+1][1] :
        result += height * (lst[i+1][0]-lst[i][0])
        height = lst[i+1][1]
    #아니면 그냥 현재면적을 더해준다. 
    else :
        result += height * (lst[i+1][0] - lst[i][0])

#뒤에서부터도 똑같이 진행한다. 
height = lst[-1][1]

for i in range(n-1, idx, -1) :
    if height < lst[i-1][1] :
        result += height * (lst[i][0]-lst[i-1][0])
        height = lst[i-1][1]
    else :
        result += height * (lst[i][0] - lst[i-1][0])

print(result)