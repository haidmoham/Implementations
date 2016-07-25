/**
 * Created by mohammad on 7/25/16.
 */
import java.util.*;
public class GridPaths {
    public static int paths(int R, int C) {
        int[][] result = new int[R][C];
        for (int i = 0; i < R; i++)
            result[i][0] = 1;
        for (int i = 0; i < C; i++)
            result[0][i] = 1;
        for (int i = 1; i < R; i++){
            for (int j = 1; j < C; j++){
                result[i][j] = result[i-1][j] + result[i][j-1];
            }
        }
        return result[R-1][C-1];
    }

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int r = s.nextInt();
        int c = s.nextInt();
        System.out.println(paths(r, c));
    }
}
