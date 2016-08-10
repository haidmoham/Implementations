/**
 * Created by mohammad on 8/9/16.
 */
import java.math.BigInteger;
import java.util.*;

public class Karatsuba {
    private final static BigInteger ZERO = new BigInteger("0");

    public static BigInteger karatsuba(BigInteger x, BigInteger y) {
//        cutoff for brute force
        int N = Math.max(x.bitLength(), y.bitLength());
        if (N <= 2000) return x.multiply(y);

//        Number of bits divided by two, rounded up
        N = (N / 2) + (N % 2);

//        x = a + 2^N b, y = c + 2^N d
        BigInteger b = x.shiftRight(N);
        BigInteger a = x.subtract(b.shiftLeft(N));
        BigInteger d = y.shiftRight(N);
        BigInteger c = y.subtract(d.shiftLeft(N));

//        Compute sub expressions
        BigInteger ac = karatsuba(a, c);
        BigInteger bd = karatsuba(b, d);
        BigInteger abcd = karatsuba(a.add(b), c.add(d));

        return ac.add(abcd.subtract(ac).subtract(bd).shiftLeft(N).add(bd.shiftLeft(2 * N)));
    }

    public static void main(String[] args) {
        long start, stop, elapsed;
        Random random = new Random();
        int N = Integer.parseInt(args[0]);
        BigInteger a = new BigInteger(N, random);
        BigInteger b = new BigInteger(N, random);

        start = System.currentTimeMillis();
        BigInteger c = karatsuba(a, b);
        stop = System.currentTimeMillis();
        System.out.println(stop - start);

        start = System.currentTimeMillis();
        BigInteger d = a.multiply(b);
        stop = System.currentTimeMillis();
        System.out.println(stop - start);

        System.out.println((c.equals(d)));
    }
}
