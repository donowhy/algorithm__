import sys

input = sys.stdin.readline


def sol2313():
    n = int(input())
    max_value = 0
    answers = []
    for _ in range(n):
        m = int(input())
        jewels = list(map(int, input().split()))

        s, e = 0, 0
        max_total = - 10 ** 9
        min_len = 1
        cur_total = 0
        u, v = 0, 0
        while v < len(jewels):
            cur_jewel = jewels[v]
            cur_total += cur_jewel

            if cur_total <= cur_jewel:
                u = v
                cur_total = cur_jewel

            if cur_total > max_total:
                s, e = u, v
                max_total = cur_total
                min_len = v - u + 1
            elif cur_total == max_total:
                if min_len > v - u + 1:
                    s, e = u, v
                    min_len = v - u + 1

            v += 1

        max_value += max_total
        answers.append(' '.join(map(str, [s + 1, e + 1])))

    return str(max_value) + '\n' + '\n'.join(answers)


if __name__ == '__main__':
    print(sol2313())

