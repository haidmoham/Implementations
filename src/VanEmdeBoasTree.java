/**
 * Created by mohammad on 8/18/16.
 */
public class VanEmdeBoasTree {

    public static int BASE_SIZE = 2; //vEB base size
    public static int NULL = -1 //initial min/max values

    private vEBNode root;

    /*
    * Creates and returns an instance of a VEB tree
    * */
    public static createVEBTree(int universeSize){
        if (isPowerOf2(universeSize)) return new VanEmdeBoasTree(universeSize);
        else {
            System.out.println("ERROR: Must create a tree with size power of 2");
            return null;
        }
    }
    /*
    * Insert x into tree
    * */
    public void insert(int x){
        insertR(root, x);
    }
    /*
    * Delete x from tree
    * */
    public void delete(int x){
        deleteR(root, x);
    }
    /*
    * Returns true if x is in tree and false otherwise
    * */
    public int predecessor(int x){
        return predecessorR(root, x);
    }
    /*
    * Return min value in tree or -1 if tree empty
    * */
    public int min(){
        return root.min;
    }
    /*
    * Return max, or -1 if tree is empty
    * */
    public int max(){
        return root.max;
    }
    private VanEmdeBoasTree(int universeSize){
//        Create root, handle all node creation via root
        root = new vEBNode(universeSize);
    }
    private void insertR(vEBNode node, int x){
        //This node is empty
        if (NULL == node.min){
            node.min = x;
            node.max = x;
        }
        if (x < node.min){
            int tempVal = x;
            x = node.min;
            node.min = tempVal;
        }
        if (x > node.min && node.universeSize > BASE_SIZE){
            int highOfX = high(node, x);
            int lowOfX = low(node, x);

            //case of non-empty cluster
            if (NULL != node.cluster[highOfX].min){
                //Insert into the cluster recursively
                insertR(node.cluster[highOfX], lowOfX);
            }
            else {
                //Insert into the summary recursively
                insertR(node.summary, highOfX);
                node.cluster[highOfX].min = lowOfX;
                node.cluster[highOfX].max = lowOfX;
            }
        }
        if (x > node.max){
            node.max = x;
        }
    }
    private void deleteR(vEBNode node, int x){
        if (node.min == node.max){
            node.min = NULL;
            node.max = NULL;
        }
        else if (BASE_SIZE == node.universeSize){
            if (0 == x) node.min = 1;
            else node.min = 0;
            node.max = node.min;
        }
        else if (x == node.min){
            int summaryMin = node.summary.min;
            x = index(node, summaryMin, node.cluster[summaryMin].min);
            node.min = x;

            int highOfX = high(node, x);
            int lowOfX = low(node, x);
            deleteR(node.cluster[highOfX], lowOfX);

            if (NULL == node.cluster[highOfX].min){
                deleteR(node.summary, highOfX);
                if (x == node.max){
                    int summaryMax = node.summary.max;
                    if (NULL == summaryMax) node.max = node.min;
                    else node.max = index(node, summaryMax, node.cluster[summaryMax].max);
                }
            }
            else if (x == node.max) node.max = index(node, highOfX, node.cluster[highOfX].max);
        }
    }

    private boolean searchR(vEBNode node, int x){
        if (x == node.min || x == node.max) return true;
        else if (BASE_SIZE == node.universeSize) return false;
        else return searchR(node.cluster[high(node, x), low(node, x)]);
    }

    public int predecessorR(vEBNode node, int x){
        if (BASE_SIZE == node.universeSize){
            if (1 == x && 0 == node.min) return 0;
            else return NULL;
        }
        else if (NULL != node.max && x > node.max) return node.max;
        else {
            int highOfX = high(node, x);
            int lowOfX = low(node, x);

            int minCluster = node.cluster[highOfX].min;
            if (NULL != minCluster && lowOfX > minCluster) return index(node, highOfX, predecessorR(node.cluster[highOfX], lowOfX));
            else {
                int clusterPred = predecessorR(node.summary, highOfX);
                if (NULL == clusterPred){
                    if (NULL != node.min && x > node.min) return node.min;
                    else return NULL;
                }
                else return index(node, clusterPred, node.cluster[clusterPred].max);
            }
        }
    }
    /*
	 * Returns the integer value of the first half of the bits of x.
	 */
    private int high(vEBNode node, int x){
        return (int) Math.floor(x / lowerSquareRoot(node));
    }

    /*
	 * Returns the integer value of the second half of the bits of x.
	 */

    private int low(vEBNode node, int x){
        return x % (int) lowerSquareRoot(node);
    }
    /*
    * Returns the value of the least significant bits of x.
    */
    private double lowerSquareRoot(vEBNode node){
        //changes to base 2 since java is dum
        return Math.pow(2, Math.floor((Math.log10(node.universeSize) / Math.log10(2)) / 2.0));
    }
    /*
	 * Returns the index in the tree of the given value.
	 */
    private int index(vEBNode node, int x, int y){
        return (int) (x * lowerSquareRoot(node) + y);
    }
    /*
	 * Returns true if x is a power of 2, false otherwise.
	 */
    private static boolean isPowerOf2(int x){
        if (0 == x) return false;
        while (x % 2 == 0) x /= 2;
        if (x > 1) return false;
        return true;
    }




    class vEBNode {
        public int universeSize;
        public int min;
        public int max;
        public vEBNode summary;
        public vEBNode[] cluster;

        public vEBNode(int universeSize){
            this.universeSize = universeSize;
            min = VanEmdeBoasTree.NULL;
            max = VanEmdeBoasTree.NULL;

            /*Allocate the summary and cluster children*/
            initializeChildren(universeSize);
        }
        private void initializeChildren(int universeSize){
            if (universeSize <= VanEmdeBoasTree.BASE_SIZE){
                summary = null;
                cluster = null;
            }
            else {
                int childUniverseSize = higherSquareRoot();

                summary = new vEBNode(childUniverseSize);
                cluster = new vEBNode[childUniverseSize];

                for (int i = 0; i < childUniverseSize; i++) cluster[i] = new vEBNode(childUniverseSize);
            }
        }

        private int higherSquareRoot(){
            return (int) Math.pow(2, Math.ceil(Math.log10(universeSize) / Math.log10(2) / 2));
        }
    }


}
