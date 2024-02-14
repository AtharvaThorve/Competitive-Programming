# Read input as a single line
input_line = input()

# Split the input line to extract n and b
n, b = map(int, input_line.split())

# Calculate the value
val = 1 << (b + 1)

# Check if n is less than or equal to (val - 1)
if n <= (val - 1):
    print("yes")
else:
    print("no")
