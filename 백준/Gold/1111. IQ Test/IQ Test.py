n = int(input())
data = list(map(int, input().split()))

if n == 1: 
    print('A')
elif n == 2:
    if data[0] == data[1]:
        print(data[0])
    else:
        print('A')
else:
    if (data[0] - data[1] == 0):
        a = 0
    else:
        a = (data[1] - data[2]) // (data[0] - data[1])

    b = data[1] - data[0] * a
  
    for i in range(n - 1):
        expect = data[i] * a + b  
        if (data[i + 1] != expect): 
            print('B')
            exit()

    print(data[-1] * a + b)