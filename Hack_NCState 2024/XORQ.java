import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class XORQ {
    
    static final int maxBit = 15;
    
    public static void main(String[] args) throws IOException {
        int t = nextInt();
        ArrayList<Integer>[] list = new ArrayList[1 << (maxBit + 1)];
        for(int i = 0; i < list.length; ++i)
            list[i] = new ArrayList<Integer>();
        while(--t >= 0) {
            int n = nextInt();
            int q = nextInt();
            for(int i = 0; i < list.length; ++i)
                list[i].clear();
            for(int i = 0; i < n; ++i) {
                int a = nextInt();
                for(int k = 0, j = 1 << (maxBit - 1); j > 0; j >>= 1) {
                    if((a & j) == 0) {
                        k = 2 * k + 1;
                    } else {
                        k = 2 * k + 2;
                    }
                    list[k].add(i);
                }
            }
            
            for(int i = 0; i < q; ++i) {
                int a = nextInt();
                int l = nextInt() - 1;
                int r = nextInt() - 1;
                int result = 0;
                for(int k = 0, j = 1 << (maxBit - 1); j > 0; j >>= 1) {
                    int k1 = 2 * k + 1;
                    int k2 = 2 * k + 2;
                    if((a & j) == 0) {
                        int tmp = k1;
                        k1 = k2;
                        k2 = tmp;
                    }
                    
                    if(!find(list[k1], l, r)) {
                        k = k2;
                    } else {
                        k = k1;
                        result |= j;
                    }
                }
                System.out.println(result);
            }
        }
    }

    private static int nextInt() throws IOException {
        InputStream in = System.in;
        int c;
        do {
            c = in.read();
        } while(c >= 0 && c <= ' ');
        boolean neg = false;
        if(c == '-') {
            neg = true;
            c = in.read();
        }
        int n = 0;
        while(c >= '0' && c <= '9') {
            n *= 10;
            n += c - '0';
            c = in.read();
        }
        return neg ? -n : n;
    }

    private static boolean find(ArrayList<Integer> list, int l, int r) {
        int s = 0, e = list.size() - 1;
        while(s <= e) {
            int mid = (s + e) >> 1;
            int value = list.get(mid);
            if(value < l) {
                s = mid + 1;
            } else if(value > r) {
                e = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}