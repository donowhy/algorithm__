def solutions(number):
    for i in range(len(number)):
        answer_max = '7' * (number[i] % 2) + '1' * (number[i] // 2 - (number[i] % 2))

        ans = [0, 0, 1, 7, 4, 2, 6, 8, 10, 18, 22]
        n = number[i]
        if n <= 10:
            answer_min = ans[n]
        else:
            answer_min = ''
            while n > 0:
                n -= 7
                if n >= 0:
                    answer_min += '8'
                else:
                    n += 7
                    break
            small = {2:'1', 5:'2', 6:'6'}
            if n in small:
                answer_min = small[n] + answer_min
            else:
                if n == 1:
                    answer_min = '10'+answer_min[1:]
                elif n == 3:
                    answer_min = '200'+answer_min[2:]
                elif n == 4:
                    answer_min = '20' + answer_min[1:]

        print(answer_min, end=' ')
        print(answer_max)

num = int(input())
number = [int(input()) for _ in range(num)]
solutions(number)