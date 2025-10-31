a = 100
result = 0
for i in range(1, 3):
    result = a >> i # 1100100 -> 0110010(2+16+32=50) // 1100100 -> 0011001(16+8+1=25)
    result = result + 1 # 50 + 1 = 51 // 25 + 1 = 26
print(result)