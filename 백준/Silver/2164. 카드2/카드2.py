input = int(input())
square = 2

while True:
    if (input == 1 or input == 2):
        print(input)
        break
    square *= 2
    if (square >= input):
        print((input - (square // 2)) * 2)
        break