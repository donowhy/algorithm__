import sys
#정답 봄, 아직 왜 인풋 받을 때 strip쓰는지 모르겠음.
n, m = map(int, input().split())


pokedic_int_key = {} 
pokedic_name_key = {} 
for i in range(n):
    name = sys.stdin.readline().strip()
    pokedic_int_key[i] = name
    pokedic_name_key[name] = i


for _ in range(m):
    item = sys.stdin.readline().strip()
    
    if item.isdigit() == True:  
        print(pokedic_int_key[int(item)-1])
    
    else:
        print(pokedic_name_key[item]+1)