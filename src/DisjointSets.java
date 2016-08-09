import jdk.nashorn.internal.runtime.arrays.NumericElements;

/**
 * Created by mohammad on 8/9/16.
 */
public class DisjointSets {
    private int[] array;
    public DisjointSets(int numElements){
        array = new int[numElements];
        for (int i = 0; i < array.length; i++)
            array[i] = -1;
    }

    /*unites two sets into one set, union-by-rank is implemented
    * to chose new root. This method will corrupt
   *  the data structure if root1 and root2 are not roots of their respective
   *  sets, or if they're identical.
   *
   *  @param root1 - the root from the first set
   *  @param root2 - the root from the other set*/
    public void union(int root1, int root2) {
        if (array[root2] < array[root1]) {
            array[root1] = root2;             // root2 is taller; make root2 new root
        } else {
            if (array[root1] == array[root2]) {
                array[root1]--;            // Both trees same height; new one is taller
            }
            array[root2] = root1;       // root1 equal or taller; make root1 new root
        }
    }

    /*find() finds the int name of the set containing a given element
    * performs path compression along the way
    *
    * @param x the element sought
    * @return the set containing x*/
    public int find(int x) {
        if (array[x] < 0) {
            return x;                         // x is the root of the tree; return it
        } else {
            // Find out who the root is; compress path by making the root x's parent.
            array[x] = find(array[x]);
            return array[x];                                       // Return the root
        }
    }
    public static void main(String[] args){
        int numElements = 128;
        int numInSameSet = 16;

        DisjointSets s = new DisjointSets(numElements);
        int set1, set2;

        for (int k = 1; k < numInSameSet; k *= 2){
            for (int j = 0; j + k < numElements; j+= 2 * k){
                set1 = s.find(j);
                set2 = s.find(j+k);
                s.union(set1, set2);
            }
        }
        for(int i = 0; i < numElements; i++){
            System.out.println(s.find(i) + "*");
            if (i % numInSameSet == numInSameSet - 1){
                System.out.println();
            }
        }
        System.out.println();
    }
}
