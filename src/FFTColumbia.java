/**
 * Created by mohammad on 8/25/16.
 */

public class FFTColumbia {
    int n, m;

    //lookup tables. Only need to recompute when size of FFT changes
    double[] cos;
    double[] sin;

    double[] window;

    public FFTColumbia(int n) {
        this.n = n;
        this.m = (int) (Math.log(n) / Math.log(2));

        //Make sure n is power of 2
        if (n != (1 << m))
            throw new RuntimeException("FFT Length must be power of 2");

        //precompute tables
        cos = new double[n / 2];
        sin = new double[n / 2];

        for (int i = 0; i < n / 2; i++) {
            cos[i] = Math.cos(-2 * Math.PI * i / n);
            sin[i] = Math.sin(-2 * Math.PI * i / n);
        }

        makeWindow();
    }

    protected void makeWindow() {
        //create blackman window
        // w(n)=0.42-0.5cos{(2*PI*n)/(N-1)}+0.08cos{(4*PI*n)/(N-1)};
        window = new double[n];
        for (int i = 0; i < window.length; i++)
            window[i] = .42 - .5 * Math.cos(2 * Math.PI * i / (n-1))
                    + .08 * Math.cos(4 * Math.PI * i / (n-1));
    }

    public double[] getWindow() {
        return window;
    }
    /***************************************************************
     00089   * fft.c
     00090   * Douglas L. Jones
     00091   * University of Illinois at Urbana-Champaign
     00092   * January 19, 1992
     00093   * http://cnx.rice.edu/content/m12016/latest/
     00094   *
     00095   *   fft: in-place radix-2 DIT DFT of a complex input
     00096   *
     00097   *   input:
     00098   * n: length of FFT: must be a power of two
     00099   * m: n = 2**m
     00100   *   input/output
     00101   * x: double array of length n with real part of data
     00102   * y: double array of length n with imag part of data
     00103   *
     00104   *   Permission to copy and use this program is granted
     00105   *   as long as this header is included.
     00106   ****************************************************************/

    public void fft(double[] x, double[] y) {
        int i, j, k, n1, n2, a;
        double c, s, e, t1, t2;

        //bit-reverse
        j = 0;
        n2 = n / 2;
        for (i = 1; i < n-1; i++){
            n1 = n2;
            while (j >= n1) {
                j -= n1;
                n1 /= 2;
            }
            j += n1;

            if (i < j){
                t1 = x[i];
                x[i] = x[j];
                x[j] = t1;
                t1 = y[i];
                y[i] = y[j];
                y[j] = t1;
            }
        }
        //FFT
        n1 = 0;
        n2 = 1;

        for (i = 0; i < m; i++){
            n1 = n2;
            n2 += n2;
            a = 0;

            for (j = 0; j < n1; j++){
                c = cos[a];
                s = sin[a];
                a += 1 << (m - i - 1);

                for (k = j; k < n; k += n2){
                    t1 = c * x[k + n1] - s * y[k + n1];
                    t2 = s * x[k + n1] + c * y[k + n1];
                    x[k + n1] = x[k] - t1;
                    x[k + n1] = y[k] - t2;
                    x[k] = x[k] + t1;
                    y[k] = y[k] + t2;
                }
            }
        }
    }

    //Test FFT
    public static void main(String[] args){
        int N = 8;

        FFTColumbia fft = new FFTColumbia(N);

        double[] window = fft.getWindow();
        double[] re = new double[N];
        double[] im = new double[N];

        //Impulse
        re[0] = 1; im[0] = 0;
        for (int i = 1; i < N; i++)
            re[i] = im[i] = 0;
        beforeAfter(fft, re, im);

        //Nyquist
        for (int i = 0; i < N; i++) {
            re[i] = Math.pow(-1, i);
            im[i] = 0;
        }
        beforeAfter(fft, re, im);
        ///Single sin
        for (int i = 0; i < N; i++){
            re[i] = Math.cos(2 * Math.PI * i / N);
            im[i] = 0;
        }
        beforeAfter(fft, re, im);

        //Ramp
        for (int i = 0; i < N; i++){
            re[i] = i;
            im[0] = 0;
        }
        beforeAfter(fft, re, im);

        long time = System.currentTimeMillis();
        double iter = 30000;
        for (int i = 0; i < iter; i++)
            fft.fft(re, im);
        time = System.currentTimeMillis() - time;
        System.out.println("Averaged " + (time / iter) + "ms per iteration");
    }

    protected static void beforeAfter(FFTColumbia fft, double[] re, double[] im){
        System.out.println("Before: ");
        printReIm(re, im);
        fft.fft(re, im);
        System.out.println("After: ");
        printReIm(re, im);
    }

    protected static void printReIm(double[] re, double[] im){
        System.out.print("Re: [");
        for (int i = 0; i < re.length; i++)
            System.out.print(((int) (re[i] * 1000) / 1000.0) + " ");
        System.out.print("]\nIm: [");
        for (int i = 0; i < im.length; i++)
            System.out.print(((int) (im[i] * 1000) / 1000.0) + " ");

        System.out.println("]");
    }
}