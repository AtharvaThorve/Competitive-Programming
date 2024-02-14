import java.util.Scanner;

public class ArrayMinimizer {

    static long half(long x) {
        return (x + 1) / 2;
    }

    static long sub(long x, long b) {
        return Math.max(x - b, 0);
    }

    static long sum(long[] arr, int start, int end) {
        long sum = 0;
        for (int i = start; i <= end; i++) {
            sum += arr[i];
        }
        return sum;
    }

    static long calculateMinCost(long[] arr, int n, long b, long p) {
        long[] prefixSum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }

        long minCost = Long.MAX_VALUE;

        for (int i = 1; i <= p; i++) {
            long operationsRemaining = p - i;
            long cost = 0;

            // Apply operations to the first 'i' elements
            cost += half(prefixSum[i]);
            cost += sub(prefixSum[i], b);

            // Apply remaining operations to the next 'operationsRemaining' elements
            cost += sum(arr, i + 1, i + (int) operationsRemaining);
            cost += (operationsRemaining * b);

            minCost = Math.min(minCost, cost);
        }

        return minCost;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt(); // Number of test cases

        for (int testCase = 0; testCase < t; testCase++) {
            int n = scanner.nextInt();
            long b = scanner.nextLong();
            long k1 = scanner.nextLong();
            long k2 = scanner.nextLong();

            long[] a = new long[n + 1];
            for (int i = 1; i <= n; i++) {
                a[i] = scanner.nextLong();
            }

            long p = Math.min(k1 + k2, n); // Calculate the maximum number of elements to apply both operations

            long minCost = calculateMinCost(a, n, b, p);
            System.out.println(minCost);
        }

        scanner.close();
    }
}
