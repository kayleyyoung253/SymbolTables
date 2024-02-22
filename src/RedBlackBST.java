public class RedBlackBST<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value> {

    private  Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private class Node{
        Key key;
        Value value;
        Node left;
        Node right;
        //node parent; //optional, but sometimes helpful.
        int nodesInSubtree;    //author calls this int N
        boolean color;

        public  Node(Key key, Value value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            nodesInSubtree = 0;
        }
    }

    public RedBlackBST(){
        root = null;
    }
    private Node put(Node current, Key key, Value val){
        if(current == null){
            Node theNewNode = new Node(key, val);
            theNewNode.nodesInSubtree = 1;
            return theNewNode;
        }
        int cmp = key.compareTo(current.key);

        if (cmp < 0) {
            current.left = put(current.left, key, val);
        } else if (cmp > 0) {
            current.right = put(current.right, key, val);
        }else{
            current.value = val;
        }
        //add some code here to check if there are "4 nodes"
        //indicate by a node having both left and right red links
        // and rebalance via rotation



        current.nodesInSubtree = size(current.left) + size(current.right) + 1;
        return current;
    }
    /**
     * Put a key-value pair into the table
     *
     * @param key
     * @param val
     */
    @Override
    public void put(Key key, Value val) {
        root = put(root, key, val); //starts the recursion
    }

    @Override
    public Value get(Key keyToFind){
        return get(root, keyToFind);   //start private recursive call at the root
    }
    /**
     * Returns the value paired with the given key.
     *
     * @param keyToFind
     */
    private Value get(Node current, Key keyToFind){
        if (current == null){
            return null;
        }
        int cmp = keyToFind.compareTo(current.key);

        if (cmp < 0){
            return get(current.left, keyToFind);
        }
        else if (cmp > 0){
            return get(current.right, keyToFind);
        }
        else {
            return current.value;
        }
    }

    /**
     * Returns the value paired with the given key.
     *
     * @param keyToFind
     */

    public Value getIterative(Key keyToFind) {   //renamed get method
        Node current = root;
        while (current != null){
            int cmp = keyToFind.compareTo(current.key);
            if (cmp < 0) {      ///KeyToFind < current.key
                //go left
                current = current.left;

            }
            else if (cmp > 0){        ///KeyToFind > current.key
                //go right
                current = current.right;
            }
            else{                 ///KeyToFind == current.key
                //otherwise i found it
                return current.value;
            }
        }
        return null;
    }

    /**
     * Returns the number of key-value pairs in the table.
     *
     * @return
     */
    @Override
    public int size() {
        return size(root);
    }

    private int size(Node current){

        //return size(current.left) + size(current.right) + 1;
        if (current == null){
            return 0;
        }else{
            return current.nodesInSubtree;
        }
    }

    /**
     * Returns an iterator that refers to all the keys
     * in the table.
     */
    @Override
    public Iterable<Key> keys() {
        Queue<Key> q = new LinkedQueue<>();  //create an empty queue
        inorder(root, q);  // start the private recursive method
        return q;
    }

    private void inorder(Node current, Queue<Key> q){
        if(current == null){
            return;
        }

        //recursive calls:
        inorder(current.left, q);
        q.enqueue(current.key);
        inorder(current.right, q);
    }

    //helper methods
    private boolean isRed(Node current){
        return false;
    }
    private Node rotateLeft(Node current){
        return null;
    }
    private Node rotateRight(Node current){
        return null;
    }
    private void flipColor(Node current){

    }
}
