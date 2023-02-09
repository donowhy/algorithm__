tc = int(input())

dic_lst = {}

dic_lst[1] = 1
dic_lst[2] = 1
dic_lst[3] = 1
dic_lst[4] = 2
dic_lst[5] = 2
dic_lst[6] = 3
dic_lst[7] = 4

for num in range(8, 101):
    dic_lst[num] = (dic_lst[num-1] + dic_lst[num-5])


for i in range(tc):
    num = int(input())
    print(dic_lst.get(num))
