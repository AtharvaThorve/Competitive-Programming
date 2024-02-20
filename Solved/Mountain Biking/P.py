import math
input_line = input()

n, g = map(int, input_line.split())

input_values = []

for _ in range(n):
    line = input().strip()
    values = line.split()
    input_values.append((int(values[0]), int(values[1])))

ans = [0.0] * n

ans[n-1] = 2 * input_values[n-1][0] * g * math.cos(math.radians(input_values[n-1][1]))
for i in range(n-2, -1, -1):
    ans[i] = ans[i+1] + (2 * input_values[i][0] * g * math.cos(math.radians(input_values[i][1])))

for d in ans:
    print(math.sqrt(abs(d)))
