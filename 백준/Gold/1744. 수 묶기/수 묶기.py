N = int(input())
pos, neg, one = [], [], []
result = 0

for _ in range(N):
    num = int(input())
    if num > 1:
        pos.append(num)
    elif num <= 0:
        neg.append(num)
    else:
        one.append(num)

pos.sort(reverse=True)
neg.sort()

# 양수 묶기
for i in range(0, len(pos) - 1, 2):
    result += pos[i] * pos[i + 1]
if len(pos) % 2 == 1:  
    result += pos[-1]

# 음수 묶기
for i in range(0, len(neg) - 1, 2):
    result += neg[i] * neg[i + 1]
if len(neg) % 2 == 1:  
    result += neg[-1]

result += len(one)

print(result)
