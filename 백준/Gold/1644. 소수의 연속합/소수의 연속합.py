num = int(input())

bool = [True] * (num + 1)
bool[0] = False
bool[1] = False

arr = []

for i in range(2, int(num ** 0.5) + 1):
    if bool[i]:
        bool[i * 2::i] = [False] * ((num - i) // i)

# 소수 배열 생성
for i in range(num + 1):
    if bool[i] == True:
        arr.append(i)


start = end = cnt = hap = 0

while True:

    if hap == num:
        cnt += 1

    if hap > num:
        hap -= arr[start]
        start += 1

    elif end == len(arr):
        break

    elif hap <= num:
        hap += arr[end]
        end += 1



print(cnt)