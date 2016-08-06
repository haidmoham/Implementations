/**
 * Created by mohammad on 8/5/16.
 */
import java.util.*;

class AVLNode {
    AVLNode left, right;
    int data;
    int height;
    /*Constructor*/
    public AVLNode(){
        left = null;
        right = null;
        data = 0;
        height = 0;
    }
    /*Constructor*/
    public AVLNode(int n){
        left = null;
        right = null;
        data = n;
        height = 0;
    }
}
/*AVL Tree class*/
public class AVLTree {
    private AVLNode root;

    /*Constructor*/
    public AVLTree(){
        root = null;
    }
    /*Check if empty tree*/
    public boolean isEmpty(){
        return root == null;
    }
    /*Makes the tree empty*/
    public void makeEmpty(){
        root = null;
    }
    /*Insert data function*/
    public void insert(int data){
        root = insert(data, root);
    }
    /*Function for height*/
    private int height(AVLNode t){
        return t == null ? -1 : t.height;
    }
    /*Function to max of left/right node*/
    private int max(int lhs, int rhs){
        return lhs > rhs ? lhs : rhs;
    }
    /*Function to insert data recursively*/
    private AVLNode insert(int x, AVLNode t){
        if (t == null)
            t = new AVLNode(x);
        else if (x < t.data){
            t.left = insert(x, t.left);
            if (height(t.left) - height(t.right) == 2){
                if (x < t.left.data)
                    t = rotateWithLeftChild(t);
                else
                    t = doubleWithLeftChild(t);
            }
        }
        else if (x > t.data){
            t.right = insert(x, t.right);
            if (height(t.right) - height(t.left) == 2)
                if (x > t.right.data)
                    t = rotateWithRightChild(t);
                else
                    t = doubleWithRightChild(t);
        }
        else;
        //duplicate case, do nothing
        t.height = max(height(t.left), height(t.right)) + 1;
        return t;
    }
    /*Rotate tree with left child*/
    private AVLNode rotateWithLeftChild(AVLNode k2){
        AVLNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = max(height(k2.left), height(k2.right)) + 1;
        k1.height = max(height(k1.left), k2.height) + 1;
        return k1;
    }
    /*Rotate tree with right child*/
    private AVLNode rotateWithRightChild(AVLNode k1){
        AVLNode k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k2.left = k1;
        k1.height = max(height(k1.left), height(k1.right)) + 1;
        k2.height = max(height(k2.right), k1.height) + 1;
        return k2;
    }
    /*
    * Double rotate binary tree node: first left child
    * with its right child, then node k3 with left child
    * */
    private AVLNode doubleWithLeftChild(AVLNode k3){
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }
    /*Double rotate binary tree node: first right child
    * with its left child; then k1 with right child*/
    private AVLNode doubleWithRightChild(AVLNode k1){
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }
    /*Function to count number of nodes*/
    public int countNodes(){
        return countNodes(root);
    }
    private int countNodes(AVLNode r){
        if (r == null) return 0;
        else {
            int l = 1;
            l += countNodes(r.left);
            l += countNodes(r.right);
            return l;
        }
    }
    /*Functions to search for a particular element*/
    public boolean search(int val){
        return search(root, val);
    }
    public boolean search(AVLNode r, int val){
        boolean found = false;
        while ((r != null) && !found){
            int rval = r.data;
            if (val < rval)
                r = r.left;
            else if (val > rval)
                r = r.right;
            else {
                found = true;
                break;
            }
            found = search(r, val);
        }
        return found;
    }
    /*Function for in order traversal*/
    public void inOrder(){
        inOrder(root);
    }
    private void inOrder(AVLNode r){
        if (r != null){
            inOrder(r.left);
            System.out.print(r.data + " ");
            inOrder(r.right);
        }
    }
    /*Function for pre order traversal*/
    public void preOrder() {
        preOrder(root);
    }
    private void preOrder(AVLNode r){
        if (r != null){
            System.out.print(r.data + " ");
            preOrder(r.left);
            preOrder(r.right);
        }
    }
    /*Function for post order traversal*/
    public void postOrder(){
        postOrder(root);
    }
    private void postOrder(AVLNode r){
        if (r != null){
            postOrder(r.left);
            postOrder(r.right);
            System.out.print(r.data + " ");
        }
    }

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        /*Creating a new AVL Tree*/
        AVLTree avlt = new AVLTree();

        System.out.println("AVL Tree Test\n");
        char ch;
        /*Perform tree operations*/
        do {
            System.out.println("\nAVLTree Operations\n");
            System.out.println("1. insert ");
            System.out.println("2. search");
            System.out.println("3. count nodes");
            System.out.println("4. check empty");
            System.out.println("5. clear tree");

            int choice = s.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter number of elements to insert");
                    int n = s.nextInt();
                    System.out.println("Enter " + n + " elements");
                    for (int i = 0; i < n; i++)
                        avlt.insert(s.nextInt());
                    break;
                case 2:
                    System.out.println("Enter element to search for: ");
                    System.out.println("Search result: " + avlt.search(s.nextInt()));
                    break;
                case 3:
                    System.out.println("Nodes = " + avlt.countNodes());
                    break;
                case 4:
                    System.out.println("Empty status = " + avlt.isEmpty());
                    break;
                case 5:
                    System.out.println("\nTree cleared");
                    avlt.makeEmpty();
                    break;
                default:
                    System.out.println("Wrong entry \n");
                    break;
            }
            /*Display tree*/
            System.out.print("\nPost order: ");
            avlt.postOrder();
            System.out.print("\nPre order: ");
            avlt.preOrder();
            System.out.print("\nIn order: ");
            avlt.inOrder();

            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = s.next().charAt(0);
        } while (ch == 'Y' || ch == 'y');
    }
}