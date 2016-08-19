/**
 * Created by mohammad on 8/19/16.
 */
public class FenwickTree {
    /*
    * Start from the index + 1 if you are updating index in original array.
    * Keep adding this value for next node until you're outside of range    *
    * */
    public void updateFenwick(int fenwick[], int val, int index){
        while (index < fenwick.length){
            fenwick[index] += val;
            index = getNext(index);
        }
    }
    /*
    * Start from index + 1 if you want prefix sum 0 to index.
    * Keep adding value until you reach 0
    * */
    public int getSum(int fenwick[], int index){
        index++;
        int sum = 0;
        while (index > 0){
            sum += fenwick[index];
            index = getParent(index);
        }
        return sum;
    }
    /*Tree creation is basically tree updating for all n*/
    public int[] createTree(int input[]){
        int fenwick[] = new int[input.length + 1];
        for (int i = 1; i <= input.length; i++)
            updateFenwick(fenwick, input[i-1], i);
        return fenwick;
    }
    /*
    * Getting parent
    * 1. 2's complement to get minus of index
    * 2. AND this with index
    * 3. Subtract that
    * */
    private int getParent(int index){
        return index - (index & -index);
    }
    /*
    * To get next
    * 1. 2's complement to get minus
    * 2. AND with index
    * 3. add to index
    * */
    private int getNext(int index){
        return index + (index & -index);
    }
    public static void main(String[] args){
        int input[] = {1, 2, 3, 4, 5, 6, 7};
        FenwickTree ft = new FenwickTree();
        int fenwick[] = ft.createTree(input);
        assert 1 == ft.getSum(fenwick,0);
        assert 3 == ft.getSum(fenwick, 1);
        assert 6 == ft.getSum(fenwick, 2);
        assert 10 == ft.getSum(fenwick, 3);
        assert 15 == ft.getSum(fenwick, 4);
        assert 21 == ft.getSum(fenwick, 5);
        assert 28 == ft.getSum(fenwick, 6);
    }
}
