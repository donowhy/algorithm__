i = 1

while True:

    a, b, c = map(int, input().split())
    if a == b == c == 0:
        break

    if (c % b) <= a:
        rest = a * (c // b) + (c % b)
        print(f'Case {i}: {rest}')
    else:
        rest = a * (c // b) + a
        print(f'Case {i}: {rest}')
    i += 1

