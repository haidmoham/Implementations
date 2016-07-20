/**
 * Created by Mohammad Haider (haidmoham) on 7/19/16.
 */
import java.util.*;

public class KMP {

    private static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        String search  = sc.next();
        String target = sc.next();
        String response = "yes";
        if (KMP(search, target) == -1) {
            response = "no";
        }
        System.out.println(response);
    }

    public static int KMP(String search, String target) {
        int[] failTable = failureTable(target);

        int tP = 0; // Pointer into Target
        int sP = 0; // Pointer into Search
        while (sP < search.length()) {
            if (search.charAt(sP) == target.charAt(tP)) {
                tP++;
                if (tP == target.length()) {
                    return sP - target.length() + 1;
                }
                sP++;
            }
            else if (tP > 0){
                tP = failTable[tP];
            }
            else {
                sP++;
            }
        }
        return -1;
    }

    public static int[] failureTable(String target) {
        int[] table = new int[target.length() + 1];
        table[0] = -1;
        table[1] = 0;

        int left = 0;
        int right = 2;

        while(right < table.length) {
            if (target.charAt(right-1) == target.charAt(left)) {
                left++;
                table[right] = left;
                right++;
            }
            else if(left > 0)
            {
                left = table[left];
            }
            else {
                table[right] = left;
                right++;
            }
        }
        return table;
    }
}