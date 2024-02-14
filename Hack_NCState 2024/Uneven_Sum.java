import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Uneven_Sum {

	public static void main(String[] args) throws IOException {
		FastReader fr = new FastReader();
		FastWriter fw = new FastWriter();
		int qt = fr.nextInt();
		while (qt-- > 0) {
            int n = fr.nextInt();
            if(n == 1) {
                fw.println(1);
            }
            n--;
            int ans = (n) * (n-1);
            ans++;
            fw.println(ans);
		}

		fw.close();
	}

    
}

class Pair<T, U> {
	private T first;
	private U second;

	public Pair(T first, U second) {
		this.first = first;
		this.second = second;
	}

	public T getFirst() {
		return (T) this.first;
	}

	public U getSecond() {
		return (U) this.second;
	}
}

class Node {
	int data;
	Node left, right;

	public Node(int data) {
		this.data = data;
		left = null;
		right = null;
	}
}

class FastReader {
	BufferedReader br;
	StringTokenizer st;

	public FastReader() {
		br = new BufferedReader(
				new InputStreamReader(System.in));
	}

	String next() {
		while (st == null || !st.hasMoreElements()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return st.nextToken();
	}

	int nextInt() {
		return Integer.parseInt(next());
	}

	long nextLong() {
		return Long.parseLong(next());
	}

	double nextDouble() {
		return Double.parseDouble(next());
	}

	String nextLine() {
		String str = "";
		try {
			if (st.hasMoreTokens()) {
				str = st.nextToken("\n");
			} else {
				str = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
}

class FastWriter {
	private final BufferedWriter bw;

	public FastWriter() {
		this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
	}

	public void print(Object object) throws IOException {
		bw.append("" + object);
	}

	public void println(Object object) throws IOException {
		print(object);
		bw.append("\n");
	}
	public void println() throws IOException {
		bw.append("\n");
	}

	public void close() throws IOException {
		bw.close();
	}
}

class Helper {

	static Integer[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	/*
	 * Returns the index of the first element in the array which has a value greater
	 * than key or last if no such element is found
	 */
	static int upperBoundOfArray(int[] arr, int key) {
		int index = Arrays.binarySearch(arr, key);
		int n = arr.length;
		if (index < 0)
			return Math.abs(index) - 1;
		while (index < n && arr[index] == key) {
			index++;
		}
		return index;
	}

	/*
	 * Returns the index of the first element in the array which has a value not
	 * less than the key
	 */
	static int lowerBoundOfArray(int[] arr, int key) {
		int index = Arrays.binarySearch(arr, key);
		if (index < 0)
			return Math.abs(index) - 1;
		while (index > 0 && arr[index - 1] == key) {
			index--;
		}
		return index;
	}

	static int upperBoundOfList(ArrayList<Integer> arr, int key) {
		int index = Collections.binarySearch(arr, key);
		int n = arr.size();
		if (index < 0)
			return Math.abs(index) - 1;
		while (index < n && arr.get(index) == key) {
			index++;
		}
		return index;
	}

	static int lowerBoundOfList(ArrayList<Integer> arr, int key) {
		int index = Collections.binarySearch(arr, key);
		if (index < 0)
			return Math.abs(index) - 1;
		while (index > 0 && arr.get(index - 1) == key) {
			index--;
		}
		return index;
	}

	static void fill2dArray(int[][] mat, int value, int rows) {
		for (int i = 0; i < rows; ++i) {
			Arrays.fill(mat[i], value);
		}
	}

	/*
	 * Function to sort a two dimensional array
	 * It has three parameters:
	 * arr: TwoD array to be sorted
	 * index: The index on which the array needs to be sorted
	 * asecending: Value to determine whether to sort the array in ascending order
	 * or descending order.
	 * Use value 1 when sorting in asecending order and value -1 when sorting in
	 * descending order
	 */
	static void sort(int[][] arr, int index, int ascending) {
		Arrays.sort(arr, new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				return ascending * (a[index] - b[index]);
			}
		});
	}

	// Modular power function
	static int power(int x, int y, int p) {
		int res = 1;
		x %= p;
		if (x == 0)
			return 0;
		while (y > 0) {
			if ((y & 1) == 1)
				res = (res * x) % p;
			y >>= 1;
			x = (x * x) % p;
		}
		return res;
	}

	// gcd of two numbers
	static int gcd(int a, int b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}

	static HashMap<Integer, Integer> sortByValueMap(HashMap<Integer, Integer> hm) {
		List<Map.Entry<Integer, Integer>> list = new LinkedList<Map.Entry<Integer, Integer>>(hm.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
			public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
				if ((o1.getValue()).compareTo(o2.getValue()) == 1) {
					return -1;
				} else if ((o1.getValue()).compareTo(o2.getValue()) == -1) {
					return 1;
				} else {
					return 0;
				}
			}
		});
		HashMap<Integer, Integer> temp = new LinkedHashMap<Integer, Integer>();
		for (Map.Entry<Integer, Integer> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
	}

	static int min(int... a) {
		int min = Integer.MAX_VALUE;
		for (int i : a) {
			if (i < min)
				min = i;
		}
		return min;
	}

	static int max(int... a) {
		int max = Integer.MIN_VALUE;
		for (int i : a) {
			if (i > max)
				max = i;
		}
		return max;
	}

	// dfs of a graph
	static void printdfs(int src, ArrayList<ArrayList<Integer>> list, boolean vis[], int count) {
		if (count != list.size()) {
			vis[src] = true;
			System.out.print(src + " ");
			count++;
			for (int i = 0; i < list.get(src).size(); i++) {
				int k = (int) list.get(src).get(i);
				if (vis[k] == false) {
					printdfs(k, list, vis, count);
				}
			}
		}
	}

	static void dfs(int src, ArrayList<ArrayList<Integer>> list, boolean vis[]) {
		printdfs(src, list, vis, 0);
	}

	// bfs of graph
	static ArrayList<String> printbfs(int s, ArrayList<ArrayList<Integer>> list, boolean vis[]) {
		Queue<Integer> q = new LinkedList<>();
		ArrayList<String> ans = new ArrayList<>();
		int val = s;
		q.add(val);
		vis[s] = true;
		while (!q.isEmpty()) {
			int root = q.peek();
			for (int i = 0; i < list.get(root).size(); i++) {
				int k = list.get(root).get(i);
				if (vis[k] == false) {
					q.add(k);
					vis[k] = true;
				}
			}
			q.remove();

			ans.add(root+"");
		}
		return ans;
	}

	static ArrayList<String> bfs(int src, ArrayList<ArrayList<Integer>> list, boolean vis[]) {
		return printbfs(src, list, vis);
	}

	static void printMatrix(int[][] mat) {
		int n = mat.length;
		int m = mat[0].length;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				System.out.print(mat[i][j] + " ");
			}
			System.out.println();
		}
	}

	// distance between 2 points in 2D plane
	static double distance(int x1, int y1, int x2, int y2) {
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}

	// level order traversal binary tree
	static void level_order(Node node) {
		Queue<Node> q = new LinkedList<>();
		q.add(node);
		while (!q.isEmpty()) {
			if (node != null) {
				if (node.left != null) {
					q.add(node.left);
				}
				if (node.right != null) {
					q.add(node.right);
				}
				Node val = q.remove();

				System.out.print(val.data + " ");
				node = q.peek();
			}
		}
	}

	// nth catalan number
	static BigInteger cat(int n) {
		BigInteger arr[] = new BigInteger[n + 2];
		arr[0] = BigInteger.ONE;
		arr[1] = BigInteger.ONE;
		for (int i = 2; i <= n; i++) {
			arr[i] = BigInteger.ZERO;
			for (int j = 0; j < i; j++) {
				arr[i] = arr[i].add(arr[j].multiply(arr[i - j - 1]));
			}
		}
		return arr[n];

	}

	// Find longest increasing subsequence in an array
	static ArrayList<Integer> lis(int[] nums, int n) {
		ArrayList<Integer> out = new ArrayList<>();
		for (int val : nums) {
			if (out.size() == 0 || out.get(out.size() - 1) < val)
				out.add(val);
			else {
				int i = lowerBoundOfList(out, val);
				out.set(i, val);
			}
		}
		return out;
	}

	// Longest common Subsequence of two arrays.
	static ArrayList<Integer> lcs2(int[] arr, int[] brr, int n, int m) {
		int[][] table = new int[n + 1][m + 1];
		for (int i = 0; i <= n; ++i) {
			for (int j = 0; j <= m; ++j) {
				if (i == 0 || j == 0)
					table[i][j] = 0;
				else if (arr[i - 1] == brr[j - 1]) {
					table[i][j] = table[i - 1][j - 1] + 1;
				} else {
					table[i][j] = max(table[i - 1][j], table[i][j - 1]);
				}
			}
		}
		int index = table[n][m];
		ArrayList<Integer> lcs = new ArrayList<>();
		for (int i = 0; i < index; ++i) {
			lcs.add(i, -1);
		}
		for (int i = n, j = m; i > 0 && j > 0;) {
			if (arr[i - 1] == brr[j - 1]) {
				lcs.set(index - 1, arr[i - 1]);
				i--;
				j--;
			} else if (table[i - 1][j] > table[i][j - 1]) {
				i--;
			} else {
				j--;
			}
		}
		return lcs;
	}

	static String lcs2OfString(String a, String b) {
		int[][] table = new int[a.length() + 1][b.length() + 1];
		for (int i = 0; i <= a.length(); ++i) {
			for (int j = 0; j <= b.length(); ++j) {
				if (i == 0 || j == 0) {
					table[i][j] = 0;
				} else if (a.charAt(i - 1) == b.charAt(j - 1)) {
					table[i][j] = table[i - 1][j - 1] + 1;
				} else {
					table[i][j] = max(table[i - 1][j], table[i][j - 1]);
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		int i = a.length(), j = b.length();
		while (i > 0 && j > 0) {
			if (a.charAt(i - 1) == b.charAt(j - 1)) {
				sb.append(a.charAt(i - 1));
				i--;
				j--;
			} else if (table[i - 1][j] > table[i][j - 1]) {
				i--;
			} else {
				j--;
			}
		}
		return sb.reverse().toString();
	}

	static String shortestCommonSupersequence(String s1, String s2) {
		int n = s1.length();
		int m = s2.length();
		int[][] dp = new int[n + 1][m + 1];
		for (int i = 1; i <= n; ++i) {
			for (int j = 1; j <= m; ++j) {
				if (s1.charAt(i - 1) == s2.charAt(j - 1))
					dp[i][j] = 1 + dp[i - 1][j - 1];
				else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
			}
		}
		StringBuilder sb = new StringBuilder();
		while (n > 0 && m > 0) {
			if (s1.charAt(n - 1) == s2.charAt(m - 1)) {
				sb.append(s1.charAt(n - 1));
				n--;
				m--;
			} else if (dp[n - 1][m] > dp[n][m - 1]) {
				sb.append(s1.charAt(n - 1));
				n--;
			} else {
				sb.append(s2.charAt(m - 1));
				m--;
			}
		}
		while (n > 0) {
			sb.append(s1.charAt(n - 1));
			n--;
		}
		while (m > 0) {
			sb.append(s2.charAt(m - 1));
			m--;
		}
		return sb.reverse().toString();
	}

	/*
	 * Reverse Array between two indices
	 */
	static int[] reverse(int[] arr, int start, int end) {
		while(start < end) {
			int temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			start++;
			end--;
		}
		return arr;
	}

	static ArrayList<Integer> stringMatchPattern(String pat, String txt) {
		ArrayList<Integer> arrl = new ArrayList<>();
		int M = pat.length();
		int N = txt.length();

		// create lps[] that will hold the longest
		// prefix suffix values for pattern
		int lps[] = new int[M];
		int j = 0; // index for pat[]

		// Preprocess the pattern (calculate lps[]
		// array)
		computeLPSArray(pat, M, lps);

		int i = 0; // index for txt[]
		while (i < N) {
			if (pat.charAt(j) == txt.charAt(i)) {
				j++;
				i++;
			}
			if (j == M) {
				arrl.add(i - j);
				j = lps[j - 1];
			}

			// mismatch after j matches
			else if (i < N && pat.charAt(j) != txt.charAt(i)) {
				// Do not match lps[0..lps[j-1]] characters,
				// they will match anyway
				if (j != 0)
					j = lps[j - 1];
				else
					i++;
			}
		}
		return arrl;
	}

	static void computeLPSArray(String pat, int M, int lps[]) {
		// length of the previous longest prefix suffix
		int len = 0;
		int i = 1;
		lps[0] = 0; // lps[0] is always 0

		// the loop calculates lps[i] for i = 1 to M-1
		while (i < M) {
			if (pat.charAt(i) == pat.charAt(len)) {
				len++;
				lps[i] = len;
				i++;
			}
			// (pat[i] != pat[len])
			else {
				// This is tricky. Consider the example.
				// AAACAAAA and i = 7. The idea is similar
				// to search step.
				if (len != 0) {
					len = lps[len - 1];

					// Also, note that we do not increment
					// i here
				}
				// if (len == 0)
				else {
					lps[i] = len;
					i++;
				}
			}
		}
	}

	// Can be used to solve multiple questions
	// 1. Check whether a subset with given sum exists or not? Just check whether
	// the value returned is more
	// 1 or not
	// 2. Find number of subsets with given sum? Return the value returned by the
	// method
	// 3. Find whether the array can be divided into two subsets of same sum? Put
	// the sum value as the sum of
	// array / 2
	// 4. Count the number of subsets with given difference? Put the sum value as
	// (sum of array + diff) / 2
	static int subsetSum(int a[], int n, int sum) {

		// Initializing the matrix
		int tab[][] = new int[n + 1][sum + 1];

		// Initializing the first value of matrix
		tab[0][0] = 1;

		for (int i = 1; i <= sum; i++)
			tab[0][i] = 0;

		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= sum; j++) {

				// If the value is greater than the sum
				if (a[i - 1] > j)
					tab[i][j] = tab[i - 1][j];

				else
					tab[i][j] = tab[i - 1][j] + tab[i - 1][j - a[i - 1]];

			}
		}
		return tab[n][sum];
	}
	
	static boolean isVowel(char c) {
	    return c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' || c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}

	static int minDesc(Node root, int min) {
        if(root == null)
            return min;

        if(root.data < min)
            min = root.data;
        
        int leftMin = minDesc(root.left, min);
        int rightMin = minDesc(root.right, min);

        return Math.min(leftMin, Math.min(rightMin, min));
    }

    static int maxDesc(Node root, int max) {
        if(root == null)
            return max;

        if(root.data > max)
            max = root.data;
        
        int leftMax = maxDesc(root.left, max);
        int rightMax = maxDesc(root.right, max);

        return Math.max(leftMax, Math.max(rightMax, max));
    }
}