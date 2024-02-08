public class SequentialSearchST <Key, Value> implements SymbolTable<Key, Value>{

    private Node head;
    private int size;
    private class Node{
        Key key;
        Value value;
        Node next;
        //constructor
        public Node(Key key, Value value, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
    public SequentialSearchST() {
        head = null;
        size = 0;
    }

    /**
     * Put a key-value pair into the table
     *
     * @param key
     * @param val
     */
    @Override
    public void put(Key key, Value val) {
        // if the key is not in the list, put it up front at the head
        // but if the key is already in the list, replace the value in that node


        //loop through the nodes to see if the key is there
        Node current = head;
        while(current != null){
            if (key.equals(current.key)){
                current.value =  val;
                return;//done
            }
            current = current.next;
        }

        //if we got here, we know key doesn't exist in list
        // so make a new node and put it at the front
        head = new Node(key, val, head);
        size++;
    }

    /**
     * Returns the value paired with the given key.
     *
     * @param key
     */
    @Override
    public Value get(Key key) {

        Node current = head;
        while (current != null) {
            if (key.equals(current.key) ){
                return current.value;
            }
            current = current.next;
        }
        //if i got here, I know key is not in the list
        return null;
    }

    /**
     * Returns the number of key-value pairs in the table.
     *
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns an iterator that refers to all the keys
     * in the table.
     */
    @Override
    public Iterable<Key> keys() {
        return null;
    }
}
