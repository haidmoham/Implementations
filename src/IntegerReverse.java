/**
 * Created by mohammad on 7/28/16.
 */
import java.util.*;

public class IntegerReverse {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int input = s.nextInt();
        System.out.println(reverse(input));
    }
    public static int reverse(int x) {
        int rev = 0;
        while(x != 0){
            rev = rev*10 + x%10;
            x /= 10;
        }
        return rev;
    }
}
