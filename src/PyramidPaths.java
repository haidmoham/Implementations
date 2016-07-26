/**
 * Created by mohammad on 7/25/16.
 */
import java.util.*;

public class PyramidPaths {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        int[][] pyramid = new int[N][N];
        for (int r = 0; r < N; r++){
            for (int c = 0; c <= r; c++){
                pyramid[r][c] = s.nextInt();
            }
        }
        System.out.println(path(pyramid, 0, 0));
    }

    public static int path(int[][] pyramid, int R, int C){
        if (R == pyramid.length) return 0;
        return pyramid[R][C] + Math.max(path(pyramid, R+1, C), path(pyramid, R+1, C+1));
    }
}
