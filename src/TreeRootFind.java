/**
 * Created by mohammad on 8/1/16.
 */
import java.util.*;

public class TreeRootFind {
    static Map<Integer, Integer> branches;
    static int target;
    static Set<Integer> root;
    static int rootn;
    static Set<Integer> nodes;
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        branches = new HashMap<Integer, Integer>();
        root = new HashSet<Integer>();
        nodes = new HashSet<Integer>();
        target = s.nextInt();
        s.nextLine();

        String[] in = s.nextLine().split(" ");

        while(Integer.parseInt(in[0]) != -1){
            root.add(Integer.parseInt(in[0]));
            for (int i = 1; i < in.length;i++){
                if (!in[i].equals("")){
                    nodes.add(Integer.parseInt(in[i]));
                    branches.put(Integer.parseInt(in[i]), Integer.parseInt(in[0]));
                }
            }
            in = s.nextLine().split(" ");
        }

        Iterator iterate = root.iterator();
        rootn = -1;
        int j;
        while(iterate.hasNext()){
            j = (int) iterate.next();
            if (!nodes.contains(j))
                rootn = j;
        }
        findRoot(target);
    }
    public static void findRoot(Integer j){
        System.out.print(j + " ");
        Integer k = branches.get(j);
        if (!k.equals(rootn)) findRoot(k);
        else  System.out.print(k);
    }
}
