N  = int(input())
bu = input().split()

def recur_min(idx, minus, index):
    global flag
    if idx == N+1:
        print(*stack, sep ='')
        flag = True
        return
    if minus:
        for i, num in enumerate(opt[:index]):
            if visited[i]: continue
            stack.append(num)
            visited[i] = True
            recur_min(idx+1, False if bu[idx%N] == '<' else True, i)
            if flag: return
            visited[i] = False
            stack.pop()
    else:
        for i, num in enumerate(opt[index+1:], start = index+1):
            if visited[i]: continue
            visited[i] = True
            stack.append(num)
            recur_min(idx+1, False if bu[idx%N] == '<' else True, i)
            if flag: return
            stack.pop()
            visited[i] = False


def recur_max(idx, minus, index):
    global flag
    if idx == N+1:
        print(*stack, sep = '')
        flag = True
        return
    if minus:
        for i, num in enumerate(opt[:index]):
            if visited[i]: continue
            stack.append(num)
            visited[i] = True
            recur_max(idx+1, False if bu[idx%N] == '>' else True, i)
            if flag: return
            visited[i] = False
            stack.pop()
    else:
        for i, num in enumerate(opt[index+1:], start = index+1):
            if visited[i]: continue
            visited[i] = True
            stack.append(num)
            recur_max(idx+1, False if bu[idx%N] == '>' else True, i)
            if flag: return
            stack.pop()
            visited[i] = False


opt = [i for i in range(9, 9-N-1, -1)]
visited = [False] * (N+1)
stack = []
flag =False
for i, num in enumerate(opt):
    stack.append(num)
    visited[i] = True
    recur_max(1, False if bu[0] == '>' else True, i)
    if flag: break
    visited[i] = False
    stack.pop()


opt = [i for i in range(N+1)]
visited = [False] * (N+1)
stack = []
flag = False
for i, num in enumerate(opt):
    stack.append(num)
    visited[i] = True
    recur_min(1, False if bu[0] == '<' else True, i)
    if flag: break
    visited[i] = False
    stack.pop()